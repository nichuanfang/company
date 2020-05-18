package com.jaychouzzz.biz.web.controller.principal;

import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import com.jaychouzzz.common.vo.RegisterVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname RegisterController
 * @description 注册控制器
 * @Author chuanfang
 * @Date 2020/5/15 11:33
 * @Version 1.0
 */
@Controller
@AllArgsConstructor
public class RegisterController {

    private IUserManager userManager;

    @RequestMapping(value = "/handleRegister",method = RequestMethod.POST)
    public String register(RegisterVo registerVo, @RequestHeader("Authentication")String token) {
        //todo 前端用户名密码需要处理成basic头
        //1.创建用户实体  (创建时间 更新时间 删除标记 用户状态 版本号)
        /*User user = new User();
        user.setUsername();
        user.setPassword();
        user.setPhoneNumber();
        user.setCompanyName();
        user.setAccountStatus();
        user.setPkId();
        user.setCreateDate();
        user.setUpdateDate();
        user.setDeleteDate();
        user.setDeleteFlag();
        user.setRecordVersion();*/
        //2.存储用户
        //3.发送邮件至指定邮箱告知用户注册成功
        //4.发送短信至指定手机告知用户注册成功
        //5.创建成功跳转至登录页,失败则返回注册页,提示错误信息500服务器繁忙请稍后尝试
        return "";
    }
}
