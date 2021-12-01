package com.atguigu.gulimall.product.web;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/29 11:09
 */
@Controller
@Repository
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {
        // TODO 1.查询所有的一级分类
        List<CategoryEntity> categoryEntities = categoryService.getLevel1Categorys();
        model.addAttribute("categorys", categoryEntities);
        return "index";
    }

    @GetMapping("/index/catalog.json")
    @ResponseBody
    public Map<String, List<Catelog2Vo>> getCatalogJson() {
        return categoryService.getCatalogJson();
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        RLock lock = redissonClient.getLock("guli-lock");
        lock.lock();//阻塞式等待，默认加锁的时间都是30s时间。
        //1.锁的自动续期，如果业务超长，运行期间自动给锁续上新的30s，不用担心业务过长，锁自动过期被删除掉。
        //2.加锁的业务只要运行完成，就不会给当前锁续期，即使不手动解锁，默认在30s之后自动删除。
        try {
            System.out.println("加锁成功！，开始执行业务代码！" + Thread.currentThread().getName());
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁..." + Thread.currentThread().getName());
            lock.unlock();
        }
        return "ok!hello!";
    }

    @GetMapping("world")
    @ResponseBody
    public String world() {
        RLock lock = redissonClient.getLock("my-lock");
        //
        lock.lock(10, TimeUnit.SECONDS);// 10s自动解锁，自动解锁时间一定要大于业务的执行时间。
        //问题：在锁时间到期后，不会自动续期
        //1.如果我们传递了锁的超时时间，就发送个redis执行脚本，进行占锁，默认超时就是我们指定的时间
        //2.如果我们未指定锁的超时时间，就使用[3*1000]LockWatchdogTimeout看门狗的默认时间
        //  只要占锁成功，就会启动一个定时任务【重新给锁设置超时时间，新的过期时间就是看门狗的默认时间】，每隔10s都会自动续期
        //  internalLockLeaseTime【看门狗时间】/3，10s
        try {
            System.out.println("加锁成功！，开始执行业务代码" + Thread.currentThread().getId());
            Thread.sleep(30000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("释放锁..." + Thread.currentThread().getId());
            lock.unlock();
        }
        return "world";
    }
}
