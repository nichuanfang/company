package com.jaychouzzz.sms.config;

import com.jaychouzzz.sms.properties.SmsProperties;
import com.qiniu.sms.SmsManager;
import com.qiniu.util.Auth;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname SmsBeanConfig
 * @description 组件配置
 * @Author chuanfang
 * @Date 2020/5/22 10:17
 * @Version 1.0
 */
@Configuration
public class SmsBeanConfig {


    @Bean
    @ConditionalOnMissingBean(SmsManager.class)
    public SmsManager smsManager(SmsProperties smsProperties) {
        Auth auth = Auth.create(smsProperties.getAccessKey(),smsProperties.getSecretKey());
        return new SmsManager(auth,configuration(smsProperties));
    }

    @Bean
    public com.qiniu.sms.Configuration configuration(SmsProperties smsProperties) {
        com.qiniu.sms.Configuration configuration = new com.qiniu.sms.Configuration();
        //连接超时配置
        configuration.connectTimeout = smsProperties.getConnectTimeout();
        //底层HTTP库中复用连接对象的最大空闲数量配置
        configuration.connectionPoolMaxIdleCount = smsProperties.getConnectionPoolMaxIdleCount();
        //底层HTTP库中复用连接对象的回收周期（单位分钟）配置
        configuration.connectionPoolMaxIdleMinutes = smsProperties.getConnectionPoolMaxIdleMinutes();
        //底层HTTP库所有的并发执行的请求数量
        configuration.dispatcherMaxRequests = smsProperties.getDispatcherMaxRequests();
        //底层HTTP库对每个独立的Host进行并发请求的数量
        configuration.dispatcherMaxRequestsPerHost = smsProperties.getDispatcherMaxRequestsPerHost();
        //回复超时时间 单位秒(默认30s)
        configuration.readTimeout = smsProperties.getReadTimeout();
        //上传失败重试次数
        configuration.retryMax = smsProperties.getRetryMax();
        //写超时时间 单位秒(默认 0 , 不超时)
        configuration.writeTimeout = smsProperties.getWriteTimeout();
        return configuration;
    }

}
