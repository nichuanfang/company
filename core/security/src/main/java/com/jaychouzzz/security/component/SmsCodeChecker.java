package com.jaychouzzz.security.component;

import cn.hutool.core.util.StrUtil;
import com.jaychouzzz.security.exception.SmsAuthenticationException;
import com.jaychouzzz.security.support.PhoneSmsCodeAuthenticationToken;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @Classname SmsCodeChecker
 * @description 验证码校验器
 * @Author chuanfang
 * @Date 2020/6/9 17:14
 * @Version 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class SmsCodeChecker {

    private RedisService redisService;

    public void check(PhoneSmsCodeAuthenticationToken token){
        String phone = (String) token.getPrincipal();
        String smsCode = (String) token.getCredentials();

        boolean flag;
        log.info("---Verification code verification starts---");
        //检测redis中是否有此key(phone) 或者 过期
        flag = redisService.hasKey(phone);
        if(!flag) {
            log.error("Verification code does not exist or has expired");
            throw new SmsAuthenticationException("Verification code does not exist or has expired");
        }
        //检测key对应的value是否正确
        String value = (String) redisService.get(phone);
        if(!StrUtil.equalsIgnoreCase(smsCode, value)) {
            log.error("Incorrect verification code");
            throw new SmsAuthenticationException("Incorrect verification code");
        }
    }

}
