package com.jaychouzzz.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Classname BaseEntity
 * @description 实体基类
 * @Author chuanfang
 * @Date 2020/5/14 15:36
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 3693222916620093774L;
    /**
     * 主键 基于雪花算法模块生成id
     */
    @TableId(type = IdType.ASSIGN_ID,value = "PK_ID")
    private String pkId;
    /**
     * 创建时间
     */
    @TableField("CREATE_DATE")
    private Date createDate;
    /**
     * 更新时间
     */
    @TableField("UPDATE_DATE")
    private Date updateDate;
    /**
     * 删除时间
     */
    @TableField("DELETE_DATE")
    private Date deleteDate;
    /**
     * 删除标记 0未删除 1已删除
     */
    @TableField("DELETE_FLAG")
    @TableLogic(value = "0",delval = "1")
    private Integer deleteFlag;
    /**
     * 版本号
     */
    @TableField("RECORD_VERSION")
    @Version
    private Long recordVersion;
}
