package com.jaychouzzz.biz.web.listener;

import cn.hutool.json.JSONUtil;
import com.jaychouzzz.biz.web.event.RegisterSuccessEvent;
import com.jaychouzzz.common.entity.User;
import com.qiniu.sms.SmsManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.HashMap;

/**
 * @Classname RegisterSuccessListener
 * @description 注册成功监听器
 * @Author chuanfang
 * @Date 2020/6/3 11:01
 * @Version 1.0
 */
@Component
@EnableAsync
@AllArgsConstructor
@Slf4j
public class RegisterSuccessListener {

    private SmsManager smsManager;

    /**
     * 注册成功监听器 只监听事务{@link @transtraction}相关的发布事件  如果是事务内的发布事件 则监听器要try catch  不影响主要业务 防止回滚  同时应该有个重试次数
     * @param event 事件源
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSuccessRegister(RegisterSuccessEvent event) {
        User user = JSONUtil.toBean((String) event.getSource(), User.class);
        //发送短信
        HashMap<String, String> map = new HashMap<>();
        map.put("code", user.getUsername());
        /*try {
            if (!StrUtil.isNullOrUndefined(user.getPhoneNumber())) {
                Response response = smsManager.sendMessage("1265168338410024960", new String[]{user.getPhoneNumber()}, map);
                if (response.isOK()) {
                    log.info("已向"+user.getPhoneNumber()+"发送短信");
                }
            }
        } catch (QiniuException e) {
            e.printStackTrace();
        }*/
    }
}
