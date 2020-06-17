package com.jaychouzzz.config;

import com.jaychouzzz.properties.RedisLockProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @Classname RedisLockConfiguration
 * @description redis锁
 * @Author chuanfang
 * @Date 2020/6/8 15:31
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties(RedisLockProperties.class)
public class RedisLockConfiguration {

    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory factory, RedisLockProperties lockProperties, Executor executor) {
        //registryKey: The key prefix for locks.
        RedisLockRegistry redisLockRegistry = new RedisLockRegistry(factory, lockProperties.getLockPrefix(), lockProperties.getExpireTime());
        redisLockRegistry.setExecutor(executor);
        return redisLockRegistry;
    }
}
