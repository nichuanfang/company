package com.jaychouzzz.common.vo;

import lombok.Data;

/**
 * @Classname Template
 * @description 短信模板对象
 * @Author chuanfang
 * @Date 2020/5/22 11:08
 * @Version 1.0
 */
@Data
public class Template {
    /**
     *  模板名称 必填
     */
    String name;
    /**
     * 模板内容,必填
     */
    String template;
    /**
     * 模板类型,必填
     * 取值范围为: notification (通知类短信), verification (验证码短信), marketing (营销类短信)。
     */
    String type;
    /**
     * 申请理由简述,必填
     */
    String description;
    /**
     * 已经审核通过的签名,必填
      */
    String signatureId;
}
