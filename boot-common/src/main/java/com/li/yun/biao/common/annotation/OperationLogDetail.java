package com.li.yun.biao.common.annotation;


import com.li.yun.biao.common.enums.EnumOperationType;
import com.li.yun.biao.common.enums.EnumOperationUnit;

import java.lang.annotation.*;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/26 5:34 PM
 * @description
 */
//@OperationLogDetail(detail = "通过手机号[{{tel}}]获取用户名",level = 3,operationUnit = EnumOperationUnit.USER,operationType = EnumOperationType.SELECT)
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationLogDetail {
    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String detail() default "";

    /**
     * 日志等级:自己定，此处分为1-9
     */
    int level() default 0;

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    EnumOperationType operationType() default EnumOperationType.UNKNOWN;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    EnumOperationUnit operationUnit() default EnumOperationUnit.UNKNOWN;
}