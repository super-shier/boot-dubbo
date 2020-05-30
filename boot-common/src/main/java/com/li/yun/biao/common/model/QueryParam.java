package com.li.yun.biao.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/28 5:18 PM
 * @description 基础分页参数
 */
@Data
public class QueryParam implements Serializable {
    /**
     * 页数
     */
    private int page = 1;
    /**
     * 条数
     */
    private int pageSize = 20;
    /**
     * 排序字段
     */
    private List<String> fieldName;
    /**
     * 是否逆序
     */
    private boolean desc = true;
}
