package com.jaychouzzz.common.enums;

import lombok.Getter;

/**
 * @Classname AccountStatus
 * @description 用户状态枚举
 * @Author chuanfang
 * @Date 2020/5/15 13:49
 * @Version 1.0
 */
@Getter
public enum AccountStatus {
    /**
     * 激活
     */
    ACTIVE("激活",1),
    /**
     * 账号被锁定
     */
    LOCKED("锁定",2),
    /**
     * 账号已过期
     */
    EXPIRED("过期",0);
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 信息
     */
    private String msg;

    AccountStatus(String msg,Integer code) {
        this.code = code;
        this.msg = msg;
    }
}
