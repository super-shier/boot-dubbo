package com.li.yun.biao.common.enums;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/26 7:17 PM
 * @description
 */
public enum EnumErrorCode {
    SYSTEM_ERROR("4001", "系统错误"),
    VALID_EXCEPTION_CODE("4000", "输入信息有误");
    private String errorCode;
    private String errorMsg;

    EnumErrorCode(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
