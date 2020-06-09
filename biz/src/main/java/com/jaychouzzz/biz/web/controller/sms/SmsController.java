package com.jaychouzzz.biz.web.controller.sms;

import com.jaychouzzz.biz.web.service.ISmsManager;
import com.qiniu.sms.SmsManager;
import lombok.AllArgsConstructor;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname SmsController
 * @description 短信验证码控制器
 * @Author chuanfang
 * @Date 2020/6/5 15:19
 * @Version 1.0
 */
@RestController
@AllArgsConstructor
public class SmsController {

    private ISmsManager smsManager;

    @RequestMapping(value = "/getValidateCode",method = RequestMethod.POST)
    public void getValidateCode(String phone) {
        //获取短信模板 (已审核)
        //每一个业务对应一个短信模板
        //根据(业务类型+业务名称)获取短信模板  业务类型<->模板类型  业务名称<->正则匹配模板名称
        // 模板名称命名规则:   businessKey*templateName        example: login*validate_code
        //
//        smsManager.sendSmsCode(phone);
    }

}
