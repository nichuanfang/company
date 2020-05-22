package com.jaychouzzz.sms;

import com.jaychouzzz.sms.properties.SmsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname EnableSmsAutoConfiguration
 * @description sms自动配置类
 * @Author chuanfang
 * @Date 2020/5/22 8:47
 * @Version 1.0
 */
@Configuration
@ComponentScan(basePackages = {"com.jaychouzzz.sms"})
public class EnableSmsAutoConfiguration {
}
