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
@Table(name = "sh_bank_card")
@org.hibernate.annotations.Table(appliesTo = "sh_bank_card", comment = "银行表")
public class ShBankCard implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 银行
     */
    @Column(name = "bank_id", columnDefinition = "int(11) DEFAULT NULL COMMENT '银行id'")
    private Integer bankId;
    /**
     * 银行代码
     */
    @Column(name = "bank_code", columnDefinition = "varchar(45) DEFAULT NULL COMMENT '银行代码'")
    private String bankCode;
    /**
     * 银行名称
     */
    @Column(name = "bank_name", columnDefinition = "varchar(45) NOT NULL COMMENT '银行名称'")
    private String bankName;
    /**
     * 银行bin
     */
    @Column(name = "bin", columnDefinition = "varchar(45) NOT NULL COMMENT '银行bin'")
    private String bin;
    /**
     * 分行代码
     */
    @Column(name = "branch_code", columnDefinition = "varchar(45) NOT NULL COMMENT '分行代码'")
    private String branchCode;

    /**
     * 银行卡名
     */
    @Column(name = "card_name", columnDefinition = "varchar(45) NOT NULL DEFAULT '' COMMENT '银行卡名'")
    private String cardName;
    /**
     * 银行卡长度
     */
    @Column(name = "card_no_length", columnDefinition = "int(11) NOT NULL COMMENT '银行卡长度'")
    private Integer cardNoLength;
    /**
     * 银行卡类型
     */
    @Column(name = "card_type", columnDefinition = "varchar(45) NOT NULL DEFAULT '' COMMENT '银行卡类型'")
    private String cardType;
    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
    /**
     * 修改时间
     */
    @Column(name = "modify_time", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Date modifyTime;
}