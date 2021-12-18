package com.atguigu.gulimall.product.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/17 23:15
 */
@Configuration
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class MyThreadConfig {

    @Autowired
    private ThreadPoolConfigProperties properties;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(properties.getCoreSize(), properties.getMaxSize(), properties.getKeepAliveTime(),
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(300),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        return threadPoolExecutor;
    }
}
