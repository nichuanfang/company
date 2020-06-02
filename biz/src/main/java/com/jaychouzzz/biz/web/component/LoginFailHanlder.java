package com.jaychouzzz.biz.web.component;

import cn.hutool.core.util.StrUtil;
import com.jaychouzzz.security.handler.MyLoginFailureHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname LoginFailHanlder
 * @description 登录失败处理器
 * @Author chuanfang
 * @Date 2020/6/2 14:43
 * @Version 1.0
 */
@Component
@Slf4j
public class LoginFailHanlder extends MyLoginFailureHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        log.error("登录错误:"+e.getLocalizedMessage());
        if(e.getCause() instanceof NullPointerException) {
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/loginError?msg=Error: User is not exists!");
        }else{
            redirectStrategy.sendRedirect(httpServletRequest,httpServletResponse,"/loginError?msg=Error: "+e.getMessage());
        }
    }

}
