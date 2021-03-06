package com.jaychouzzz.security.support;

import java.util.List;

/**
 * @Classname SecurityAuthorityProvider
 * @description 安全权限提供商  开发一个注解 controller方法上添加此注解后 路径会添加到此provider中
 * @Author chuanfang
 * @Date 2020/6/11 10:54
 * @Version 1.0
 */
public interface SecurityAuthorityProvider {

    /**
     * 授权
     * @return 权限集合
     */
    List<String> grantAuthority();
}
