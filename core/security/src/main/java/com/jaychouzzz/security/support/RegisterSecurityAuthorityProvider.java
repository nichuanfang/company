package com.jaychouzzz.security.support;

import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RegisterSecurityAuthorityProvider
 * @description 注册相关的权限
 * @Author chuanfang
 * @Date 2020/6/11 11:11
 * @Version 1.0
 */
@AllArgsConstructor
@Component
@Slf4j
public class RegisterSecurityAuthorityProvider implements SecurityAuthorityProvider{

    private SecurityProperties securityProperties;

    @Override
    public List<String> grantAuthority() {
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add(securityProperties.getRegister().getRegisterUrl());
        authorities.add(securityProperties.getRegister().getRegisterProcessingUrl());
        return authorities;
    }
}
