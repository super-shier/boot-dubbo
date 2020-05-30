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
@Table(name = "sh_goods")
@org.hibernate.annotations.Table(appliesTo = "sh_goods", comment = "商品表")
public class ShGoods implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品名称
     */
    @Column(name = "name", columnDefinition = "varchar(200) NOT NULL COMMENT '商品名称'")
    private String name;
    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "int(11) NOT NULL COMMENT '状态 0 下架 1 上架'")
    private Integer status;
    /**
     * 分类id
     */
    @Column(name = "category_id", columnDefinition = "bigint(11) NOT NULL COMMENT '分类id'")
    private Long categoryId;
    /**
     * 概述
     */
    @Column(name = "summary", columnDefinition = "text COMMENT '概述'")
    private String summary;
    /**
     * 图片
     */
    @Column(name = "image_url", columnDefinition = "varchar(256) DEFAULT '' COMMENT '图片'")
    private String imageUrl;
    /**
     * 详情
     */
    @Column(name = "description", columnDefinition = "text COMMENT '详情'")
    private String description;
    /**
     * 排序
     */
    @Column(name = "sort", columnDefinition = "int(11) DEFAULT NULL COMMENT '排序'")
    private Integer sort;
    /**
     * 步骤
     */
    @Column(name = "step", columnDefinition = "int(11) NOT NULL DEFAULT '1' COMMENT '步骤'")
    private Integer step;
    /**
     * 使用橱窗
     */
    @Column(name = "show_case", columnDefinition = "int(11) DEFAULT '0' COMMENT '使用橱窗 0 使用 1 不使用'")
    private Integer showCase;
    /**
     * 发票
     */
    @Column(name = "invoice", columnDefinition = "varchar(64) DEFAULT '0' COMMENT '发票'")
    private String invoice;
    /**
     * 退换货承诺
     */
    @Column(name = "refund", columnDefinition = "varchar(256) DEFAULT '0' COMMENT '退换货承诺'")
    private String refund;
    /**
     * 是否保修
     */
    @Column(name = "warranty", columnDefinition = "int(11) DEFAULT '0' COMMENT '是否保修'")
    private Integer warranty;
    /**
     *
     */
    @Column(name = "p_type", columnDefinition = "varchar(45) DEFAULT NULL")
    private String pType;
    /**
     * 折扣
     */
    @Column(name = "discount", columnDefinition = "float DEFAULT '1' COMMENT '折扣：0.7'")
    private Float discount;

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