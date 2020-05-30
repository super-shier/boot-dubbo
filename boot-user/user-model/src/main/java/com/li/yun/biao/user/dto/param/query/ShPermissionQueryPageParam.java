package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 4:14 PM
 * @description
 */
@Data
public class ShPermissionQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 上级ID
     */
    @ApiModelProperty(value = "上级ID")
    private Long pid;
    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private Short type;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Short state;
    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    private String permCode;
    /**
     * 前端类型
     */
    @ApiModelProperty(value = "前端类型")
    private Integer pType;

}
