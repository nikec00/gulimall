package com.atguigu.gulimall.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.gulimall.product.service.CategoryBrandRelationService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import org.apache.commons.lang.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import org.springframework.transaction.annotation.Transactional;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 1. 查出所有数据
        List<CategoryEntity> categoryEntities = baseMapper.selectList(null);
        // 2. 生成树结构
        // 2.1 找到所有一级分类
        List<CategoryEntity> level1Menus =
                categoryEntities.stream()
                        .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                        .map((menu) -> {
                            menu.setChildren(getChildrens(menu, categoryEntities));
                            return menu;
                        })
                        .sorted((menu1, menu2) -> {
                            return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                        })
                        .collect(Collectors.toList());
        return level1Menus;
    }

    @Override
    public void removeMenusByIds(List<Long> asList) {
        baseMapper.deleteBatchIds(asList);
    }

    @Override
    public Long[] findCatelogPath(Long catelogId) {
        List<Long> path = new ArrayList<>();
        List<Long> parentPath = findParentPath(catelogId, path);
        Collections.reverse(parentPath);
        return (Long[]) path.toArray(new Long[parentPath.size()]);
    }

    /**
     * 级联更新所有关联的数据
     *
     * @param category
     */
    @Override
    @Transactional
    public void updateCasecade(CategoryEntity category) {
        this.updateById(category);
        categoryBrandRelationService.updateCategory(category.getCatId(), category.getName());
    }
    // 每一个需要缓存的数据我们都指定要放到哪个名字下的缓存【缓存的分区（按照业务类型分类）】
    @Cacheable({"category"}) // 代表当前方法的结果需要缓存，如果缓存中有，方法不调用。如果缓存中没有，会调用方法，并保存缓存。
    @Override
    public List<CategoryEntity> getLevel1Categorys() {
        System.out.println("getLevel1Categorys。。。。。。");
        List<CategoryEntity> categoryEntities = baseMapper.selectList(new QueryWrapper<CategoryEntity>().eq("parent_cid", 0));
        return categoryEntities;
    }

    @Override
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        // 加入缓存逻辑
        /**
         * 1.空结果缓存，解决缓存穿透
         * 2.设置过期时间（加随机值） ，解决缓存雪崩
         * 3.加锁，解决缓存击穿
         */
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        if (StringUtils.isEmpty(catalogJSON)) {
            System.out.println("缓存不命中..查询了数据库..");
            return getCatalogJsonFromDbWithRedissonLock();
        }
        System.out.println("缓存命中。。直接返回");
        return JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
        });
    }

    /**
     * 使用redisson分布式锁
     *
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedissonLock() {
        RLock lock = redissonClient.getLock("catalogJson-lock");
        lock.lock();
        System.out.println("获取分布式锁成功");
        Map<String, List<Catelog2Vo>> dataFromDb;
        try {
            // 查数据库
            dataFromDb = getDataFromDb();
        } finally {
            lock.unlock();
        }
        return dataFromDb;
    }

    /**
     * 使用redis实现分布式锁
     * 最终形态：加锁时候原子性；删除锁的时候保证原子性
     *
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDbWithRedisLock() {
        String uuid = UUID.randomUUID().toString();
        Boolean lock = stringRedisTemplate.opsForValue().setIfAbsent("lock", uuid, 30, TimeUnit.SECONDS);
        if (lock) {
            System.out.println("获取分布式锁成功");
            Map<String, List<Catelog2Vo>> dataFromDb;
            try {
                dataFromDb = getDataFromDb();
            } finally {
                // 使用lua脚本删除锁
                final String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                stringRedisTemplate.execute(new DefaultRedisScript<Long>(script, Long.class), Arrays.asList("lock"), uuid);
            }
            return dataFromDb;
        } else {
            //自旋：加索失败重试
            //休眠100ms重试
            System.out.println("获取分布式锁失败。。等待重试");
            return getCatalogJsonFromDbWithRedisLock();
        }

    }

    private Map<String, List<Catelog2Vo>> getDataFromDb() {
        String catalogJSON = stringRedisTemplate.opsForValue().get("catalogJSON");
        if (!StringUtils.isEmpty(catalogJSON)) {
            Map<String, List<Catelog2Vo>> result = JSON.parseObject(catalogJSON, new TypeReference<Map<String, List<Catelog2Vo>>>() {
            });
            return result;
        }
        System.out.println("查了数据库。。");
        List<CategoryEntity> selectList = baseMapper.selectList(null);
        List<CategoryEntity> level1Categorys = getParent_cid(selectList, 0L);
        Map<String, List<Catelog2Vo>> parent_cid = level1Categorys.stream().collect(Collectors.toMap(k -> k.getCatId().toString(), v -> {
            // 每一个1级分类，查到这个一级分类的二级分类
            List<CategoryEntity> entities = getParent_cid(selectList, v.getParentCid());
            List<Catelog2Vo> collect = null;
            if (entities != null) {
                collect = entities.stream().map(l2 -> {
                    Catelog2Vo catelog2Vo = new Catelog2Vo(v.getCatId().toString(), null, l2.getCatId().toString(), l2.getName());
                    // 找当前分类的三级分类
                    List<CategoryEntity> categoryEntities3 = getParent_cid(selectList, l2.getCatId());
                    if (categoryEntities3 != null) {
                        List<Catelog2Vo.Catelog3Vo> catelog3Vos = categoryEntities3.stream().map(l3 -> {
                            Catelog2Vo.Catelog3Vo catelog3Vo = new Catelog2Vo.Catelog3Vo(l2.getCatId().toString(), l3.getCatId().toString(), l3.getName());
                            return catelog3Vo;
                        }).collect(Collectors.toList());
                        catelog2Vo.setCatalog3List(catelog3Vos);
                    }
                    return catelog2Vo;
                }).collect(Collectors.toList());
            }
            return collect;
        }));
        // 将查到的数据放入缓存
        String jsonString = JSON.toJSONString(parent_cid);
        stringRedisTemplate.opsForValue().set("catalogJSON", jsonString, 1, TimeUnit.DAYS);
        return parent_cid;
    }

    /**
     * 从数据库查询并分类数据
     *
     * @return
     */
    public Map<String, List<Catelog2Vo>> getCatalogJsonFromDb() {
        /**
         * 1.将数据库多次查询变为一次
         */
        synchronized (this) {
            return getDataFromDb();
        }

    }

    private List<CategoryEntity> getParent_cid(List<CategoryEntity> selectList, Long parent_cid) {
        List<CategoryEntity> collect = selectList.stream().filter(item -> item.getParentCid() == parent_cid).collect(Collectors.toList());
        return collect;
    }

    private List<Long> findParentPath(Long catelogId, List paths) {
        // 1.收集当前节点id
        paths.add(catelogId);
        CategoryEntity byId = this.getById(catelogId);
        if (byId.getParentCid() != 0) {
            findParentPath(byId.getParentCid(), paths);
        }
        return paths;
    }


    private List<CategoryEntity> getChildrens(CategoryEntity root, List<CategoryEntity> all) {
        List<CategoryEntity> collect = all.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == root.getCatId())
                .map((menu) -> {
                    // 找到子菜单
                    menu.setChildren(getChildrens(menu, all));
                    return menu;
                })
                // 排序
                .sorted((menu1, menu2) -> {
                    return (menu1.getSort() == null ? 0 : menu1.getSort()) - (menu2.getSort() == null ? 0 : menu2.getSort());
                })
                .collect(Collectors.toList());
        return collect;
    }

}