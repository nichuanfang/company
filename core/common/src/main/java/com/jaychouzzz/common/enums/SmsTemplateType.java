package com.jaychouzzz.common.enums;

import lombok.Getter;

/**
 * @Classname SmsTemplateType
 * @description 短信模板类型
 * @Author chuanfang
 * @Date 2020/6/5 15:49
 * @Version 1.0
 */
@Getter
public enum SmsTemplateType {
    /**
     * 语音
     */
    VOICE("voice",0),
    /**
     * 推广
     */
    MARKETING("marketing",1),
    /**
     * 通知
     */
    NOTIFICATION("notification",2),
    /**
     * 验证码
     */
    VERIFICATION("verification",3);

    private String desc;

    private Integer code;

    SmsTemplateType(String desc,Integer code) {
        this.desc = desc;
        this.code = code;
    }
}
