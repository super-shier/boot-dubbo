package com.li.yun.biao.goods.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 8:06 PM
 * @description
 */
@Data
public class ShGoodsPhotoWallQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 商品Id
     */
    @ApiModelProperty(value = "商品Id")
    private Long goodId;
    /**
     * 商品Id列表
     */
    @ApiModelProperty(value = "商品Id列表")
    private List<Long> goodIdList;
    /**
     * 分类Id
     */
    @ApiModelProperty(value = "分类Id")
    private Long categoryId;
    /**
     * 分类Id列表
     */
    @ApiModelProperty(value = "分类Id列表")
    private List<Long> categoryIdList;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Integer actionType;
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
