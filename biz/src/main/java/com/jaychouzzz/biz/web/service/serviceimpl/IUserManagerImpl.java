package com.jaychouzzz.biz.web.service.serviceimpl;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Classname IUserManagerImpl
 * @description
 * @Author chuanfang
 * @Date 2020/5/15 14:33
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class IUserManagerImpl implements IUserManager {

    private UserMapper userMapper;

    @Override
    public void createAccount(User user) {
        userMapper.insert(user);
    }
}
