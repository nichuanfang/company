package com.jaychouzzz.biz.web.service;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.security.config.MySecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Classname MyUserDetailsService
 * @description 用户信息获取服务
 * @Author chuanfang
 * @Date 2020/5/14 15:34
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserMapper userMapper;

    private MySecurityConfig mySecurityConfig;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username,"$2a$10$.VlLv0T/0Lb4IKFbnYBahemH.iXt2l/gH7ZDbeB9eGxM6JJFzynjK", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
