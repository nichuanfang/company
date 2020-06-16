package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname ProxyProperties
 * @description 代理配置
 * @Author chuanfang
 * @Date 2020/6/15 16:56
 * @Version 1.0
 */
@Data
public class ProxyProperties {
    /**
     * 是否开启代理
     */
    private Boolean enableProxy = false;
    /**
     * ip
     */
    private String host = "localhost";
    /**
     * 端口号
     */
    private Integer port = 1080;

}
