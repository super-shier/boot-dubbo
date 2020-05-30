package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 4:48 PM
 * @description
 */
@Data
public class ShUserRoleQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long uid;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long rid;
    /**
     * 前端类型
     */
    @ApiModelProperty(value = "前端类型")
    private Long pType;
}
