package com.jaychouzzz.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Classname PhoneNotFoundException
 * @description
 * @Author chuanfang
 * @Date 2020/6/9 15:34
 * @Version 1.0
 */
public class PhoneNotFoundException extends AuthenticationException {

    public PhoneNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public PhoneNotFoundException(String msg) {
        super(msg);
    }
}
