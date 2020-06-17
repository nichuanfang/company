package com.jaychouzzz.security.support;

import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ImageSecurityAuthorityProvider
 * @description 图形验证码相关权限
 * @Author chuanfang
 * @Date 2020/6/11 11:15
 * @Version 1.0
 */
@AllArgsConstructor
@Component
@Slf4j
public class ImageSecurityAuthorityProvider implements SecurityAuthorityProvider{

    private SecurityProperties securityProperties;

    @Override
    public List<String> grantAuthority() {
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add(securityProperties.getImage().getCaptchaPathRegex());
        return authorities;
    }
}
