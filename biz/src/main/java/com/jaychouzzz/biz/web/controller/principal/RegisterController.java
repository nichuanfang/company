package com.jaychouzzz.biz.web.controller.principal;

import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.vo.RegisterVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    @ResponseBody
    public String register(RegisterVo registerVo, @RequestHeader("Authentication")String token, HttpServletResponse response) {
        return userManager.register(registerVo,token,response);
    }
}
