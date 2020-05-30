package com.li.yun.biao.user.dto.param.query;

import com.li.yun.biao.common.model.QueryParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:17 PM
 * @description
 */
@Data
public class ShUserBankCardQueryPageParam extends QueryParam implements Serializable {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "主键id")
    private Long uid;
    /**
     * 认证状态
     */
    @ApiModelProperty(value = "主键id")
    private Integer status;

    /**
     * 状态数组
     */
    @ApiModelProperty(value = "主键id")
    private List<Integer> statusList;
    /**
     * 银行卡号
     */
    @ApiModelProperty(value = "主键id")
    private String cardNo;
    /**
     * 卡类型
     */
    @ApiModelProperty(value = "主键id")
    private Integer cardType;
    /**
     * 账户姓名
     */
    @ApiModelProperty(value = "账户姓名")
    private String accountName;
    /**
     * 账户手机号
     */
    @ApiModelProperty(value = "账户手机号")
    private String accountMobile;
    /**
     * 身份证号码
     */
    @ApiModelProperty(value = "身份证号码")
    private String idNumber;
    /**
     * 开始创建时间
     */
    @ApiModelProperty(value = "开始创建时间")
    private Date startCreateTime;
    /**
     * 结束创建时间
     */
    @ApiModelProperty(value = "结束创建时间")
    private Date endCreateTime;

}
