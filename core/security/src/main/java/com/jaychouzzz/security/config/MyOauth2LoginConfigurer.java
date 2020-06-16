package com.jaychouzzz.security.config;

import com.jaychouzzz.security.component.CustomOauth2UserService;
import com.jaychouzzz.security.component.MyAuthorizationCodeTokenResponseClient;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestRedirectFilter;
import org.springframework.security.oauth2.client.web.OAuth2LoginAuthenticationFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 * @Classname MyOauth2LoginConfigurer
 * @description
 * @Author chuanfang
 * @Date 2020/6/12 9:59
 * @Version 1.0
 */
@Builder
@Data
public class MyOauth2LoginConfigurer implements Customizer<OAuth2LoginConfigurer<HttpSecurity>> {

    private AuthenticationSuccessHandler successHandler;

    private AuthenticationFailureHandler failureHandler;

    private MyAuthorizationCodeTokenResponseClient tokenResponseClient;

    private CustomOauth2UserService oauth2UserService;

    @Override
    public void customize(OAuth2LoginConfigurer<HttpSecurity> http){
        http
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .authorizationEndpoint()
                //这个url就是前端发起授权的url /oauth2/authorization 经过OAuth2AuthorizationRequestRedirectFilter重定向到服务提供方
                .baseUri(OAuth2AuthorizationRequestRedirectFilter.DEFAULT_AUTHORIZATION_REQUEST_BASE_URI)
                .and()
                .redirectionEndpoint()
                //这个url就是获取code后重定向的url基础路径  /login/oauth2/code/*  不配置会进不了{@OAuth2LoginAuthenticationFilter}无法完成授权码发放
                .baseUri(OAuth2LoginAuthenticationFilter.DEFAULT_FILTER_PROCESSES_URI)
                .and()
                .tokenEndpoint()
                .accessTokenResponseClient(tokenResponseClient)
                .and()
                .userInfoEndpoint()
                .userService(oauth2UserService);

    }
}
