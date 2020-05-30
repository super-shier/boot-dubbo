package com.li.yun.biao.quartz.util;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.quartz.entity.JobEntity;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author: liyunbiao
 * @date: 2020/1/17 11:04 上午
 * @description
 */
public class JobSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobSchedule.class);
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    private JobSchedule() {
    }

    /**
     * 添加定时任务
     *
     * @param jobEntity
     * @throws SchedulerException
     */
    public static void addJob(JobEntity jobEntity) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        try {
            Class jobClass = Class.forName(jobEntity.getJobClassName());
            jobClass.newInstance();

            JobDataMap jobDataMap = !StringUtils.isEmpty(jobEntity.getJobData()) ?
                    JSON.parseObject(jobEntity.getJobData(), JobDataMap.class) : new JobDataMap();
            // 用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
            JobDetail jobDetail = JobBuilder.newJob(jobClass).
                    withIdentity(jobEntity.getJobName(), jobEntity.getJobGroup()).
                    setJobData(jobDataMap).build();

            // 构建一个触发器，规定触发的规则
            Trigger trigger = TriggerBuilder.newTrigger()// 创建一个新的TriggerBuilder来规范一个触发器
                    .withIdentity(jobEntity.getTrigger(), jobEntity.getTriggerGroup())// 给触发器起一个名字和组名
                    .startNow()// 立即执行
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobEntity.getCron())) // 触发器的执行时间
                    .build();// 产生触发器

            scheduler.scheduleJob(jobDetail, trigger);
            LOGGER.info("添加任务:{},{},{},{},{},{}", jobEntity.getJobName(), jobEntity.getJobGroup(), jobEntity.getTrigger(),
                    jobEntity.getTriggerGroup(), jobEntity.getCron(), jobEntity.getJobClassName());
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改定时任务
     *
     * @param jobEntity
     * @throws SchedulerException
     */
    public static void modifyJobTime(JobEntity jobEntity) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        TriggerKey triggerKey = new TriggerKey(jobEntity.getTrigger(), jobEntity.getTriggerGroup());
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            LOGGER.info("********查询失败");
            return;
        }
        String oldTime = trigger.getCronExpression();
        LOGGER.info("********oldTime:{},newTime:{}", oldTime, jobEntity.getCron());
        if (!oldTime.equalsIgnoreCase(jobEntity.getCron())) {
            removeJob(jobEntity.getJobName(), jobEntity.getTrigger(), jobEntity.getTriggerGroup());
            addJob(jobEntity);
        }
    }

    /**
     * 移除定时任务
     *
     * @param jobName
     * @param trigger
     * @param triggerGroup
     * @throws SchedulerException
     */
    public static void removeJob(String jobName, String trigger, String triggerGroup) throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobKey jobKey = new JobKey(jobName, triggerGroup);
        // 停止触发器
        scheduler.pauseJob(jobKey);
        scheduler.unscheduleJob(new TriggerKey(trigger, triggerGroup));// 移除触发器
        scheduler.deleteJob(jobKey);// 删除任务
        LOGGER.info("移除任务:{}", jobName);
    }


    /**
     * 启动所有任务
     *
     * @throws SchedulerException
     */
    public static void startJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start();
        LOGGER.debug("启动所有任务");
    }

    /**
     * 关闭所有定时任务
     *
     * @throws SchedulerException
     */
    public static void shutdownJobs() throws SchedulerException {
        Scheduler scheduler = schedulerFactory.getScheduler();
        if (!scheduler.isShutdown()) {
            scheduler.shutdown();
            LOGGER.debug("关闭所有任务");
        }
    }

}
