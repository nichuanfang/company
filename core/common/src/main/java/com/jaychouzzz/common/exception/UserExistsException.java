package com.jaychouzzz.common.exception;

/**
 * @Classname UserExistsException
 * @description 用户已存在异常
 * @Author chuanfang
 * @Date 2020/6/2 14:24
 * @Version 1.0
 */
public class UserExistsException extends RuntimeException{

    public UserExistsException(String msg) {
        super(msg);
    }

    public UserExistsException() {
        super("Username is already exist!,please retry");
    }
}
