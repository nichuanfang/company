package com.jaychouzzz.security.config;

import com.jaychouzzz.security.component.SmsCodeChecker;
import com.jaychouzzz.security.filter.SmsAuthenticationFilter;
import com.jaychouzzz.security.support.SmsAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Classname SmsConfigurer
 * @description 短信验证码配置器
 * @Author chuanfang
 * @Date 2020/6/9 9:53
 * @Version 1.0
 */
public class SmsConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private SmsCodeChecker smsCodeChecker;

    public SmsConfigurer(SmsCodeChecker smsCodeChecker) {
        this.smsCodeChecker = smsCodeChecker;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new SmsAuthenticationFilter(http.getSharedObject(AuthenticationManager.class)),UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(new SmsAuthenticationProvider(smsCodeChecker));
    }

}
