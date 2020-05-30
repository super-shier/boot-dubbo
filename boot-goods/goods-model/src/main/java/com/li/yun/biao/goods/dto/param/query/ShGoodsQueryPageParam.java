package com.li.yun.biao.goods.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 5:02 PM
 * @description
 */
@Data
public class ShGoodsQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    private String name;
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
     * 分类id
     */
    @ApiModelProperty(value = "分类id")
    private Long categoryId;
    /**
     * 分类id列表
     */
    @ApiModelProperty(value = "分类id列表")
    private List<Long> categoryIdList;
    /**
     * 使用橱窗
     */
    @ApiModelProperty(value = "使用橱窗")
    private Integer showCase;
    /**
     * 是否保修
     */
    @ApiModelProperty(value = "是否保修")
    private Integer warranty;
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
