package com.jaychouzzz.common.entity;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.MybatisEnumTypeHandler;
import com.jaychouzzz.common.enums.AccountStatus;
import lombok.Data;

/**
 * @Classname User
 * @description 用户
 * @Author chuanfang
 * @Date 2020/5/14 15:36
 * @Version 1.0
 */
@Data
@TableName("USER")
public class User extends BaseEntity{

    private static final long serialVersionUID = -6192312499636458166L;
    /**
     * 用户名
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 密码
     */
    @TableField("PASSWORD")
    private String password;
    /**
     * 手机号
     */
    @TableField("PHONE_NUMBER")
    private String phoneNumber;
    /**
     * 公司名称
     */
    @TableField("COMPANY_NAME")
    private String companyName;
    /**
     * 是否可用
     */
    @TableField(value = "ACCOUNT_STATUS")
    private AccountStatus accountStatus;
}
