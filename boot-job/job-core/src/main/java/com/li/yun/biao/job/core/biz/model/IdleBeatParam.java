package com.li.yun.biao.job.core.biz.model;

import java.io.Serializable;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description
 */
public class IdleBeatParam implements Serializable {
    private static final long serialVersionUID = 42L;

    private int jobId;

    public IdleBeatParam() {
    }

    public IdleBeatParam(int jobId) {
        this.jobId = jobId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}