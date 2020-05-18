package com.jaychouzzz.biz.web.service;

import com.jaychouzzz.common.entity.User;

/**
 * @Classname IUserManager
 * @description 用户管理
 * @Author chuanfang
 * @Date 2020/5/15 14:33
 * @Version 1.0
 */
public interface IUserManager {
    /**
     * 创建用户
     * @param user 用户实体
     */
    public void createAccount(User user);
}
