package com.jaychouzzz.biz;

import com.anji.captcha.config.BeanAutoConfig;
import com.anji.captcha.service.CaptchaService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.jaychouzzz.biz.web.mapper"})
public class BizApplication {

    public static void main(String[] args) {
        SpringApplication.run(BizApplication.class, args);
    }

}
