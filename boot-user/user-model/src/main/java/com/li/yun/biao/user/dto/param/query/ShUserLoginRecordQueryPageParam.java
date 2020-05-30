package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 10:59 AM
 * @description
 */
@Data
public class ShUserLoginRecordQueryPageParam extends QueryParam implements Serializable {

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
     * 用户数组
     */
    @ApiModelProperty(value = "用户数组")
    private List<Long> uidList;
    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String reallyName;
    /**
     * 开始登陆时间
     */
    @ApiModelProperty(value = "开始登陆时间")
    private Date startLoginTime;
    /**
     * 结束登陆时间
     */
    @ApiModelProperty(value = "结束登陆时间")
    private Date endLoginTime;
    /**
     * 登陆地址
     */
    @ApiModelProperty(value = "登陆地址")
    private String loginAddress;
}
