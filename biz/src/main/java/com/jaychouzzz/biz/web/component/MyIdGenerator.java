package com.jaychouzzz.biz.web.component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.jaychouzzz.sequence.builder.SeqBuilder;
import com.jaychouzzz.sequence.sequence.Sequence;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Classname MyIdGenerator
 * @description 自定义id生成策略
 * @Author chuanfang
 * @Date 2020/5/18 16:08
 * @Version 1.0
 */
@Component
@AllArgsConstructor
public class MyIdGenerator implements IdentifierGenerator {

    private Sequence sequence;

    @Override
    public Number nextId(Object entity) {
        return sequence.nextValue();
    }
}
