package com.jaychouzzz.biz.web.service;

/**
 * @Classname ISmsManager
 * @description 短信模板管理器
 * @Author chuanfang
 * @Date 2020/6/8 16:04
 * @Version 1.0
 */
public interface ISmsManager {
    /**
     * 发送手机短信验证码
     * @param phone 手机号码
     */
    void sendSmsCode(String phone);
}
