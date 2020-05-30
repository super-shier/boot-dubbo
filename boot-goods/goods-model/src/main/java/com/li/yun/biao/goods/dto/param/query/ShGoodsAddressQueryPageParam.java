package com.li.yun.biao.goods.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 7:55 PM
 * @description
 */
@Data
public class ShGoodsAddressQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 用户uid
     */
    @ApiModelProperty(value = "用户uid")
    private Long uid;
    /**
     * 用户uid列表
     */
    @ApiModelProperty(value = "用户uid列表")
    private List<Long> uidList;
    /**
     * 收货人
     */
    @ApiModelProperty(value = "收货人")
    private String consignee;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 省份编码
     */
    @ApiModelProperty(value = "省份编码")
    private Long provinceCode;
    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码")
    private Long cityCode;
    /**
     * 区域编码
     */
    @ApiModelProperty(value = "区域编码")
    private Long areaCode;
    /**
     * 邮编
     */
    @ApiModelProperty(value = "邮编")
    private String postCode;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;
    /**
     * 状态列表
     */
    @ApiModelProperty(value = "状态列表")
    private List<Integer> statusList;
    /**
     * 开始创建时间
     */
    @ApiModelProperty(value = "开始创建时间")
    private Date startCreateTime;
    /**
     * 截止创建时间
     */
    @ApiModelProperty(value = "截止创建时间")
    private Date endCreateTime;
}
