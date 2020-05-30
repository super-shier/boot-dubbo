package com.li.yun.biao.quartz.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: liyunbiao
 * @date: 2020/1/17 12:23 下午
 * @description
 */
@Data
public class JobEntity {
    private Integer id;
    /**
     * 名称
     */
    private String jobName;
    /**
     * 执行参数
     */
    private String jobData;
    /**
     * 类名
     */
    private String jobClassName;
    /**
     * 组名
     */
    private String jobGroup;
    /**
     * 时间表达式
     */
    private String cron;
    /**
     * 触发器
     */
    private String trigger;
    /**
     * 触发器组
     */
    private String triggerGroup;
    /**
     * 任务状态
     */
    private Integer status;
    /**
     * 任务描述
     */
    private String description;
    /**
     * 操作者
     */
    private String operator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
