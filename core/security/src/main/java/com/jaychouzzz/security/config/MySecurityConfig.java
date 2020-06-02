package com.jaychouzzz.security.config;

import com.jaychouzzz.security.handler.MyLoginFailureHandler;
import com.jaychouzzz.security.handler.MyLoginSuccessfulHandler;
import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Classname MySecurityConfig
 * @description security配置
 * @Author chuanfang
 * @Date 2020/5/14 14:18
 * @Version 1.0
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
@AllArgsConstructor
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    private SecurityProperties securityProperties;

    private MyLoginSuccessfulHandler myLoginSuccessfulHandler;

    private MyLoginFailureHandler myLoginFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //表单登录配置  包括用户名密码登录  或者自定义表单登录(短信验证码)
                .formLogin()
                //页面可配置 默认配置<应用配置<请求配置
                .loginPage(securityProperties.getLogin().getLoginPage())
                .loginProcessingUrl(securityProperties.getLogin().getLoginProcessingUrl())
                .successHandler(myLoginSuccessfulHandler)
                .failureHandler(myLoginFailureHandler)
                .and()
                //授权配置
                .authorizeRequests()
                .antMatchers(securityProperties.getLogin().getLoginPage()
                        ,securityProperties.getRegister().getRegisterUrl()
                        ,securityProperties.getRegister().getRegisterProcessingUrl()
                        ,securityProperties.getLogin().getLoginErrorUrl())
                .permitAll()
                //剩下的请求需要认证
                .anyRequest()
                .authenticated()
                //跨域防护
                .and()
                .csrf()
                .disable();
    }

    /**
     * 不拦截静态资源
     *
     * @param web web对象
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/css/**","/static/js/**","/static/images/**","/static/fonts/**");
    }

}
