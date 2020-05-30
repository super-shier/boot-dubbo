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
@Table(name = "sh_user_role")
@org.hibernate.annotations.Table(appliesTo = "sh_user_role", comment = "用户表")
public class ShUserRole implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 用户id
     */
    @Column(name = "uid", columnDefinition = "bigint(11) NOT NULL COMMENT '用户ID'")
    private Long uid;
    /**
     * 角色ID
     */
    @Column(name = "rid", columnDefinition = "bigint(11) NOT NULL COMMENT '角色ID'")
    private Long rid;
    /**
     * 前端类型
     */
    @Column(name = "p_type", columnDefinition = "int(2) NOT NULL DEFAULT '0' COMMENT '0:javaWeb,1:vue'")
    private Integer pType;
}