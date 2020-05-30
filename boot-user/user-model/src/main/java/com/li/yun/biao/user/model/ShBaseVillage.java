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
@Table(name = "sh_base_village")
@org.hibernate.annotations.Table(appliesTo = "sh_base_village", comment = "村委会表")
public class ShBaseVillage implements Serializable {
    /**
     * 主键id(村委会code)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    /**
     * 乡镇街道名称
     */
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '村委会名称'")
    private String name;

    /**
     * 所属乡镇街道code
     */
    @Column(name = "street_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属乡镇街道code'")
    private Long streetCode;

    /**
     * 所属区域code
     */
    @Column(name = "area_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属区域code'")
    private Long areaCode;
    /**
     * 所属城市code
     */
    @Column(name = "city_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属城市code'")
    private Long cityCode;
    /**
     * 所属省份code
     */
    @Column(name = "province_code", columnDefinition = "bigint(11) NOT NULL COMMENT '所属省份code'")
    private Long provinceCode;
}