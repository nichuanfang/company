package com.jaychouzzz.biz.web.controller.principal;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname AccountCtrl
 * @description 账户信息控制器
 * @Author chuanfang
 * @Date 2020/6/10 16:57
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
public class AccountCtrl {

    /**
     * 获取账号
     * @return 认证信息
     */
    @RequestMapping(value = "/account",method = RequestMethod.POST)
    public Authentication retrieveAccount() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


}
