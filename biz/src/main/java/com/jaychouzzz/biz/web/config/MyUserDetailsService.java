package com.jaychouzzz.biz.web.config;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.jaychouzzz.common.entity.User user = userMapper.selectByUserName(username);
        return new User(username,user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
