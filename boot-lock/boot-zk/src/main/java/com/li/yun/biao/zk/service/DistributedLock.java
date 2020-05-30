package com.li.yun.biao.zk.service;

import java.util.concurrent.TimeUnit;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 10:04 上午
 * @description 分配锁接口
 */
public interface DistributedLock {

    /*
     * 获取锁，如果没有得到就等待
     */
    void acquire() throws Exception;

    /*
     * 获取锁，直到超时
     */
    boolean acquire(long time, TimeUnit unit) throws Exception;

    /*
     * 释放锁
     */
    void release() throws Exception;


}
