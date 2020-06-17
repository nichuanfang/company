package com.jaychouzzz.common.utils;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Classname WebUtils
 * @description  webutils拓展
 * @Author chuanfang
 * @Date 2020/6/3 11:54
 * @Version 1.0
 */
public class WebUtils extends org.springframework.web.util.WebUtils {

    /**
     * 获取{@link HttpServletRequest}
     * @return HttpServletRequest
     */
    public static HttpServletRequest obtainHttpServletRequest() {
        return obtainRequestAttributes().getRequest();
    }

    /**
     * 获取 {@link ServletRequestAttributes}
     * @return ServletRequestAttributes
     */
    public static ServletRequestAttributes obtainRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取  {@link HttpServletResponse}
     * @return HttpServletResponse
     */
    public static HttpServletResponse obtainHttpServletResponse() {
        return obtainRequestAttributes().getResponse();
    }

    /**
     * 获取 sessionId
     * @return sessionId
     */
    public static String obtainSessionId() {
        return obtainRequestAttributes().getSessionId();
    }

    /**
     * 获取 {@link HttpSession}
     * @return HttpSession
     */
    public static HttpSession obtainSession() {
        return obtainHttpServletRequest().getSession();
    }


}
