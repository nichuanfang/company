package com.jaychouzzz.biz.web.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Classname RegisterSuccessEvent
 * @description 注册成功事件
 * @Author chuanfang
 * @Date 2020/6/3 10:57
 * @Version 1.0
 */
public class RegisterSuccessEvent extends ApplicationEvent{
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public RegisterSuccessEvent(Object source) {
        super(source);
    }
}
