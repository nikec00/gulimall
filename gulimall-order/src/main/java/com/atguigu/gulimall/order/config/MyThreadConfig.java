package com.atguigu.gulimall.order.config;

import org.omg.SendingContext.RunTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2022/1/7 22:56
 */
@Configuration
public class MyThreadConfig {

    public static final Integer CORE_SIZE = Runtime.getRuntime().availableProcessors() + 1;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_SIZE,
                20,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        return executor;
    }
}
