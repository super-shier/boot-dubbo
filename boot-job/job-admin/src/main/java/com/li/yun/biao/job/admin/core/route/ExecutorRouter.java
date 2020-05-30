package com.li.yun.biao.job.admin.core.route;

import com.li.yun.biao.job.core.biz.model.ReturnT;
import com.li.yun.biao.job.core.biz.model.TriggerParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description
 */
public abstract class ExecutorRouter {
    protected static Logger logger = LoggerFactory.getLogger(ExecutorRouter.class);

    /**
     * route address
     *
     * @param addressList
     * @return ReturnT.content=address
     */
    public abstract ReturnT<String> route(TriggerParam triggerParam, List<String> addressList);

}
