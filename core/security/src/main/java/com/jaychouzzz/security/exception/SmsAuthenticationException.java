package com.jaychouzzz.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Classname SmsAuthenticationException
 * @description
 * @Author chuanfang
 * @Date 2020/6/10 14:06
 * @Version 1.0
 */
public class SmsAuthenticationException extends AuthenticationException {

    public SmsAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public SmsAuthenticationException(String msg) {
        super(msg);
    }
}
