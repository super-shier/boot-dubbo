package com.li.yun.biao.job.core.biz;

import com.li.yun.biao.job.core.biz.model.HandleCallbackParam;
import com.li.yun.biao.job.core.biz.model.RegistryParam;
import com.li.yun.biao.job.core.biz.model.ReturnT;

import java.util.List;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description adminBiz
 */
public interface AdminBiz {


    /**
     * callback
     *
     * @param callbackParamList
     * @return
     */
    ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);

    /**
     * registry
     *
     * @param registryParam
     * @return
     */
    ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     *
     * @param registryParam
     * @return
     */
    ReturnT<String> registryRemove(RegistryParam registryParam);


}
