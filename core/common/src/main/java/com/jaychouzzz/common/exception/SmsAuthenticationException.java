package com.jaychouzzz.common.exception;

import org.springframework.core.NestedRuntimeException;

/**
 * @Classname SmsAuthenticationException
 * @description  sms认证异常
 * @Author chuanfang
 * @Date 2020/6/9 10:40
 * @Version 1.0
 */
public class SmsAuthenticationException extends NestedRuntimeException {

    private static final long serialVersionUID = -8142846498019237976L;

    public SmsAuthenticationException(String msg) {
        super(msg);
    }

    public SmsAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
