package com.jaychouzzz.security.manager;

import cn.hutool.core.util.ArrayUtil;
import com.jaychouzzz.security.support.SecurityAuthorityProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @Classname SecurityAuthorityManager
 * @description 权限管理器
 * @Author chuanfang
 * @Date 2020/6/11 10:46
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class SecurityAuthorityManager {

    private Map<String, SecurityAuthorityProvider> providerMap;

    /**
     * 获取所有的安全权限
     * @return 权限路径
     */
    public String[] antPatterns() {
        Set<String> keySet = providerMap.keySet();
        ArrayList<String> authorities = new ArrayList<>();
        for (String key: keySet) {
            SecurityAuthorityProvider provider = providerMap.get(key);
            authorities.addAll(provider.grantAuthority());
        }
        return ArrayUtil.toArray(authorities,String.class);
    };

}
