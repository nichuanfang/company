package com.jaychouzzz.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Classname RedisLockProperties
 * @description redis锁配置
 * @Author chuanfang
 * @Date 2020/6/8 15:36
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "jaychouzzz.transaction")
@Data
public class RedisLockProperties {
    /**
     * 锁前缀
     */
    private String lockPrefix = "lock_";
    /**
     * 过期时间 (单位: s)
     */
    private int expireTime = 60;

}
