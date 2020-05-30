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
@Table(name = "sh_goods_photo_wall")
@org.hibernate.annotations.Table(appliesTo = "sh_goods_photo_wall", comment = "图片墙表")
public class ShGoodsPhotoWall implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商品Id
     */
    @Column(name = "good_id", columnDefinition = "bigint(11) NOT NULL COMMENT '商品Id'")
    private Long goodId;
    /**
     * 分类ID
     */
    @Column(name = "category_id", columnDefinition = "bigint(11) NOT NULL COMMENT '分类ID'")
    private Long categoryId;
    /**
     * 图片地址
     */
    @Column(name = "image_url", columnDefinition = "varchar(256) NOT NULL COMMENT '图片地址'")
    private String imageUrl;
    /**
     * 链接地址
     */
    @Column(name = "url", columnDefinition = "varchar(256) DEFAULT NULL COMMENT '链接地址'")
    private String url;
    /**
     * 排序
     */
    @Column(name = "sort", columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '排序'")
    private Integer sort;
    /**
     * 类型
     */
    @Column(name = "action_type", columnDefinition = "int(11) DEFAULT NULL COMMENT '1 Webview 2 商品 3 分类'")
    private Integer actionType;
    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "varchar(256) DEFAULT NULL COMMENT '描述'")
    private String description;
    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "int(11) NOT NULL DEFAULT '1' COMMENT '状态 1 启用 2禁用'")
    private Integer status;
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