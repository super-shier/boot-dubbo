package com.li.yun.biao.goods.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 8:00 PM
 * @description
 */
@Data
public class ShGoodsCategoryQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 分类名
     */
    @ApiModelProperty(value = "分类名")
    private String name;
    /**
     * 父类id
     */
    @ApiModelProperty(value = "父类id")
    private Long pid;
    /**
     * 父类id列表
     */
    @ApiModelProperty(value = "父类id列表")
    private List<Long> pidList;
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
