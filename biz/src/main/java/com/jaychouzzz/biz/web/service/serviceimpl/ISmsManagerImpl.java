package com.jaychouzzz.biz.web.service.serviceimpl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.jaychouzzz.biz.web.config.MyUserDetailsService;
import com.jaychouzzz.biz.web.service.ISmsManager;
import com.jaychouzzz.biz.web.service.SmsTemplateManager;
import com.jaychouzzz.common.constants.AuditStatus;
import com.jaychouzzz.common.constants.SmsBusinessScope;
import com.jaychouzzz.common.enums.SmsTemplateType;
import com.jaychouzzz.common.utils.WebUtils;
import com.jaychouzzz.utils.LockUtils;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.sms.SmsManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ISmsManagerImpl
 * @description
 * @Author chuanfang
 * @Date 2020/6/8 16:08
 * @Version 1.0
 */
@Service
@AllArgsConstructor
@Slf4j
public class ISmsManagerImpl implements ISmsManager {

    private SmsTemplateManager templateManager;

    private SmsManager smsManager;

    private LockUtils lockUtils;

    private RedisTemplate redisTemplate;

    private MyUserDetailsService myUserDetailsService;


    @Override
    public void sendSmsCode(String phone) {
        //判断账户存在性可用性
        User user = myUserDetailsService.loadUserByPhone(phone);
        if(user==null) {
            throw new InternalAuthenticationServiceException("user is not exist!");
        }
        String code = "" + RandomUtil.randomString(RandomUtil.BASE_NUMBER, 6);
        lockUtils.commonBusiness(() -> {
            redisTemplate.opsForValue().set(phone, code, 60, TimeUnit.SECONDS);
        }, code);
        //发送短信
        try {
            HashMap<String, String> params = new HashMap<>();
            params.put("code", code);
            String templateId = templateManager.getTemplateId(SmsBusinessScope.LOGIN, SmsTemplateType.VERIFICATION, AuditStatus.PASSED, "validate_code");
            smsManager.sendMessage(templateId, new String[]{phone}, params);
            log.info("短信验证码发送成功!");
        } catch (QiniuException e) {
            //短信发送失败要移除redis
            lockUtils.commonBusiness(() -> {
                redisTemplate.opsForValue().set(phone, "");
            }, code);
            HttpServletResponse response = WebUtils.obtainHttpServletResponse();
            response.setStatus(HttpStatus.HTTP_INTERNAL_ERROR);
        }
    }
}
