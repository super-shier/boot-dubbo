package com.li.yun.biao.job.admin.core.alarm;

import com.li.yun.biao.job.admin.core.model.JobInfo;
import com.li.yun.biao.job.admin.core.model.JobLog;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 2:38 下午
 * @description
 */
public interface JobAlarm {

    /**
     * job alarm
     *
     * @param info
     * @param jobLog
     * @return
     */
    boolean doAlarm(JobInfo info, JobLog jobLog);

}
