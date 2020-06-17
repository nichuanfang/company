package com.jaychouzzz.biz.web.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.sql.DataSource;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Classname BeanConfiguration
 * @description
 * @Author chuanfang
 * @Date 2020/5/18 17:26
 * @Version 1.0
 */
@Configuration
public class BeanConfiguration {

    /**
     * mybatisplus 乐观锁需要此组件
     * @return 乐观锁拦截器
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    /**
     * 配置线程池
     * @return  executor
     */
    @Bean
    @Primary
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //获取到服务器的cpu核心数
        int processorsNum = Runtime.getRuntime().availableProcessors();
        //核心线程池大小
        executor.setCorePoolSize(5);
        //最大线程数
        executor.setMaxPoolSize(100);
        //队列程度
        executor.setQueueCapacity(1000);
        //队列空闲时间
        executor.setKeepAliveSeconds(1000);
        //线程名称前缀
        executor.setThreadNamePrefix("task-asyn");
        //配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

}
