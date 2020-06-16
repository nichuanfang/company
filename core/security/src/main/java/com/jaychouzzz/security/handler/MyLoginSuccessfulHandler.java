package com.jaychouzzz.security.handler;

import com.jaychouzzz.common.constants.AuthorizeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname MyLoginSuccessfulHandler
 * @description 登录成功处理器
 * @Author chuanfang
 * @Date 2020/5/14 14:56
 * @Version 1.0
 */
@Slf4j
public class MyLoginSuccessfulHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        if(authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            String registrationId = token.getAuthorizedClientRegistrationId();
            if(token.getAuthorizedClientRegistrationId().equalsIgnoreCase(AuthorizeType.GITHUB)) {
                log.debug("已授权:"+registrationId+",用户:"+token.getPrincipal().getAttribute("login"));
            }else {
                log.debug("已授权:"+registrationId+",用户:"+token.getPrincipal().getAttribute("name"));
            }
        }else {
            log.debug("登录成功");
        }
        super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
    }
}
