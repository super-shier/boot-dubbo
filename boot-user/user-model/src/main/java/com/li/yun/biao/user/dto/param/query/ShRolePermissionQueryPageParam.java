package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 4:42 PM
 * @description
 */
@Data
public class ShRolePermissionQueryPageParam extends QueryParam implements Serializable {

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long rid;
    /**
     * 权限ID
     */
    @ApiModelProperty(value = "权限ID")
    private Long pid;
    /**
     * 前端类型
     */
    @ApiModelProperty(value = "前端类型")
    private Integer pType;

}
