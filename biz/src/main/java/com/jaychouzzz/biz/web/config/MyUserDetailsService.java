package com.jaychouzzz.biz.web.config;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


/**
 * @Classname MyUserDetailsService
 * @description 用户信息获取服务
 * @Author chuanfang
 * @Date 2020/5/14 15:34
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Primary
public class MyUserDetailsService implements UserDetailsService {

    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.jaychouzzz.common.entity.User user = userMapper.selectByUserName(username);
        return new User(username,user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    public User loadUserByPhone(String phone){
        com.jaychouzzz.common.entity.User user = null;
        try {
            user = userMapper.selectByPhone(phone);
        } catch (Exception e) {
            throw new InternalAuthenticationServiceException(e.getCause().getMessage());
        }
        switch (user.getAccountStatus()) {
            case LOCKED: {
                throw new InternalAuthenticationServiceException("账号已锁定");
            }
            case EXPIRED: {
                throw new InternalAuthenticationServiceException("账号已过期");
            }
            default: break;
        }

        return new User(user.getUsername(),user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
