package com.jaychouzzz.biz.web.service;

import com.jaychouzzz.common.entity.User;
import com.jaychouzzz.common.vo.RegisterVo;

import javax.servlet.http.HttpServletResponse;

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

    /**
     * 注册用户
     * @param registerVo 注册vo
     * @param token 用户主体
     * @param response  响应
     * @return 页面名称
     */
    String register(RegisterVo registerVo, String token, HttpServletResponse response);
}
