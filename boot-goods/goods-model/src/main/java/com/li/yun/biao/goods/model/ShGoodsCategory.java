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
@Table(name = "sh_goods_category")
@org.hibernate.annotations.Table(appliesTo = "sh_goods_category", comment = "商品分类")
public class ShGoodsCategory implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 分类名
     */
    @Column(name = "name", columnDefinition = "varchar(45) NOT NULL COMMENT '分类名'")
    private String name;
    /**
     * 商品数量
     */
    @Column(name = "count", columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '商品数量'")
    private Integer count;
    /**
     * 排序
     */
    @Column(name = "sort", columnDefinition = "int(11) NOT NULL DEFAULT '0' COMMENT '排序'")
    private Integer sort;
    /**
     * 图标地址
     */
    @Column(name = "thumb_url", columnDefinition = "varchar(256) DEFAULT NULL COMMENT '图标地址'")
    private String thumbUrl;
    /**
     * 父类
     */
    @Column(name = "pid", columnDefinition = "bigint(11) NOT NULL DEFAULT '0' COMMENT '父类'")
    private Long pid;
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