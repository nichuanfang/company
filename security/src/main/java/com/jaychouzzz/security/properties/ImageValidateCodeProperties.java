package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname ImageValidateCodeProperties
 * @description 图形验证码配置
 * @Author chuanfang
 * @Date 2020/5/14 14:49
 * @Version 1.0
 */
@Data
public class ImageValidateCodeProperties extends ValidateCodeProperties{
    /**
     * 图形验证码长度
     */
    private Integer length = 40;
    /**
     * 图形验证码宽度
     */
    private Integer width = 30;

}
