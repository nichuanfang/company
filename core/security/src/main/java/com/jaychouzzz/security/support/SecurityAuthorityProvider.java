package com.jaychouzzz.security.support;

import java.util.List;

/**
 * @Classname SecurityAuthorityProvider
 * @description 安全权限提供商
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
