package com.jaychouzzz.security.support;

import com.jaychouzzz.security.component.SmsCodeChecker;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @Classname SmsAuthenticationProvider
 * @description 验证码认证逻辑
 * @Author chuanfang
 * @Date 2020/6/9 15:59
 * @Version 1.0
 */
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private SmsCodeChecker smsCodeChecker;


//    private AccountChecker accountChecker;

    public SmsAuthenticationProvider(SmsCodeChecker smsCodeChecker) {
        this.smsCodeChecker = smsCodeChecker;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //验证码检测
        smsCodeCheck((PhoneSmsCodeAuthenticationToken) authentication);

        //获取账号
        UserDetails userDetails = obtainAccount((String) authentication.getPrincipal());

        //检测账号存在性 可用性
        accountCheck((String) authentication.getPrincipal());

        return createSuccessAuthentication();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneSmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * 账号检测
     *
     * @param phone 电话
     * @return 验真值
     */
    private Boolean accountCheck(String phone) {
        return true;
    }

    /**
     * 验证码检测
     *
     * @param token 票据
     * @return 验真值
     */
    private Boolean smsCodeCheck(PhoneSmsCodeAuthenticationToken token) {
        return smsCodeChecker.check(token);
    }

    private Authentication createSuccessAuthentication() {
        return null;
    }

    private UserDetails obtainAccount(String phone) {
        return null;
    }

    public void setSmsCodeChecker(SmsCodeChecker smsCodeChecker) {
        this.smsCodeChecker = smsCodeChecker;
    }

    public SmsCodeChecker getSmsCodeChecker() {
        return smsCodeChecker;
    }

}
