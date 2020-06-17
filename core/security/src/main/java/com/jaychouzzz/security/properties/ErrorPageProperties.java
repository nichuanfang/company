package com.jaychouzzz.security.properties;

import lombok.Data;

/**
 * @Classname ErrorPageProperties
 * @description 错误页面配置
 * @Author chuanfang
 * @Date 2020/6/3 13:22
 * @Version 1.0
 */
@Data
public class ErrorPageProperties {
    /**
     * 失败页面
     */
     private String errorPage = "/errorPage";
}
