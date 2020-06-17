package com.jaychouzzz.common.component;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.jaychouzzz.common.constants.Region;
import com.jaychouzzz.common.reader.AcsProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Classname AcsClientManager
 * @description 阿里云账号管理器
 * @Author chuanfang
 * @Date 2020/5/22 16:04
 * @Version 1.0
 */
public abstract class AcsClientManager {

    /**
     * 获取阿里云http客户端
     * @return http客户端
     */
    public IAcsClient getClient(AcsProperties acsProperties) {
        // 如果是除杭州region外的其它region（如新加坡region）， 需要做如下处理
        if (!StrUtil.equalsIgnoreCase(acsProperties.getRegion(), Region.CN_HANGZHOU)) {
            String path = StrUtil.addSuffixIfNot(StrUtil.addPrefixIfNot(acsProperties.getRegion(),"dm."),".aliyuncs.com");
            try {
                DefaultProfile.addEndpoint(path, acsProperties.getRegion(), "Dm", path);
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
        // 如果是除杭州region外的其它region（如新加坡、澳洲Region），需要将下面的”cn-hangzhou”替换为”ap-southeast-1”、或”ap-southeast-2”。
        IClientProfile profile = DefaultProfile.getProfile(acsProperties.getRegion(),acsProperties.getAccessKey(),acsProperties.getAccessSecret());
        return new DefaultAcsClient(profile);
    }


}
