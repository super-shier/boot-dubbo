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
@Table(name = "sh_bank_lianhanghao")
@org.hibernate.annotations.Table(appliesTo = "sh_bank_lianhanghao", comment = "连行号表")
public class ShBankLianhanghao implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 银行序号
     */
    @Column(name = "bank_id", columnDefinition = "int(11) NOT NULL COMMENT '银行序号'")
    private Integer bankId;

    /**
     * 银行名字
     */
    @Column(name = "bank", columnDefinition = "varchar(32) NOT NULL DEFAULT '' COMMENT '银行名字'")
    private String bank;
    /**
     * 联行行号
     */
    @Column(name = "bank_number", columnDefinition = "varchar(32) NOT NULL DEFAULT '' COMMENT '联行行号'")
    private String bankNumber;

    /**
     * 银行支行名字
     */
    @Column(name = "bank_name", columnDefinition = "varchar(50) NOT NULL DEFAULT '' COMMENT '银行支行名字'")
    private String bankName;
    /**
     * 银行联系电话
     */
    @Column(name = "bank_phone", columnDefinition = "varchar(32) DEFAULT NULL COMMENT '银行联系电话'")
    private String bankPhone;
    /**
     * 省份code
     */
    @Column(name = "province_code", columnDefinition = "varchar(32) NOT NULL DEFAULT '' COMMENT '省份code'")
    private String provinceCode;
    /**
     * 省份名称
     */
    @Column(name = "province_name", columnDefinition = "varchar(32) NOT NULL DEFAULT '' COMMENT '省份名称'")
    private String provinceName;
    /**
     * 城市code
     */
    @Column(name = "city_code", columnDefinition = "varchar(32) NOT NULL DEFAULT '' COMMENT '城市code'")
    private String cityCode;
    /**
     * 城市名称
     */
    @Column(name = "city_name", columnDefinition = "varchar(32) NOT NULL DEFAULT '' COMMENT '城市名称'")
    private String cityName;
    /**
     * 区域code
     */
    @Column(name = "area_code", columnDefinition = "varchar(64) DEFAULT NULL COMMENT '区域code'")
    private String areaCode;
    /**
     * 区域名称
     */
    @Column(name = "area_name", columnDefinition = " varchar(64) DEFAULT NULL COMMENT '区域名称'")
    private String areaName;
    /**
     * 地址
     */
    @Column(name = "address", columnDefinition = "varchar(50) DEFAULT NULL COMMENT '地址'")
    private String address;
    /**
     * 分行swiftCode
     */
    @Column(name = "swift_code", columnDefinition = "varchar(10) DEFAULT NULL COMMENT '分行swiftCode'")
    private String swiftCode;
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