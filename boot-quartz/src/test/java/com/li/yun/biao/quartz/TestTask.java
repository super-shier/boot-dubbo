package com.li.yun.biao.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.li.yun.biao.quartz.entity.JobEntity;
import com.li.yun.biao.quartz.util.JobSchedule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author: liyunbiao
 * @date: 2020/1/17 11:09 上午
 * @description
 */
public class TestTask extends BaseQuartzTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTask.class);

    @Test
    public void addJob() {
        try {
            JSONObject jobData = new JSONObject();
            jobData.put("jobData", "jobData数据");
            JobEntity job = new JobEntity();
            job.setJobName("测试任务");
            job.setJobClassName("com.li.yun.biao.quartz.job.HelloJob");
            job.setJobGroup("HELLO_GROUP");
            job.setJobData(JSON.toJSONString(jobData));
            job.setTrigger("trigger");
            job.setTriggerGroup("triggerGroup");
            job.setCron("0/1 * * * * ?");
            LOGGER.info("【系统启动】开始(每1秒输出一次)...");
            JobSchedule.addJob(job);
            Thread.sleep(5000);

            JobEntity jobEntity = new JobEntity();
            BeanUtils.copyProperties(job, jobEntity);
            jobData.put("upodate", "修改");
            jobEntity.setCron("0/2 * * * * ?");
            jobEntity.setJobData(JSON.toJSONString(jobData));
            LOGGER.info("【修改任务】开始...");
            JobSchedule.modifyJobTime(jobEntity);
            LOGGER.info("【修改任务】成功");
            Thread.sleep(10000);

            LOGGER.info("【移除定时】开始...");
            JobSchedule.removeJob(jobEntity.getJobName(), jobEntity.getTrigger(), jobEntity.getTriggerGroup());
            Thread.sleep(10000);
            LOGGER.info("【移除定时】成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
