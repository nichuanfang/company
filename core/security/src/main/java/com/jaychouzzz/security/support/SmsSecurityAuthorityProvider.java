package com.jaychouzzz.security.support;

import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SmsSecurityAuthorityProvider
 * @description 短信验证码相关权限配置
 * @Author chuanfang
 * @Date 2020/6/11 11:20
 * @Version 1.0
 */
@Component
@Slf4j
@AllArgsConstructor
public class SmsSecurityAuthorityProvider implements SecurityAuthorityProvider{

    private SecurityProperties securityProperties;


    @Override
    public List<String> grantAuthority() {
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add(securityProperties.getSms().getValidateCodePath());
        return authorities;
    }
}
