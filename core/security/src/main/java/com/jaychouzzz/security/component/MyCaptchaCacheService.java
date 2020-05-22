package com.jaychouzzz.security.component;

import com.anji.captcha.service.CaptchaCacheService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Classname MyCaptchaCacheService
 * @description 验证码缓存服务
 * @Author chuanfang
 * @Date 2020/5/19 15:10
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class MyCaptchaCacheService implements CaptchaCacheService {

    private RedisService redisService;

    @Override
    public void set(String s, String s1, long l) {
        redisService.set(s,s1,l);
    }

    @Override
    public boolean exists(String s) {
        return redisService.hasKey(s);
    }

    @Override
    public void delete(String s) {
        redisService.del(s);
    }

    @Override
    public String get(String s) {
        return (String) redisService.get(s);
    }
}
