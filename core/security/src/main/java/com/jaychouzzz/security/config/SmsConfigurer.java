package com.jaychouzzz.security.config;

import com.jaychouzzz.security.component.AccountChecker;
import com.jaychouzzz.security.component.SmsCodeChecker;
import com.jaychouzzz.security.filter.SmsAuthenticationFilter;
import com.jaychouzzz.security.support.SmsAuthenticationProvider;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Classname SmsConfigurer
 * @description 短信验证码配置器
 * @Author chuanfang
 * @Date 2020/6/9 9:53
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private SmsCodeChecker smsCodeChecker;

    private AccountChecker accountChecker;

    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private AuthenticationFailureHandler authenticationFailureHandler;

    SmsConfigurer(SmsCodeChecker smsCodeChecker,AccountChecker accountChecker) {
        this.smsCodeChecker = smsCodeChecker;
        this.accountChecker = accountChecker;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter(http.getSharedObject(AuthenticationManager.class));
        //失败处理器
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        //成功处理器
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        http
                .addFilterBefore(smsAuthenticationFilter,UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(new SmsAuthenticationProvider(smsCodeChecker,accountChecker));
    }

}
