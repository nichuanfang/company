package com.jaychouzzz.biz.web.service.serviceimpl;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Classname IUserManagerImplTest
 * @description
 * @Author chuanfang
 * @Date 2020/5/18 16:42
 * @Version 1.0
 */
@SpringBootTest
class IUserManagerImplTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void update() {
        User user = userMapper.selectById("314803716967354368");
        user.setPhoneNumber("18326186224");
        int i = userMapper.updateById(user);
    }

}