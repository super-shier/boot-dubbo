package com.li.yun.biao.job.core.enums;

/**
 * Created by xuxueli on 17/5/9.
 */

import java.util.Arrays;
import java.util.Objects;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 10:43 上午
 * @description
 */
public enum ExecutorBlockStrategyEnum {

    SERIAL_EXECUTION("Serial execution"),
    /*CONCURRENT_EXECUTION("并行"),*/
    DISCARD_LATER("Discard Later"),
    COVER_EARLY("Cover Early");

    private String title;


    ExecutorBlockStrategyEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static ExecutorBlockStrategyEnum match(String name, ExecutorBlockStrategyEnum defaultItem) {
        return Arrays.stream(values()).filter(item -> Objects.equals(item.name(), name)).findFirst().orElse(defaultItem);
    }

}
