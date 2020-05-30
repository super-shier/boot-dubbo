package com.li.yun.biao.common.model;

import com.li.yun.biao.common.enums.EnumErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: liyunbiao
 * @Date: 2019/7/24 4:11 PM
 * @Description 请求结果
 */
@Data
public class DubboResponse<T> implements Serializable {
    private String respCode;
    private String respMsg;
    private Boolean success;
    private Date returnDate;
    private T responseObject;

    public DubboResponse() {
        this.success = true;
    }

    public DubboResponse(EnumErrorCode enumErrorCode) {
        this.success = false;
        this.respCode = enumErrorCode.getErrorCode();
        this.respMsg = enumErrorCode.getErrorMsg();
    }

    public DubboResponse(String respCode, String respMsg) {
        this.success = false;
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    public DubboResponse(T responseObject) {
        this.success = true;
        this.responseObject = responseObject;
    }

}
