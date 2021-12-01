package com.atguigu.gulimall.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/11/30 15:58
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class GulimallProductApplicationTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testStringRedisTemplate() {
        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set("hello", "world_" + UUID.randomUUID().toString());

        String hello = opsForValue.get("hello");
        System.out.println("之前保存的数据是：" + hello);
    }


}
