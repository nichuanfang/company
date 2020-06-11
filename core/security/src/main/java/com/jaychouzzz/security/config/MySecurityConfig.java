package com.jaychouzzz.security.config;

import com.jaychouzzz.security.component.AccountChecker;
import com.jaychouzzz.security.component.SmsCodeChecker;
import com.jaychouzzz.security.handler.MyLoginFailureHandler;
import com.jaychouzzz.security.handler.MyLoginSuccessfulHandler;
import com.jaychouzzz.security.manager.SecurityAuthorityManager;
import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

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

    private PersistentTokenRepository tokenRepository;

    private UserDetailsService userDetailsService;

    private SmsCodeChecker smsCodeChecker;

    private AccountChecker accountChecker;

    private SecurityAuthorityManager authorityManager;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsConfigurer smsConfigurer = new SmsConfigurer(smsCodeChecker,accountChecker);
        smsConfigurer.setAuthenticationSuccessHandler(myLoginSuccessfulHandler);
        smsConfigurer.setAuthenticationFailureHandler(myLoginFailureHandler);
        http
                .apply(smsConfigurer)
                .and()
                //表单登录配置  包括用户名密码登录  或者自定义表单登录(短信验证码)
                .formLogin()
                //页面可配置 默认配置<应用配置<请求配置
                .loginPage(securityProperties.getLogin().getLoginPage())
                .loginProcessingUrl(securityProperties.getLogin().getLoginProcessingUrl())
                .successHandler(myLoginSuccessfulHandler)
                .failureHandler(myLoginFailureHandler)
                .and()
                .rememberMe()
                .tokenRepository(tokenRepository)
                .userDetailsService(userDetailsService)
                .authenticationSuccessHandler(myLoginSuccessfulHandler)
                //token有效时间
                .tokenValiditySeconds(securityProperties.getRememberMe().getTokenValiditySeconds())
                //是否总是记住 即使前端没有remember-me参数
                .alwaysRemember(securityProperties.getRememberMe().isAlwaysRemember())
                //cookie作用的域名
                .rememberMeCookieDomain(securityProperties.getRememberMe().getRememberMeCookieDomain())
                //cookie名称
                .rememberMeCookieName(securityProperties.getRememberMe().getRememberMeCookieName())
                //如果置为true则只支持https
                .useSecureCookie(securityProperties.getRememberMe().isUseSecureCookie())
                .and()
                //授权配置
                .authorizeRequests()
                .antMatchers(authorityManager.antPatterns())
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
        web.ignoring().antMatchers("/static/css/**", "/static/js/**", "/static/images/**", "/static/fonts/**");
    }

}
