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
@Table(name = "sh_permission")
@org.hibernate.annotations.Table(appliesTo = "sh_permission", comment = "权限表")
public class ShPermission implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 上级ID
     */
    @Column(name = "pid", columnDefinition = "bigint(11) NOT NULL DEFAULT '0' COMMENT '上级ID'")
    private Long pid;
    /**
     * 标题
     */
    @Column(name = "title", columnDefinition = "varchar(30) COLLATE utf8_bin NOT NULL COMMENT '标题'")
    private String title;
    /**
     * 类型
     */
    @Column(name = "type", columnDefinition = "smallint(6) NOT NULL DEFAULT '0' COMMENT '类型 0、菜单 1、功能'")
    private Short type;
    /**
     * 状态
     */
    @Column(name = "state", columnDefinition = "smallint(6) NOT NULL DEFAULT '0' COMMENT '状态 1、正常 0、禁用'")
    private Short state;
    /**
     * 排序
     */
    @Column(name = "sort", columnDefinition = "int(4) NOT NULL DEFAULT '0' COMMENT '排序'")
    private Integer sort;
    /**
     * 地址
     */
    @Column(name = "url", columnDefinition = "varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '地址'")
    private String url;
    /**
     * 权限编码
     */
    @Column(name = "perm_code", columnDefinition = "varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '权限编码'")
    private String permCode;
    /**
     * 图标
     */
    @Column(name = "icon", columnDefinition = "varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT '图标'")
    private String icon;
    /**
     * 描述
     */
    @Column(name = "description", columnDefinition = "varchar(80) COLLATE utf8_bin NOT NULL COMMENT '描述'")
    private String description;
    /**
     * 前端类型
     */
    @Column(name = "p_type", columnDefinition = "int(2) NOT NULL DEFAULT '0' COMMENT '0:javaWeb,1:vue'")
    private Integer pType;
}