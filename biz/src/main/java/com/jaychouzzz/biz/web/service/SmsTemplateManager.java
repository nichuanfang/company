package com.jaychouzzz.biz.web.service;

import com.jaychouzzz.common.enums.SmsTemplateType;
import com.qiniu.sms.SmsManager;
import com.qiniu.sms.model.TemplateInfo;

import java.util.List;

/**
 * @Classname SmsTemplateManager
 * @description 短信验证码模板管理器
 * @Author chuanfang
 * @Date 2020/6/5 15:45
 * @Version 1.0
 */
public interface SmsTemplateManager {

    /**
     * 查询指定业务范围+模板类型+审核状态的所有模板
     * @param smsBusinessScope 业务范围
     * @param templateType 模板类型
     * @param auditStatus 审核状态
     * @return 模板集合
     */
    TemplateInfo listTemplateInfo(String smsBusinessScope, SmsTemplateType templateType,String auditStatus);

    /**
     *  获取指定业务范围+模板类型+审核状态+模板名称的模板
     * @param smsBusinessScope 业务范围
     * @param templateType 模板类型
     * @param auditStatus 审核状态
     * @param templateName 模板名称
     * @return 模板id
     */
    String getTemplateId(String smsBusinessScope, SmsTemplateType templateType, String auditStatus, String templateName);
}
