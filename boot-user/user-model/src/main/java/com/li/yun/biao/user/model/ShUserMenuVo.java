package com.li.yun.biao.user.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/13 4:27 PM
 * @description 用户菜单
 */
@Data
public class ShUserMenuVo implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 地址
     */
    @ApiModelProperty(value = "地址")
    private String url;

    /**
     * 权限编码
     */
    @ApiModelProperty(value = "权限编码")
    private String permCode;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 下级菜单列表
     */
    @ApiModelProperty(value = "下级菜单列表")
    private List<ShUserMenuVo> mvList;
}
