package com.jaychouzzz.security.component;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.jaychouzzz.common.constants.AuthorizeType;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname AuthenticationController
 * @description 认证控制器
 * @Author chuanfang
 * @Date 2020/5/14 14:38
 * @Version 1.0
 */
@Controller
@AllArgsConstructor
public class AuthenticationController {

    private OAuth2ClientProperties oAuth2ClientProperties;

    @RequestMapping(value = "/form/authentication")
    public String handleLoginPage() {
        return "signin";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/errorPage")
    public String errorPage(String msg, ModelMap map) {
        map.put("msg",msg);
        return "error_page";
    }

    @RequestMapping(value = "/authorize/token",method = RequestMethod.GET)
    public void authorize(@RequestParam("code")String code,String state) {
        String basePath = "";
        switch (state) {
            case AuthorizeType.GITHUB: {
                basePath = "https://github.com/login/oauth/access_token";
                break;
            }
            default:break;
        }
        //拼接url 通过此code去申请访问票据
        HttpRequest request = HttpUtil.createRequest(Method.POST, basePath);
        //x-www-form-urlencoded数据
        HashMap<String, Object> formMap = new HashMap<>();
        OAuth2ClientProperties.Registration registration = oAuth2ClientProperties.getRegistration().get(state);
        formMap.put("client_id",registration.getClientId());
        formMap.put("client_secret",registration.getClientSecret());
        formMap.put("code",code);
        //认证成功跳转url
        //应该新增用户
        formMap.put("redirect_uri",registration.getRedirectUri());
        //携带的随机数 防伪
        formMap.put("state","");
        request.form(formMap);
        String body = request.execute().body();
        System.out.println(body);
    }
}
