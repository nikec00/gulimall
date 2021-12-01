package com.atguigu.gulimall.product.cofig;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @Description：
 * @Author: nkc
 * @Date: 2021/12/1 13:41
 */
@Configuration
public class MyRedissonConfig {


    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() throws IOException {
        // 创建配置
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // 根据config创建出redisson的示例
        return Redisson.create(config);
    }

}
