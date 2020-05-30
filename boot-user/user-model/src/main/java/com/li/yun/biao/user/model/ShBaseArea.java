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
@Table(name = "sh_base_area")
@org.hibernate.annotations.Table(appliesTo = "sh_base_area", comment = "地区表")
public class ShBaseArea implements Serializable {
    /**
     * 主键id(区域code)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    /**
     * 区域名称
     */
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '区域名称'")
    private String name;
    /**
     * 所属城市code
     */
    @Column(name = "city_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属城市code''")
    private Long cityCode;
    /**
     * 所属省份code
     */
    @Column(name = "province_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属省份code'")
    private Long provinceCode;
}