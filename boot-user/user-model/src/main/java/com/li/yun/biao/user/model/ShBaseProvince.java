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
@Table(name = "sh_base_province")
@org.hibernate.annotations.Table(appliesTo = "sh_base_province", comment = "省份表")
public class ShBaseProvince implements Serializable {
    /**
     * 主键id(省份code)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    /**
     * 省份名称
     */
    @Column(name = "name", columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '省份名称'")
    private String name;
}