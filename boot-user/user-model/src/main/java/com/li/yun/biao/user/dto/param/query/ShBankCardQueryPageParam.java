package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:36 PM
 * @description
 */
@Data
public class ShBankCardQueryPageParam extends QueryParam implements Serializable {
    /**
     * 银行
     */
    @ApiModelProperty(value = "银行")
    private Integer bankId;
    /**
     * 银行代码
     */
    @ApiModelProperty(value = "银行代码")
    private String bankCode;
    /**
     * 银行名称
     */
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    /**
     * 银行bin
     */
    @ApiModelProperty(value = "银行bin")
    private String bin;
    /**
     * 分行代码
     */
    @ApiModelProperty(value = "分行代码")
    private String branchCode;
    /**
     * 银行卡类型
     */
    @ApiModelProperty(value = "银行卡类型")
    private String cardType;
}
