package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname RememberMeProperties
 * @description 记住我配置
 * @Author chuanfang
 * @Date 2020/6/3 9:33
 * @Version 1.0
 */
@Data
public class RememberMeProperties {
    /**
     * one day default
     */
    private int tokenValiditySeconds = 60*60*24;
    /**
     * enable RememberMe always or not
     */
    private boolean alwaysRemember = false;
    /**
     * cookie's domain
     */
    private String rememberMeCookieDomain;
    /**
     * cookie's name
     */
    private String rememberMeCookieName;
    /**
     * use HTTPS or not
     */
    private boolean useSecureCookie;
}
