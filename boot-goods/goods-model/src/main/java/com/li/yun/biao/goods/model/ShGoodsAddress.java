package com.li.yun.biao.goods.model;

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
@Table(name = "sh_goods_address")
@org.hibernate.annotations.Table(appliesTo = "sh_goods_address", comment = "用户收货地址")
public class ShGoodsAddress implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户uid
     */
    @Column(name = "uid", columnDefinition = "bigint(11) unsigned NOT NULL COMMENT '用户uid'")
    private Long uid;

    /**
     * 收货人
     */
    @Column(name = "consignee", columnDefinition = "varchar(45) NOT NULL DEFAULT '' COMMENT '收货人'")
    private String consignee;

    /**
     * 手机号
     */
    @Column(name = "mobile", columnDefinition = "varchar(11) NOT NULL DEFAULT '' COMMENT '手机号'")
    private String mobile;

    /**
     * 性别
     */
    @Column(name = "sex", columnDefinition = "int(2) NOT NULL DEFAULT '0' COMMENT '性别 0男 1女'")
    private Integer sex;
    /**
     * 省份编码
     */
    @Column(name = "province_code", columnDefinition = "bigint(11) NOT NULL COMMENT '省份编码'")
    private Long provinceCode;
    /**
     * 省份名称
     */
    @Column(name = "province_name", columnDefinition = "varchar(45) NOT NULL DEFAULT '' COMMENT '省份'")
    private String provinceName;
    /**
     * 城市编码
     */
    @Column(name = "city_code", columnDefinition = "bigint(11) DEFAULT NULL COMMENT '城市编码'")
    private Long cityCode;
    /**
     * 城市
     */
    @Column(name = "city_name", columnDefinition = "varchar(45) DEFAULT '' COMMENT '城市'")
    private String cityName;
    /**
     * 区域编码
     */
    @Column(name = "area_code", columnDefinition = "bigint(11) DEFAULT NULL COMMENT '区域编码'")
    private Long areaCode;
    /**
     * 区域名称
     */
    @Column(name = "area_name", columnDefinition = "varchar(45) DEFAULT '' COMMENT '区域'")
    private String areaName;

    /**
     * 详细信息
     */
    @Column(name = "address", columnDefinition = "varchar(256) NOT NULL COMMENT '详细信息'")
    private String address;

    /**
     * 地理位置信息
     */
    @Column(name = "lng_lat", columnDefinition = "varchar(100) DEFAULT NULL COMMENT '地理位置信息'")
    private String lngLat;
    /**
     * 邮编
     */
    @Column(name = "post_code", columnDefinition = "varchar(20) DEFAULT NULL COMMENT '邮编'")
    private String postCode;
    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "int(2) NOT NULL DEFAULT '0' COMMENT '1默认收货地址0非默认'")
    private Integer status;
    /**
     * 创建日期
     */
    @Column(name = "create_time", columnDefinition = "datetime NOT NULL COMMENT '创建日期'")
    private Date createTime;
    /**
     * 修改日期
     */
    @Column(name = "modify_time", columnDefinition = "datetime NOT NULL COMMENT '修改日期'")
    private Date modifyTime;

}