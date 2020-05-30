package com.li.yun.biao.user.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "sh_user_bank_card")
@org.hibernate.annotations.Table(appliesTo = "sh_user_bank_card", comment = "用户卡列表")
public class ShUserBankCard implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "uid", columnDefinition = "bigint(11) NOT NULL COMMENT '用户id'")
    private Long uid;
    /**
     * 认证状态
     */
    @Column(name = "status", columnDefinition = "int(1) NOT NULL DEFAULT '0' COMMENT '认证状态,0:未认证，1：认证，2：认证失败'")
    private Integer status;
    /**
     * 银行卡bin
     */
    @Column(name = "bin", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '银行卡bin'")
    private String bin;
    /**
     * 银行卡号
     */
    @Column(name = "card_no", columnDefinition = "varchar(20) NOT NULL COMMENT '银行卡号不能重复'")
    private String cardNo;
    /**
     * 卡类型
     */
    @Column(name = "card_type", columnDefinition = "int(11) NOT NULL DEFAULT '1' COMMENT '1借记卡，2贷记卡，3其他'")
    private Integer cardType;
    /**
     * 卡名称
     */
    @Column(name = "card_name", columnDefinition = "varchar(45) DEFAULT NULL COMMENT '卡名称'")
    private String cardName;
    /**
     * 银行
     */
    @Column(name = "bank_name", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '银行'")
    private String bankName;
    /**
     * 银行code
     */
    @Column(name = "bank_code", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '银行code'")
    private String bankCode;
    /**
     * 账户姓名
     */
    @Column(name = "account_name", columnDefinition = "varchar(20) NOT NULL DEFAULT '' COMMENT '账户姓名'")
    private String accountName;
    /**
     * 账户手机号
     */
    @Column(name = "account_mobile", columnDefinition = "varchar(20) NOT NULL DEFAULT '' COMMENT '账户手机号'")
    private String accountMobile;
    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "varchar(200) DEFAULT NULL COMMENT '描述'")
    private String description;
    /**
     * 有效期
     */
    @Column(name = "valid_date", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '有效期'")
    private String validDate;
    /**
     * 信用卡校验码
     */
    @Column(name = "v_code", columnDefinition = "varchar(3) DEFAULT NULL COMMENT '信用卡校验码'")
    private String vCode;
    /**
     * 身份证号码
     */
    @Column(name = "id_number", columnDefinition = "varchar(18) NOT NULL DEFAULT '' COMMENT '身份证号码'")
    private String idNumber;
    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "datetime NOT NULL COMMENT '创建时间'")
    private Date createTime;
    /**
     * 修改时间
     */
    @Column(name = "modify_time", columnDefinition = "datetime NOT NULL COMMENT '修改时间'")
    private Date modifyTime;
}