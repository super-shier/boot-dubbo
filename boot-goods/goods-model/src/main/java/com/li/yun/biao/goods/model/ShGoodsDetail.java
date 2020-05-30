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
@Table(name = "sh_goods_detail")
@org.hibernate.annotations.Table(appliesTo = "sh_goods_detail", comment = "具体商品表")
public class ShGoodsDetail implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 价格
     */
    @Column(name = "price", columnDefinition = "bigint(11) NOT NULL DEFAULT '0' COMMENT '价格(分)'")
    private Long price;
    /**
     * 商品id
     */
    @Column(name = "goods_id", columnDefinition = "bigint(11) NOT NULL COMMENT '商品id'")
    private Long goodsId;
    /**
     * 库存
     */
    @Column(name = "amount", columnDefinition = "int(11) NOT NULL COMMENT '库存'")
    private Integer amount;
    /**
     * 销售数量
     */
    @Column(name = "sold_amount", columnDefinition = "bigint(11) NOT NULL DEFAULT '0' COMMENT '销售数量'")
    private Long soldAmount;
    /**
     * 图片
     */
    @Column(name = "image_url", columnDefinition = "text COMMENT '图片'")
    private String imageUrl;
    /**
     * 货号
     */
    @Column(name = "number", columnDefinition = "varchar(45) NOT NULL COMMENT '货号'")
    private String number;
    /**
     * 状态
     */
    @Column(name = "status", columnDefinition = "int(2) NOT NULL COMMENT '状态'")
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