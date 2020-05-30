package com.li.yun.biao.job.core.biz.client;

import com.li.yun.biao.job.core.biz.model.HandleCallbackParam;
import com.li.yun.biao.job.core.biz.model.RegistryParam;
import com.li.yun.biao.job.core.biz.AdminBiz;
import com.li.yun.biao.job.core.biz.model.ReturnT;
import com.li.yun.biao.job.core.util.JobRemotingUtil;

import java.util.List;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description AdminBizClient
 */
public class AdminBizClient implements AdminBiz {

    public AdminBizClient() {
    }

    public AdminBizClient(String addressUrl, String accessToken) {
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
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList) {
        return JobRemotingUtil.postBody(addressUrl + "api/callback", accessToken, timeout, callbackParamList, String.class);
    }

    @Override
    public ReturnT<String> registry(RegistryParam registryParam) {
        return JobRemotingUtil.postBody(addressUrl + "api/registry", accessToken, timeout, registryParam, String.class);
    }

    @Override
    public ReturnT<String> registryRemove(RegistryParam registryParam) {
        return JobRemotingUtil.postBody(addressUrl + "api/registryRemove", accessToken, timeout, registryParam, String.class);
    }

}
