package com.jaychouzzz.common.constants;

/**
 * @Classname AuditStatus
 * @description 审核状态
 * @Author chuanfang
 * @Date 2020/6/5 15:26
 * @Version 1.0
 */
public interface AuditStatus {
    /**
     * 已通过
     */
     String PASSED = "passed";
    /**
     * 已拒绝
     */
    String REJECTED = "rejected";
    /**
     * 正在审核
     */
    String REVIEWING = "reviewing";
}
