package com.jaychouzzz.biz.web.controller.principal;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import com.jaychouzzz.common.enums.AccountStatus;
import com.jaychouzzz.common.vo.RegisterVo;
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
        //1.创建用户实体  (创建时间 更新时间 删除标记 用户状态 版本号)
        token = Base64.decodeStr(ReUtil.delPre("Basic ",token));
        String username = StrUtil.split(token,":")[0];
        String password = StrUtil.split(token,":")[1];
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhoneNumber(registerVo.getPhone());
        user.setCompanyName(registerVo.getCompanyName());
        user.setAccountStatus(AccountStatus.ACTIVE);
        //pkId由发号器模块发放
        user.setPkId(12);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setDeleteDate(null);
        //0表示未删除
        user.setDeleteFlag(0);
        //版本号
        user.setRecordVersion(0L);
        //2.存储用户


        //3.发送邮件至指定邮箱告知用户注册成功

        //4.发送短信至指定手机告知用户注册成功

        //5.创建成功跳转至登录页,失败则返回注册页,提示错误信息500服务器繁忙请稍后尝试

        return "";
    }
}
