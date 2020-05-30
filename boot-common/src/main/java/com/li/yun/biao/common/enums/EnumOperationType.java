package com.li.yun.biao.common.enums;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/26 5:34 PM
 * @description
 */
public enum EnumOperationType {
    /**
     * 操作类型
     */
    UNKNOWN("unknown"),
    DELETE("delete"),
    SELECT("select"),
    UPDATE("update"),
    INSERT("insert");

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    EnumOperationType(String s) {
        this.value = s;
    }
}
