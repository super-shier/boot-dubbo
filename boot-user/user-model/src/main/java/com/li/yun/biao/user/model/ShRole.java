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
@Table(name = "sh_role")
@org.hibernate.annotations.Table(appliesTo = "sh_role", comment = "角色表")
public class ShRole implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 角色
     */
    @Column(name = "name", columnDefinition = "varchar(30) NOT NULL COMMENT '角色'")
    private String name;

    /**
     * 排序
     */
    @Column(name = "sort", columnDefinition = "smallint(6) NOT NULL DEFAULT '0' COMMENT '排序'")
    private Short sort;
    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "varchar(60) NOT NULL COMMENT '描述'")
    private String description;
    /**
     * 前端类型
     */
    @Column(name = "p_type", columnDefinition = "int(2) NOT NULL DEFAULT '0' COMMENT '0:javaWeb,1:vue'")
    private Integer pType;
}