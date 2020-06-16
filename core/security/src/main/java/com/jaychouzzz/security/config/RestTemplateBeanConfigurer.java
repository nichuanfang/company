package com.jaychouzzz.security.config;

import com.jaychouzzz.security.properties.ProxyProperties;
import com.jaychouzzz.security.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.List;

/**
 * @Classname RestTemplateBeanConfigurer
 * @description restTemplate配置 代理配置
 * @Author chuanfang
 * @Date 2020/6/15 17:00
 * @Version 1.0
 */
@Configuration
public class RestTemplateBeanConfigurer {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory, List<ClientHttpRequestInterceptor> interceptors) {
        RestTemplate template = new RestTemplate(factory);
        template.setInterceptors(interceptors);
        return template;
    }

    @Bean
    public ClientHttpRequestFactory clientHttpRequestFactory(SecurityProperties securityProperties) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        //是否启用了proxy
        if(securityProperties.getProxy().getEnableProxy()) {
            SocketAddress socketAddress = new InetSocketAddress(securityProperties.getProxy().getHost(),securityProperties.getProxy().getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP,socketAddress);
            factory.setProxy(proxy);
        }
        return factory;
    }


}
