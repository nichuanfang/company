package com.jaychouzzz.biz.web.service.serviceimpl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.HashMap;

/**
 * @Classname HttpTest
 * @description http测试
 * @Author chuanfang
 * @Date 2020/6/15 11:38
 * @Version 1.0
 */
@Slf4j
@SpringBootTest
public class HttpTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void httpTest() {
        HashMap<String, Object> formMap = new HashMap<>();
        HttpRequest httpRequest = HttpRequest.get("https://graph.facebook.com/v2.8/oauth/access_token").setFollowRedirects(true);
        //必须项  idea配置无效
        httpRequest.setProxy(new Proxy(Proxy.Type.HTTP,new InetSocketAddress("localhost",1080)));
        HttpResponse response = httpRequest.execute();
        System.out.println(response.body());
    }

    @Test
    public void restTest() {
        Object o = restTemplate.getForObject("https://www.google.com", Object.class);
        System.out.println(o);
    }

    @Test
    public void excludeClassTest() {


    }

}
