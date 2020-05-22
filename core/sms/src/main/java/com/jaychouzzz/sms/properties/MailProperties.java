package com.jaychouzzz.sms.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname MailProperties
 * @description 阿里云邮件读取器
 * @Author chuanfang
 * @Date 2020/5/22 15:51
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "jaychouzzz.mail")
@Component
@Data
public class MailProperties {
    /**
     * 发信地址
     */
    private String accountName;
    /**
     * 发信人昵称
     */
    private String fromAlias;
    /**
     * 地址类型
     */
    private Integer addressType;
    /**
     * 控制台创建的标签名称
     */
    private String  tagName;
    /**
     * 是否开启回信
     */
    private Boolean replyToAddress;
}
