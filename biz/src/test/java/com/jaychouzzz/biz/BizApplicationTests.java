package com.jaychouzzz.biz;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootTest
class BizApplicationTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        String encode = passwordEncoder.encode("123456");
        User user = new User();
        user.setUsername("root");
        user.setPassword(encode);
        user.setCompanyName("znjt");
        user.setPhoneNumber("18326186224");
        user.setPkId("");
        userMapper.insert(user);
    }

}
