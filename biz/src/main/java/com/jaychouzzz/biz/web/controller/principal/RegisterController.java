package com.jaychouzzz.biz.web.controller.principal;

import com.jaychouzzz.common.vo.RegisterVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname RegisterController
 * @description 注册控制器
 * @Author chuanfang
 * @Date 2020/5/15 11:33
 * @Version 1.0
 */
@Controller
public class RegisterController {

    @RequestMapping(value = "/handleRegister",method = RequestMethod.POST)
    public String register(RegisterVo registerVo) {
        //1.创建用户实体  (创建时间 更新时间 删除标记 用户状态 版本号)
        //2.存储用户
        //3.发送邮件至指定邮箱告知用户注册成功
        //4.发送短信至指定手机告知用户注册成功
        //5.创建成功跳转至登录页,失败则返回注册页,提示错误信息500服务器繁忙请稍后尝试
        return "register";
    }
}
