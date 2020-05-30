package com.li.yun.biao.user.model;

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
@Table(name = "sh_user_login_record")
@org.hibernate.annotations.Table(appliesTo = "sh_user_login_record", comment = "用户登录表")
public class ShUserLoginRecord implements Serializable {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "uid", columnDefinition = "bigint(11) NOT NULL COMMENT '用户id'")
    private Long uid;
    /**
     * 用户姓名
     */
    @Column(name = "really_name", columnDefinition = "varchar(11) DEFAULT NULL COMMENT '用户姓名'")
    private String reallyName;

    /**
     * 登陆时间
     */
    @Column(name = "login_time", columnDefinition = "datetime NOT NULL COMMENT '登陆时间'")
    private Date loginTime;
    /**
     * 登陆地址
     */
    @Column(name = "login_address", columnDefinition = "varchar(1024) DEFAULT '' COMMENT '登陆地址'")
    private String loginAddress;
}