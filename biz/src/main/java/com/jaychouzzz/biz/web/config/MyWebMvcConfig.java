package com.jaychouzzz.biz.web.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname MyWebMvcConfig
 * @description
 * @Author chuanfang
 * @Date 2020/5/14 18:05
 * @Version 1.0
 */
@Component
public class MyWebMvcConfig implements WebMvcConfigurer {


    /**
     * 处理静态资源
     * @param registry 静态资源注册器
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
