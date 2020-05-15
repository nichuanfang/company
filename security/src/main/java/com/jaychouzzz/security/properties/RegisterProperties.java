package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname RegisterProperties
 * @description 注册相关读取器
 * @Author chuanfang
 * @Date 2020/5/15 11:13
 * @Version 1.0
 */
@Data
public class RegisterProperties {
    /**
     * 注册页面
     */
    private String registerUrl = "/register";
    /**
     * 注册处理url
     */
    private String registerProcessingUrl = "/handleRegister";
}
