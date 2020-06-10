package com.jaychouzzz.security.component;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @Classname AccountChecker
 * @description 账号校验器
 * @Author chuanfang
 * @Date 2020/6/10 14:21
 * @Version 1.0
 */
public interface AccountChecker {

    /**
     * 通过手机号校验+获取用户信息
     * @param phone 手机号
     * @return 用户详情
     */
    public User obtainAndCheck(String phone);

}
