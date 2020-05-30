package com.li.yun.biao.job.core.handler.annotation;

import java.lang.annotation.*;

/**
 * @author: liyunbiao
 * @date: 2020/5/28 10:43 上午
 * @description annotation for method jobhandler
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Job {

    /**
     * jobhandler name
     */
    String value();

    /**
     * init handler, invoked when JobThread init
     */
    String init() default "";

    /**
     * destroy handler, invoked when JobThread destroy
     */
    String destroy() default "";

}
