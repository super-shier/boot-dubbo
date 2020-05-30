package com.li.yun.biao.job.admin.core.route.strategy;

import com.li.yun.biao.job.admin.core.route.ExecutorRouter;
import com.li.yun.biao.job.admin.core.scheduler.JobScheduler;
import com.li.yun.biao.job.admin.core.util.I18nUtil;
import com.li.yun.biao.job.core.biz.ExecutorBiz;
import com.li.yun.biao.job.core.biz.model.ReturnT;
import com.li.yun.biao.job.core.biz.model.TriggerParam;

import java.util.List;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 2:38 下午
 * @description
 */
public class ExecutorRouteFailover extends ExecutorRouter {

    @Override
    public ReturnT<String> route(TriggerParam triggerParam, List<String> addressList) {

        StringBuffer beatResultSB = new StringBuffer();
        for (String address : addressList) {
            // beat
            ReturnT<String> beatResult = null;
            try {
                ExecutorBiz executorBiz = JobScheduler.getExecutorBiz(address);
                beatResult = executorBiz.beat();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                beatResult = new ReturnT<String>(ReturnT.FAIL_CODE, "" + e);
            }
            beatResultSB.append((beatResultSB.length() > 0) ? "<br><br>" : "")
                    .append(I18nUtil.getString("jobconf_beat") + "：")
                    .append("<br>address：").append(address)
                    .append("<br>code：").append(beatResult.getCode())
                    .append("<br>msg：").append(beatResult.getMsg());

            // beat success
            if (beatResult.getCode() == ReturnT.SUCCESS_CODE) {

                beatResult.setMsg(beatResultSB.toString());
                beatResult.setContent(address);
                return beatResult;
            }
        }
        return new ReturnT<String>(ReturnT.FAIL_CODE, beatResultSB.toString());

    }
}
