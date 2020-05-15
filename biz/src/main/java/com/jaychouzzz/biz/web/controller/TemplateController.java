package com.jaychouzzz.biz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname TemplateController
 * @description 模板控制器
 * @Author chuanfang
 * @Date 2020/5/15 9:07
 * @Version 1.0
 */
@Controller
public class TemplateController {
    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/signin")
    public String signin() {
        return "signin";
    }

    @RequestMapping(value = "/single")
    public String single() {
        return "single";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }

    @RequestMapping(value = "/blogs")
    public String blogs() {
        return "blogs";
    }

    @RequestMapping(value = "/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "/features")
    public String features() {
        return "features";
    }

    @RequestMapping(value = "/gallery")
    public String gallery() {
        return "gallery";
    }

    @RequestMapping(value = "/plans")
    public String plans() {
        return "plans";
    }
}
