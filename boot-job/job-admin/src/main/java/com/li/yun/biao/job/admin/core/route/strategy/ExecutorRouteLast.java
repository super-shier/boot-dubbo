package com.li.yun.biao.job.admin.core.route.strategy;

import com.li.yun.biao.job.admin.core.route.ExecutorRouter;
import com.li.yun.biao.job.core.biz.model.ReturnT;
import com.li.yun.biao.job.core.biz.model.TriggerParam;

import java.util.List;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 2:38 下午
 * @description
 */
public class ExecutorRouteLast extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        return new ReturnT<String>(addressList.get(addressList.size() - 1));
    }

}
