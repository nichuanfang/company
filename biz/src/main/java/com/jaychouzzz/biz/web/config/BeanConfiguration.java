package com.jaychouzzz.biz.web.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
