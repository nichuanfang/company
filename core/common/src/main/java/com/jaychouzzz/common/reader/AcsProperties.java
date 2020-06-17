package com.jaychouzzz.common.reader;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname AcsProperties
 * @description 阿里云账号配置
 * @Author chuanfang
 * @Date 2020/5/22 16:06
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "jaychouzzz.ali")
@Component
@Data
public class AcsProperties {
    /**
     * 访问key
     */
    private String accessKey;
    /**
     * 访问密钥
     */
    private String accessSecret;
    /**
     * 所属区域  杭州/新加坡/澳洲
     */
    private String region;
}
