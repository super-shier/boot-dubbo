package com.li.yun.biao.job.core.handler.impl;

import com.li.yun.biao.job.core.log.JobLogger;
import com.li.yun.biao.job.core.biz.model.ReturnT;
import com.li.yun.biao.job.core.handler.IJobHandler;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 10:43 上午
 * @description glue job handler
 */
public class GlueJobHandler extends IJobHandler {

    private long glueUpdatetime;
    private IJobHandler jobHandler;

    public GlueJobHandler(IJobHandler jobHandler, long glueUpdatetime) {
        this.jobHandler = jobHandler;
        this.glueUpdatetime = glueUpdatetime;
    }

    public long getGlueUpdatetime() {
        return glueUpdatetime;
    }

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        JobLogger.log("----------- glue.version:" + glueUpdatetime + " -----------");
        return jobHandler.execute(param);
    }

}
