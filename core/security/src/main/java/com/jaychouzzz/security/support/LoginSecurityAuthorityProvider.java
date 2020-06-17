package com.jaychouzzz.security.support;

import com.jaychouzzz.security.properties.LoginProperties;
import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname LoginSecurityAuthorityProvider
 * @description 登录相关的授权配置
 * @Author chuanfang
 * @Date 2020/6/11 10:59
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class LoginSecurityAuthorityProvider implements SecurityAuthorityProvider{

    private SecurityProperties securityProperties;

    @Override
    public List<String> grantAuthority() {
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add(securityProperties.getLogin().getLoginPage());
        authorities.add(securityProperties.getLogin().getLoginProcessingUrl());
        authorities.add(securityProperties.getLogin().getErrorPage());
        return authorities;
    }
}
