package com.li.yun.biao.job.core.enums;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 10:43 上午
 * @description
 */
public class RegistryConfig {

    public static final int BEAT_TIMEOUT = 30;
    public static final int DEAD_TIMEOUT = BEAT_TIMEOUT * 3;

    public enum RegistType {EXECUTOR, ADMIN}

}
