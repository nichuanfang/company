package com.jaychouzzz.common.exception;


/**
 * @Classname SmsException
 * @description 短信验证码异常
 * @Author chuanfang
 * @Date 2020/5/22 10:45
 * @Version 1.0
 */
public class SmsException extends RuntimeException{


    public SmsException() {
        super();
    }

    public SmsException(String msg) {
        super(msg);
    }
}
