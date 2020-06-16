package com.jaychouzzz.security.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Classname IClientHttpRequestInterceptor
 * @description  拦截处理通过restTemplate发送的请求
 * @Author chuanfang
 * @Date 2020/6/16 13:58
 * @Version 1.0
 */
@Component
@Slf4j
public class IClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        log.info(request.getURI().getPath());
        return execution.execute(request,body);
    }
}
