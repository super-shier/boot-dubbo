package com.li.yun.biao.quartz.job;

import com.alibaba.fastjson.JSON;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: liyunbiao
 * @date: 2020/1/17 11:01 上午
 * @description
 */
public class HelloJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LOGGER.info(context.getScheduler().getSchedulerName());
            JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
            LOGGER.info("********jobDataMap:{}", JSON.toJSONString(jobDataMap));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
