package com.jaychouzzz.biz.web.service.serviceimpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.redis.util.RedisLockRegistry;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @Classname RedisLockTest
 * @description
 * @Author chuanfang
 * @Date 2020/6/8 15:46
 * @Version 1.0
 */
@SpringBootTest
public class RedisLockTest {

    @Autowired
    private RedisLockRegistry lockRegistry;

    @Test
    public void lockTest() {
        Lock lock = lockRegistry.obtain("lock");

        boolean flag = lock.tryLock();

        boolean b = lock.tryLock();


    }

}
