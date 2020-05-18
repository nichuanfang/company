package com.jaychouzzz.security.component;

import lombok.AllArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname AuthenticationController
 * @description 认证控制器
 * @Author chuanfang
 * @Date 2020/5/14 14:38
 * @Version 1.0
 */
@Controller
public class AuthenticationController {

    @RequestMapping(value = "/form/authentication")
    public String handleLoginPage() {
        return "signin";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }
}
