package com.li.yun.biao.job.admin.core.route.strategy;

import com.li.yun.biao.job.admin.core.route.ExecutorRouter;
import com.li.yun.biao.job.core.biz.model.ReturnT;
import com.li.yun.biao.job.core.biz.model.TriggerParam;

import java.util.List;
import java.util.Random;


/**
 * @author: liyunbiao
 * @date: 2020/5/28 2:38 下午
 * @description
 */
public class ExecutorRouteRandom extends ExecutorRouter {

    private static Random localRandom = new Random();

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {
        String address = addressList.get(localRandom.nextInt(addressList.size()));
        return new ReturnT<String>(address);
    }

}
