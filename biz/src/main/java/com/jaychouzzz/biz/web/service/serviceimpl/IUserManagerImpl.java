package com.jaychouzzz.biz.web.service.serviceimpl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.jaychouzzz.biz.web.event.RegisterSuccessEvent;
import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import com.jaychouzzz.common.enums.AccountStatus;
import com.jaychouzzz.common.exception.UserExistsException;
import com.jaychouzzz.common.exception.UserInsertException;
import com.jaychouzzz.common.vo.RegisterVo;
import com.jaychouzzz.sms.component.MailManager;
import com.qiniu.common.QiniuException;
import com.qiniu.sms.SmsManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;

/**
 * @Classname IUserManagerImpl
 * @description
 * @Author chuanfang
 * @Date 2020/5/15 14:33
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class IUserManagerImpl implements IUserManager {

    private UserMapper userMapper;

    private PasswordEncoder passwordEncoder;

    private ApplicationEventPublisher eventPublisher;

    @Override
    public void createAccount(User user) {
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public String register(RegisterVo registerVo, String token, HttpServletResponse response) {
        //1.创建用户实体  (创建时间 更新时间 删除标记 用户状态 版本号)
        token = Base64.decodeStr(ReUtil.delPre("Basic ", token));
        String username = StrUtil.split(token, ":")[0];
        String password = StrUtil.split(token, ":")[1];
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(registerVo.getPhone());
        user.setCompanyName(registerVo.getCompanyName());
        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setMail(registerVo.getMail());
        //pkId由发号器模块发放
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        user.setDeleteDate(null);
        //0表示未删除
        user.setDeleteFlag(0);
        //版本号
        user.setRecordVersion(0L);
        //2.存储用户
        try {
            //3.判断用户名是否存在
            if (userMapper.selectByUserName(username) == null) {
                log.info("开始新增用户");
                userMapper.insert(user);
                log.info("用户注册成功: " + JSONUtil.toJsonStr(user));
            }else{
                response.setStatus(HttpStatus.HTTP_INTERNAL_ERROR);
                throw new UserExistsException();
            }
        } catch (Exception e) {
            response.setStatus(HttpStatus.HTTP_INTERNAL_ERROR);
            throw new UserInsertException("Insert failed: "+e.getCause());
        }
        //4.发送短信至指定手机告知用户注册成功
        //5.创建成功跳转至登录页,失败则返回注册页,提示错误信息500服务器繁忙请稍后尝试
        /**/
        if (!StrUtil.isNullOrUndefined(registerVo.getPhone())) {
            eventPublisher.publishEvent(new RegisterSuccessEvent(JSONUtil.toJsonStr(user)));
        }
        return "signin";
    }
}
