package com.jaychouzzz.security.support;

import com.jaychouzzz.security.component.AccountChecker;
import com.jaychouzzz.security.component.SmsCodeChecker;
import com.jaychouzzz.security.exception.SmsAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

/**
 * @Classname SmsAuthenticationProvider
 * @description 验证码认证逻辑
 * @Author chuanfang
 * @Date 2020/6/9 15:59
 * @Version 1.0
 */
@Slf4j
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private SmsCodeChecker smsCodeChecker;

    private AccountChecker accountChecker;

    public SmsAuthenticationProvider(SmsCodeChecker smsCodeChecker,AccountChecker accountChecker) {
        this.smsCodeChecker = smsCodeChecker;
        this.accountChecker = accountChecker;
    }

    @Override
    public Authentication authenticate(Authentication authentication){

        //验证码检测
        smsCodeCheck((PhoneSmsCodeAuthenticationToken) authentication);

        //获取账号+检测账号存在性 可用性
        UserDetails userDetails = obtainAccount((String) authentication.getPrincipal());
        return createSuccessAuthentication(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PhoneSmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }

    /**
     * 验证码检测
     *
     * @param token 票据
     * @return 验真值
     */
    private void smsCodeCheck(PhoneSmsCodeAuthenticationToken token){
        smsCodeChecker.check(token);
    }

    private Authentication createSuccessAuthentication(UserDetails userDetails) {
        log.info("用户"+userDetails.getUsername()+"登录成功,"+new Date());
        return new PhoneSmsCodeAuthenticationToken(userDetails,userDetails.getPassword(),userDetails.getAuthorities());
    }

    private UserDetails obtainAccount(String phone) {
        return accountChecker.obtainAndCheck(phone);
    }

    public void setSmsCodeChecker(SmsCodeChecker smsCodeChecker) {
        this.smsCodeChecker = smsCodeChecker;
    }

    public SmsCodeChecker getSmsCodeChecker() {
        return smsCodeChecker;
    }

}
