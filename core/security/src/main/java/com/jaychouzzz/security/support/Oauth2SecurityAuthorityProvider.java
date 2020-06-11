package com.jaychouzzz.security.support;

import com.jaychouzzz.security.properties.SecurityProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname Oauth2SecurityAuthorityProvider
 * @description oauth2授权相关
 * @Author chuanfang
 * @Date 2020/6/11 15:34
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class Oauth2SecurityAuthorityProvider implements SecurityAuthorityProvider{

    @Override
    public List<String> grantAuthority() {
        ArrayList<String> authorities = new ArrayList<>();
        authorities.add("/authorize/**");
        return authorities;
    }

}
