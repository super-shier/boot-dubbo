package com.li.yun.biao.redis.lock;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 为了可以以后扩展为使用其他方式来实现分布式锁，定义接口
 */
public interface IDistributedLock {
    static final long TIMEOUT_MILLIS = 5000;

    static final int RETRY_TIMES = Integer.MAX_VALUE;

    static final long SLEEP_MILLIS = 500;

    boolean lock(String key);

    boolean lock(String key, int retryTimes);

    boolean lock(String key, int retryTimes, long sleepMillis);

    boolean lock(String key, long expire);

    boolean lock(String key, long expire, int retryTimes);

    boolean lock(String key, long expire, int retryTimes, long sleepMillis);

    boolean releaseLock(String key);
}
