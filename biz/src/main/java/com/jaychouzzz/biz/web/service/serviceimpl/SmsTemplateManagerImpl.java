package com.jaychouzzz.biz.web.service.serviceimpl;

import cn.hutool.core.util.StrUtil;
import com.jaychouzzz.biz.web.service.SmsTemplateManager;
import com.jaychouzzz.common.enums.SignalEnum;
import com.jaychouzzz.common.enums.SmsTemplateType;
import com.qiniu.common.QiniuException;
import com.qiniu.sms.SmsManager;
import com.qiniu.sms.model.TemplateInfo;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Classname SmsTemplateManagerImpl
 * @description
 * @Author chuanfang
 * @Date 2020/6/5 15:46
 * @Version 1.0
 */
@Service
@AllArgsConstructor
public class SmsTemplateManagerImpl implements SmsTemplateManager {

    private SmsManager smsManager;

    @Lazy
    private ThreadPoolTaskExecutor executor;

    @Override
    public TemplateInfo listTemplateInfo(String smsBusinessScope, SmsTemplateType templateType, String auditStatus) {
        TemplateInfo templateInfo = getTemplateInfo(auditStatus);
        assert templateInfo != null;
        List<TemplateInfo.Item> collect = templateInfo.getItems().stream().filter(item -> (StrUtil.equalsIgnoreCase(item.getType(), templateType.getDesc()))
                && (item.getName().startsWith(smsBusinessScope + SignalEnum.ASTERISK.getValue()))).collect(Collectors.toList());
        templateInfo.getItems().clear();
        templateInfo.getItems().addAll(collect);
        return templateInfo;
    }

    @Override
    public String getTemplateId(String smsBusinessScope, SmsTemplateType templateType, String auditStatus, String templateName) {
        TemplateInfo templateInfo = listTemplateInfo(smsBusinessScope, templateType, auditStatus);
        Optional<TemplateInfo.Item> first = templateInfo.getItems().stream().filter(item -> {
            //处理模板名称
            String name = smsBusinessScope.concat(SignalEnum.ASTERISK.getValue()).concat(templateName);
            return StrUtil.equalsIgnoreCase(name,item.getName());
        }).findFirst();
        return first.map(TemplateInfo.Item::getId).orElse(null);
    }

    /**
     * 获取所有模板信息
     *
     * @param auditStatus 审核状态
     * @return 模板信息
     */
    private TemplateInfo getTemplateInfo(String auditStatus) {
        TemplateInfo templateInfo = null;
        try {
            //去掉分页  默认page 1 pageSize 20  todo
            templateInfo = smsManager.describeTemplateItems(auditStatus, -1, -1);
            if (templateInfo.getTotal() == 0) {
                return null;
            }
            //如果总数目小于等于每页的最大size 说明已经拿到所有数据
            if (templateInfo.getTotal() <= templateInfo.getPageSize()) {
                return templateInfo;
            } else {
                //如果总条目大于 先计算总页数  遍历+线程池轮询调用接口  拿到所有数据
                int totalPage = 0;
                if (((templateInfo.getTotal()) % (templateInfo.getPageSize())) == 0) {
                    totalPage = templateInfo.getTotal() / templateInfo.getPageSize();
                } else {
                    totalPage = templateInfo.getTotal() / templateInfo.getPageSize() + 1;
                }
                for (int i = templateInfo.getPage() + 1; i < totalPage + 1; i++) {
                    int[] ints = {i};
                    Future<TemplateInfo> future = executor.submit(() -> {
                        //发起查询
                        return smsManager.describeTemplateItems(auditStatus, ints[0], -1);
                    });
                    templateInfo.getItems().addAll(future.get().getItems());
                }

            }
        } catch (QiniuException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return templateInfo;
    }
}
