package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname LoginProperties
 * @description 登录相关配置
 * @Author chuanfang
 * @Date 2020/5/14 14:45
 * @Version 1.0
 */
@Data
public class LoginProperties {
    /**
     * 登录页面
     */
    private String loginPage = "/form/authentication";
    /**
     * 告知UsernamePasswordAuthenticationFilter处理这个url
     */
    private String loginProcessingUrl = "/form/login";
    /**
     * 登录失败页面
     */
    private String loginErrorUrl = "/loginError";

}
