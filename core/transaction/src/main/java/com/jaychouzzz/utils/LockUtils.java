package com.jaychouzzz.utils;

import com.jaychouzzz.common.function.Empty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @Classname LockUtils
 * @description 锁工具
 * @Author chuanfang
 * @Date 2020/6/8 16:17
 * @Version 1.0
 */
@Data
@Component
@AllArgsConstructor
public class LockUtils<T, R> {

    private RedisLockRegistry redisLockRegistry;

    /**
     *  通用业务
     * @param business 纯业务接口
     * @param lockKey 锁
     */
    public void commonBusiness(Empty business, Object lockKey) {
        Lock lock = redisLockRegistry.obtain(lockKey);
        boolean result = lock.tryLock();
        //获取锁成功->走业务
        if (result) {
            business.run();
            lock.unlock();
        }
    }

    /**
     * 抽离加锁业务
     *
     * @param business 业务
     * @param lockKey lockKey
     * @param t        业务参数
     */
    public void consumerBusiness(Consumer<T> business, T t,Object lockKey) {
        Lock lock = redisLockRegistry.obtain(lockKey);
        boolean result = lock.tryLock();
        //获取锁成功->走业务
        if (result) {
            business.accept(t);
            lock.unlock();
        }
    }


    /**
     * 抽离加锁业务
     *
     * @param business 业务
     * @param lockKey lockKey
     */
    public R functionBusiness(Function<T, R> business, T t,Object lockKey) {
        Lock lock = redisLockRegistry.obtain(lockKey);
        boolean flag = lock.tryLock();
        try {
            if (flag) {
                return business.apply(t);
            }
            return null;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 抽离加锁业务
     *
     * @param business  业务
     * @param lockKey lockKey
     * @return R 返回值
     */
    public R supplierBusiness(Supplier<R> business,Object lockKey) {
        Lock lock = redisLockRegistry.obtain(lockKey);
        boolean result = lock.tryLock();
        //获取锁成功->走业务
        try {
            if (result) {
                return business.get();
            } else {
                return null;
            }
        } finally {
//            1、不管有木有出现异常，finally块中代码都会执行；
//            2、当try和catch中有return时，finally仍然会执行；
//            3、finally是在return后面的表达式运算后执行的（此时并没有返回运算后的值，而是先把要返回的值保存起来，管finally中的代码怎么样，返回的值都不会改变，任然是之前保存的值），所以函数返回值是在finally执行前确定的；
//            4、finally中最好不要包含return，否则程序会提前退出，返回值不是try或catch中保存的返回值。
            lock.unlock();
        }
    }

}
