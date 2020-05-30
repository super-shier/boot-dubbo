package com.li.yun.biao.user.model;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "sh_base_city")
@org.hibernate.annotations.Table(appliesTo = "sh_base_city", comment = "城市表")
public class ShBaseCity implements Serializable {
    /**
     * 主键id(城市code)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 城市名称
     */
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '城市名称'")
    private String name;

    /**
     * 省份code
     */
    @Column(name = "province_code", columnDefinition = "bigint(11) NOT NULL COMMENT '省份code'")
    private Long provinceCode;

}