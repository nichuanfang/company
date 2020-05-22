package com.jaychouzzz.biz.web.service.serviceimpl;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.transform.UnmarshallerContext;
import com.jaychouzzz.biz.web.mapper.UserMapper;
import com.jaychouzzz.biz.web.service.IUserManager;
import com.jaychouzzz.common.entity.User;
import com.jaychouzzz.common.enums.AccountStatus;
import com.jaychouzzz.common.exception.UserInsertException;
import com.jaychouzzz.common.vo.RegisterVo;
import com.jaychouzzz.sequence.sequence.Sequence;
import com.jaychouzzz.sms.component.MailManager;
import com.qiniu.sms.SmsManager;
import com.qiniu.sms.model.TemplateInfo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

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

    private SmsManager  smsManager;

    private MailManager mailManager;

    @Override
    public void createAccount(User user) {
        userMapper.insert(user);
    }

    @Override
    public String register(RegisterVo registerVo, String token) {
        //1.创建用户实体  (创建时间 更新时间 删除标记 用户状态 版本号)
        token = Base64.decodeStr(ReUtil.delPre("Basic ",token));
        String username = StrUtil.split(token,":")[0];
        String password = StrUtil.split(token,":")[1];
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
            log.info("开始新增用户");
            userMapper.insert(user);
            log.info("用户注册成功:"+ JSONUtil.toJsonStr(user));
        } catch (Exception e) {
            throw new UserInsertException("新增用户异常:"+e.getLocalizedMessage());
        }
        //3.发送邮件至指定邮箱告知用户注册成功
//        AcsResponse mailResponse = mailManager.sendSingleMail("1290274972@qq.com", "注册测试", "test-test-test");
        //4.发送短信至指定手机告知用户注册成功

        //5.创建成功跳转至登录页,失败则返回注册页,提示错误信息500服务器繁忙请稍后尝试

        return "signin";
    }
}
