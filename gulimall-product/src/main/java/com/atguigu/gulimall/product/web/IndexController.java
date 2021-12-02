package com.atguigu.gulimall.product.web;

import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;
import com.atguigu.gulimall.product.vo.Catelog2Vo;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @Autowired
    private StringRedisTemplate redisTemplate;

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

    // *************************************可重入锁***************************************************
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

    //*******************************读写锁*********************************************************

    /**
     * 保证一定能读到最新数据，修改期间，写锁是一个互斥锁（排他锁，独享锁），读锁是一个共享锁
     * 写锁没释放，读锁必须等待
     * 读 + 读：相当于无锁，并发读，只会在redis中记录好，所有当前的读锁。他们都会同时加索成功。
     * 写 + 读：等待写锁释放。
     * 写 + 写：阻塞方式。
     * 读 + 写：有读锁写也需要等待。
     *
     * @return
     */
    @GetMapping("/write")
    @ResponseBody
    public String write() {
        RLock rLock = redissonClient.getReadWriteLock("rw-lock").writeLock();
        String s = "";
        try {
            rLock.lock();
            Thread.sleep(20000);
            s = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set("writeValue", s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
        return s;
    }

    @GetMapping("/read")
    @ResponseBody
    public String read() {
        String s = "";
        RLock rLock = redissonClient.getReadWriteLock("rw-lock").readLock();
        rLock.lock();
        try {
            s = redisTemplate.opsForValue().get("writeValue");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rLock.unlock();
        }
        return s;
    }

    //***********************************信号量***********************************************

    @GetMapping("park")
    @ResponseBody
    public String park() throws InterruptedException {
        RSemaphore park = redissonClient.getSemaphore("park");
        boolean b = park.tryAcquire();
        return "ok => " + b;
    }

    @GetMapping("go")
    @ResponseBody
    public String go() {
        RSemaphore park = redissonClient.getSemaphore("park");
        park.release();
        return "ok";
    }

    //*********************************闭锁*****************************************

    @GetMapping("/lockDoor")
    @ResponseBody
    public String lockDoor() throws InterruptedException {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.trySetCount(5);
        door.await();//等待闭锁都完成
        return "放假了";
    }

    @GetMapping("/gogogo/{id}")
    @ResponseBody
    public String gogogo(@PathVariable("id") Long id) {
        RCountDownLatch door = redissonClient.getCountDownLatch("door");
        door.countDown();//计数减一
        return id + "班的人都走了~~";
    }

    @GetMapping("hcct/{str}")
    @ResponseBody
    public String hcct(@PathVariable("str") String str) {
        RLock lock = redissonClient.getLock("gcct-lock");
        lock.lock();
        try {
            String key = redisTemplate.opsForValue().get(str);
            String val = "";
            if (StringUtils.isEmpty(key)) {
                //模拟查数据库
                val = null;
                redisTemplate.opsForValue().set(str, "null", 60, TimeUnit.SECONDS);
                System.out.println("走数据库，查询结果为空将空数据记录缓存中保存60s");
                return "结果为" + val;
            } else {
                System.out.println("走缓存");
                return key;
            }
        } finally {
            lock.unlock();
        }
    }
}
