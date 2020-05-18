package com.jaychouzzz.common.exception;

/**
 * @Classname UserInsertException
 * @description 用户新增异常
 * @Author chuanfang
 * @Date 2020/5/18 17:33
 * @Version 1.0
 */
public class UserInsertException extends RuntimeException{

    public UserInsertException(String msg) {
        super(msg);
    }
}
