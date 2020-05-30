package com.li.yun.biao.job.core.biz.client;

import com.li.yun.biao.job.core.biz.ExecutorBiz;
import com.li.yun.biao.job.core.biz.model.*;
import com.li.yun.biao.job.core.util.JobRemotingUtil;
import lombok.NoArgsConstructor;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description admin api test
 */
@NoArgsConstructor
public class ExecutorBizClient implements ExecutorBiz {


    public ExecutorBizClient(String addressUrl, String accessToken) {
        this.addressUrl = addressUrl;
        this.accessToken = accessToken;

        // valid
        if (!this.addressUrl.endsWith("/")) {
            this.addressUrl = this.addressUrl + "/";
        }
    }

    private String addressUrl;
    private String accessToken;
    private int timeout = 3;


    @Override
    public ReturnT<String> beat() {
        return JobRemotingUtil.postBody(addressUrl + "beat", accessToken, timeout, null, String.class);
    }

    @Override
    public ReturnT<String> idleBeat(IdleBeatParam idleBeatParam) {
        return JobRemotingUtil.postBody(addressUrl + "idleBeat", accessToken, timeout, idleBeatParam, String.class);
    }

    @Override
    public ReturnT<String> run(TriggerParam triggerParam) {
        return JobRemotingUtil.postBody(addressUrl + "run", accessToken, timeout, triggerParam, String.class);
    }

    @Override
    public ReturnT<String> kill(KillParam killParam) {
        return JobRemotingUtil.postBody(addressUrl + "kill", accessToken, timeout, killParam, String.class);
    }

    @Override
    public ReturnT<LogResult> log(LogParam logParam) {
        return JobRemotingUtil.postBody(addressUrl + "log", accessToken, timeout, logParam, LogResult.class);
    }

}
