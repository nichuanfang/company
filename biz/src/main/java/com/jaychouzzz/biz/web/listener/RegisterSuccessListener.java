package com.jaychouzzz.biz.web.listener;

import com.jaychouzzz.biz.web.event.RegisterSuccessEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @Classname RegisterSuccessListener
 * @description 注册成功监听器
 * @Author chuanfang
 * @Date 2020/6/3 11:01
 * @Version 1.0
 */
@Component
@EnableAsync // 开启异步支持
public class RegisterSuccessListener {

    /**
     * 注册成功监听器 只监听事务{@link @transtraction}相关的发布事件  如果是事务内的发布事件 则监听器要try catch  不影响主要业务 防止回滚  同时应该有个重试次数
     * @param event 事件源
     */
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSuccessRegister(RegisterSuccessEvent event) {
        System.out.println(event);
    }
}
