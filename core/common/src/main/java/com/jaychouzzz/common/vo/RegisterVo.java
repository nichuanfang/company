package com.jaychouzzz.common.vo;

import lombok.Data;

/**
 * @Classname RegisterVo
 * @description 注册vo
 * @Author chuanfang
 * @Date 2020/5/15 11:37
 * @Version 1.0
 */
@Data
public class RegisterVo {
    /**
     * 邮箱
     */
    private String mail;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 公司名称
     */
    private String companyName;
}
