package com.jaychouzzz.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @Classname SecurityProperties
 * @description 安全配置项
 * @Author chuanfang
 * @Date 2020/5/14 14:07
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "jaychouzzz.security")
@Data
public class SecurityProperties {
    /**
     * 登录相关配置
     */
    @NestedConfigurationProperty
    private LoginProperties login;
    /**
     * 短信验证码相关配置
     */
    @NestedConfigurationProperty
    private ValidateCodeProperties sms;
    /**
     * 图形验证码配置
     */
    @NestedConfigurationProperty
    private ImageValidateCodeProperties image;

}
