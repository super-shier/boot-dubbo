package com.li.yun.biao.goods.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 8:02 PM
 * @description
 */
@Data
public class ShGoodsDetailQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 最小价格
     */
    @ApiModelProperty(value = "最小价格")
    private Long minPrice;
    /**
     * 最大价格
     */
    @ApiModelProperty(value = "最大价格")
    private Long maxPrice;
    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    private Long goodsId;
    /**
     * 商品id列表
     */
    @ApiModelProperty(value = "商品id列表")
    private List<Long> goodsIdList;
    /**
     * 货号
     */
    @ApiModelProperty(value = "货号")
    private String number;
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
