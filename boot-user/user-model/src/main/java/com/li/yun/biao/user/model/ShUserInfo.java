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
@Table(name = "sh_user_info")
@org.hibernate.annotations.Table(appliesTo = "sh_user_info", comment = "用户表")
public class ShUserInfo implements Serializable {
    /**
     * 用户id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long uid;
    /**
     * 用户手机号
     */
    @Column(name = "mobile", columnDefinition = "varchar(11) COMMENT '用户手机号'")
    private String mobile;

    /**
     * 用户姓名
     */
    @Column(name = "name", columnDefinition = "varchar(20) COMMENT '用户姓名'")
    private String name;

    /**
     * 用户密码
     */
    @Column(name = "pass_word", columnDefinition = "varchar(256) COMMENT '用户密码'")
    private String passWord;

    /**
     * 身份证号
     */
    @Column(name = "id_number", columnDefinition = "varchar(18) COMMENT '身份证号'")
    private String idNumber;

    /**
     * 用户状态
     */
    @Column(name = "status", columnDefinition = "int(2) default 0 comment '用户状态0:未审核,1:审核通过,2:审核不通过,-1:禁用'")
    private Integer status;
    /**
     * 等级
     */
    @Column(name = "level", columnDefinition = "int(3) default null comment '等级'")
    private Integer level;

    /**
     * 用户头像
     */
    @Column(name = "head_portrait", columnDefinition = "varchar(126) default 0 comment '用户头像'")
    private String headPortrait;

    /**
     * 总交易金额
     */
    @Column(name = "total_transaction", insertable = false, updatable = false, columnDefinition = "bigint(20) NOT NULL DEFAULT '0' comment '总交易金额'")
    private Long totalTransaction;

    /**
     * 账户余额
     */
    @Column(name = "total_balance", insertable = false, updatable = false, columnDefinition = "bigint(11) NOT NULL DEFAULT '0' COMMENT '账户余额'")
    private Long totalBalance;
    /**
     * 基本住址
     */
    @Column(name = "address", columnDefinition = "varchar(256) DEFAULT NULL COMMENT '基本住址'")
    private String address;
    /**
     * 注册地址
     */
    @Column(name = "register_address", columnDefinition = "varchar(256) DEFAULT NULL COMMENT '注册地址'")
    private String registerAddress;
    /**
     * 创建时间
     */
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Date createTime;
    /**
     * 修改时间
     */
    @Column(name = "modify_time", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Date modifyTime;
}