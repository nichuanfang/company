package com.jaychouzzz.common.enums;

import lombok.Getter;

/**
 * @Classname SignalEnum
 * @description 特殊符号枚举
 * @Author chuanfang
 * @Date 2020/6/8 10:40
 * @Version 1.0
 */
@Getter
public enum SignalEnum {
    /**
     * 星号
     */
    ASTERISK("*","星号"),
    /**
     * 逗号
     */
    COMMA(",","逗号"),
    /**
     * 单引号
     */
    SINGLE_QUOTES("'","单引号"),
    /**
     * 分号
     */
    SEMICOLON(";","分号"),
    /**
     * 顿号
     */
    Ton("、","顿号"),
    /**
     * 冒号
     */
    COLON(":","冒号"),
    /**
     * 结束符
     */
    PERIOD(".","结束符")
    ;
    /**
     * 值
     */
    private String value;
    /**
     * 注释
     */
    private String msg;
    SignalEnum(String value,String msg) {this.value=value;this.msg=msg;}
}
