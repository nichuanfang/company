package com.jaychouzzz.biz.web.service.serviceimpl;

import com.jaychouzzz.common.constants.AuditStatus;
import com.jaychouzzz.common.constants.SmsBusinessScope;
import com.jaychouzzz.common.enums.SmsTemplateType;
import com.qiniu.sms.SmsManager;
import com.qiniu.sms.model.TemplateInfo;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


/**
 * @Classname SmsTemplateManagerImplTest
 * @description
 * @Author chuanfang
 * @Date 2020/6/5 16:05
 * @Version 1.0
 */
@SpringBootTest
@Rollback
@Slf4j
class SmsTemplateManagerImplTest {

    @Autowired
    private SmsTemplateManagerImpl smsTemplateManager;

    @Autowired
    private SmsManager smsManager;

    @Test
    @SneakyThrows
    void getTemplateInfo() {
        TemplateInfo templateInfo = smsManager.describeTemplateItems(AuditStatus.REVIEWING,1,1);
        System.out.println(templateInfo);
    }

    @Test
    public void getAllTemplates() {
//        TemplateInfo templateInfo = smsTemplateManager.getTemplateInfo(AuditStatus.PASSED);
//        TemplateInfo templateInfo = smsTemplateManager.getTemplateInfo(AuditStatus.REVIEWING);
        /*TemplateInfo templateInfo = null;
        try {
            templateInfo = smsManager.describeTemplateItems(AuditStatus.PASSED, -1, 1);
        } catch (QiniuException e) {
            e.printStackTrace();
        }*/
//        System.out.println(templateInfo);
    }

    @Test
    public void listTemplateInfo() {
        TemplateInfo templateInfo = smsTemplateManager.listTemplateInfo(SmsBusinessScope.LOGIN, SmsTemplateType.VERIFICATION, AuditStatus.PASSED);
    }

    @Test
    public void getTemplateId() {
        String id = smsTemplateManager.getTemplateId(SmsBusinessScope.LOGIN, SmsTemplateType.VERIFICATION, AuditStatus.PASSED, "validate_code");
    }
}