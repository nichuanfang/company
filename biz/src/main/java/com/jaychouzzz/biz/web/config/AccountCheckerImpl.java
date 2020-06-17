package com.jaychouzzz.biz.web.config;

import com.jaychouzzz.security.component.AccountChecker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

/**
 * @Classname AccountCheckerImpl
 * @description
 * @Author chuanfang
 * @Date 2020/6/10 14:36
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class AccountCheckerImpl implements AccountChecker {

    private MyUserDetailsService  myUserDetailsService;

    @Override
    public User obtainAndCheck(String phone) {
        return myUserDetailsService.loadUserByPhone(phone);
    }
}
