package com.li.yun.biao.redis.annotations;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 定义分布式锁的注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DistributedLock {

    /**
     * 锁的资源，key。
     * 支持spring El表达式
     */
    @AliasFor("name")
    String name() default "'default'";

    /**
     * 锁的资源，value。
     * 支持spring El表达式
     */
    @AliasFor("value")
    String value() default "'default'";

    /**
     * 持锁时间,单位毫秒
     */
    long keepMills() default 5000;

    /**
     * 当获取失败时候动作
     */
    LockFailAction action() default LockFailAction.CONTINUE;

    public enum LockFailAction {
        /**
         * 放弃
         */
        GIVEUP,
        /**
         * 继续
         */
        CONTINUE;
    }

    /**
     * 重试的间隔时间,设置giveUp忽略此项
     */
    long sleepMills() default 200;

    /**
     * 重试次数
     */
    int retryTimes() default 5;

}
