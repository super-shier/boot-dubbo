package com.li.yun.biao.job.core.biz;

import com.li.yun.biao.job.core.biz.model.*;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description executorBiz
 */
public interface ExecutorBiz {

    /**
     * beat
     *
     * @return
     */
    ReturnT<String> beat();

    /**
     * idle beat
     *
     * @param idleBeatParam
     * @return
     */
    ReturnT<String> idleBeat(IdleBeatParam idleBeatParam);

    /**
     * run
     *
     * @param triggerParam
     * @return
     */
    ReturnT<String> run(TriggerParam triggerParam);

    /**
     * kill
     *
     * @param killParam
     * @return
     */
    ReturnT<String> kill(KillParam killParam);

    /**
     * log
     *
     * @param logParam
     * @return
     */
    ReturnT<LogResult> log(LogParam logParam);

}
