package com.jaychouzzz.security.config;

import com.jaychouzzz.security.handler.MyLoginFailureHandler;
import com.jaychouzzz.security.handler.MyLoginSuccessfulHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Classname SecurityBeanConfig
 * @description security bean config相关   需要第三方应用扩展时需要@ConditionalOnMissingBean
 * @Author chuanfang
 * @Date 2020/5/14 15:03
 * @Version 1.0
 */
@Configuration
public class SecurityBeanConfig {

    /**
     * 缺省配置
     * @return 登录成功处理器
     */
    @Bean
    @ConditionalOnMissingBean(MyLoginSuccessfulHandler.class)
    public MyLoginSuccessfulHandler myLoginSuccessfulHandler() {
        return new MyLoginSuccessfulHandler();
    }

    /**
     * 登录失败处理器
     * @return 处理器
     */
    @Bean
    @ConditionalOnMissingBean(MyLoginFailureHandler.class)
    public MyLoginFailureHandler myLoginFailureHandler() {
        return new MyLoginFailureHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
