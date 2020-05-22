package com.jaychouzzz.sms.properties;

import com.jaychouzzz.common.vo.Signature;
import com.jaychouzzz.common.vo.Template;
import com.qiniu.common.Constants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @Classname SmsProperties
 * @description 短信配置
 * @Author chuanfang
 * @Date 2020/5/22 10:20
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "jaychouzzz.sms")
@Component
@Data
public class SmsProperties {
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 密钥
     */
    private String secretKey;
    /**
     * 短信发送状态的回调地址
     */
    private String sendStatusUrl;
    /**
     * 用户回复短信时的回调地址
     */
    private String upStatusUrl;
    /**
     * 连接超时时间 单位秒(默认10s)
     */
    private Integer connectTimeout = Constants.CONNECT_TIMEOUT;
    /**
     * 写超时时间 单位秒(默认 0 , 不超时)
     */
    private Integer writeTimeout = Constants.WRITE_TIMEOUT;
    /**
     * 回复超时时间 单位秒(默认30s)
     */
    private Integer readTimeout = Constants.READ_TIMEOUT;
    /**
     * 底层HTTP库所有的并发执行的请求数量
     */
    private Integer dispatcherMaxRequests = Constants.DISPATCHER_MAX_REQUESTS;
    /**
     * 底层HTTP库对每个独立的Host进行并发请求的数量
     */
    private Integer dispatcherMaxRequestsPerHost = Constants.DISPATCHER_MAX_REQUESTS_PER_HOST;
    /**
     * 底层HTTP库中复用连接对象的最大空闲数量
     */
    private Integer connectionPoolMaxIdleCount = Constants.CONNECTION_POOL_MAX_IDLE_COUNT;
    /**
     * 底层HTTP库中复用连接对象的回收周期（单位分钟）
     */
    private Integer connectionPoolMaxIdleMinutes = Constants.CONNECTION_POOL_MAX_IDLE_MINUTES;
    /**
     * 上传失败重试次数
     */
    public Integer retryMax = 5;
    /**
     * 自定义短信签名  只要该属性定义好了就会走审核流程
     */
    @NestedConfigurationProperty
    private Set<Signature> signatures;
    /**
     * 自定义短信模板  前提时签名已定义+该属性定义好了就会走审核流程
     */
    @NestedConfigurationProperty
    private Set<Template> templates;
}
