package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:37 PM
 * @description
 */
@Data
public class ShBankLianHangHaoQueryPageParam extends QueryParam implements Serializable {

    /**
     * 银行序号
     */
    @ApiModelProperty(value = "银行序号")
    private Integer bankId;

    /**
     * 银行名字
     */
    @ApiModelProperty(value = "银行名字")
    private String bank;
    /**
     * 联行行号
     */
    @ApiModelProperty(value = "联行行号")
    private String bankNumber;
    /**
     * 省份code
     */
    @ApiModelProperty(value = "省份code")
    private String provinceCode;

    /**
     * 城市code
     */
    @ApiModelProperty(value = "城市code")
    private String cityCode;

    /**
     * 区域code
     */
    @ApiModelProperty(value = "区域code")
    private String areaCode;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String address;
}
