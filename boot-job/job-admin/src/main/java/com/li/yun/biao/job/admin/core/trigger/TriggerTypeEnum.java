package com.li.yun.biao.job.admin.core.trigger;

import com.li.yun.biao.job.admin.core.util.I18nUtil;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 8:33 下午
 * @description trigger type enum
 */
public enum TriggerTypeEnum {

    MANUAL(I18nUtil.getString("jobconf_trigger_type_manual")),
    CRON(I18nUtil.getString("jobconf_trigger_type_cron")),
    RETRY(I18nUtil.getString("jobconf_trigger_type_retry")),
    PARENT(I18nUtil.getString("jobconf_trigger_type_parent")),
    API(I18nUtil.getString("jobconf_trigger_type_api"));

    private TriggerTypeEnum(String title) {
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

}
