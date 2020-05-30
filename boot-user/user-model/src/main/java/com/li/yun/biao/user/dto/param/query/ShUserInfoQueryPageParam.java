package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/6 3:32 PM
 * @description
 */
@Data
public class ShUserInfoQueryPageParam extends QueryParam implements Serializable {
    /**
     * 用户uid
     */
    @ApiModelProperty(value = "用户uid")
    public Long uid;
    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String mobile;

    /**
     * 用户姓名
     */
    @ApiModelProperty(value = "用户姓名")
    private String name;

    /**
     * 身份证号
     */
    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private Integer status;
    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private Integer level;

    /**
     * 开始创建时间
     */
    @ApiModelProperty(value = " 开始创建时间")
    private Date startCreateTime;

    /**
     * 结束创建时间
     */
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;
    /**
     * 用户id集合
     */
    @ApiModelProperty(value = "用户id集合")
    private List<Long> uidList;

    /**
     * 状态集合
     */
    @ApiModelProperty(value = "状态集合")
    private List<Integer> statusList;
}
