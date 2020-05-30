package com.li.yun.biao.common.enums;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/26 6:04 PM
 * @description
 */
public enum EnumOperationUnit {
    /**
     * 被操作的单元
     */
    UNKNOWN("unknown"),
    USER("user"),
    EMPLOYEE("employee"),
    Redis("redis");

    private String value;

    EnumOperationUnit(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
