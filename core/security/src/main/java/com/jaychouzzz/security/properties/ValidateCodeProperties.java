package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname ValidateCodeProperties
 * @description 验证码配置
 * @Author chuanfang
 * @Date 2020/5/14 14:44
 * @Version 1.0
 */
@Data
public class ValidateCodeProperties {
    /**
     * 验证码长度
     */
    private Integer codeLength = 4;
    /**
     * 过期时间(单位:秒)
     */
    private Integer expireTime = 60;
    /**
     * 获取短信验证码接口
     */
    private String validateCodePath = "/getValidateCode";

}
