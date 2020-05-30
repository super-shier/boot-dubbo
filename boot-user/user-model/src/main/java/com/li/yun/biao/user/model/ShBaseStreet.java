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
@Table(name = "sh_base_street")
@org.hibernate.annotations.Table(appliesTo = "sh_base_street", comment = "街道表")
public class ShBaseStreet implements Serializable {
    /**
     * 主键id(街道code)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    /**
     * 乡镇街道名称
     */
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '乡镇街道名称'")
    private String name;
    /**
     * 所属区域
     */
    @Column(name = "area_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属区域'")
    private Long areaCode;
    /**
     * 所属城市
     */
    @Column(name = "city_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属城市'")
    private Long cityCode;
    /**
     * 省份名称
     */
    @Column(name = "province_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属省份'")
    private Long provinceCode;

}