package com.jaychouzzz.biz.web.controller.principal;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import com.jaychouzzz.common.enums.AccountStatus;
import com.jaychouzzz.common.vo.RegisterVo;
import com.jaychouzzz.sequence.builder.SnowflakeSeqBuilder;
import com.jaychouzzz.sequence.properties.SequenceSnowflakeProperties;
import com.jaychouzzz.sequence.sequence.Sequence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

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
        return userManager.register(registerVo,token);
    }
}
