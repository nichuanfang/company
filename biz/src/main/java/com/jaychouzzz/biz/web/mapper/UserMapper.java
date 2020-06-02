package com.jaychouzzz.biz.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jaychouzzz.common.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname UserMapper
 * @description 用户映射
 * @Author chuanfang
 * @Date 2020/5/14 16:10
 * @Version 1.0
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过用户名查询用户信息
     * @param username 用户名
     * @return 用户实体
     */
    @Select("SELECT * FROM USER WHERE USERNAME = #{username}")
    User selectByUserName(@Param("username") String username);
}
