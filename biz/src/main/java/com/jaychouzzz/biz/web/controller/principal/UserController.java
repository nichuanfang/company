package com.jaychouzzz.biz.web.controller.principal;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UserController
 * @description
 * @Author chuanfang
 * @Date 2020/5/14 18:57
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public void insert() {

    }
}
