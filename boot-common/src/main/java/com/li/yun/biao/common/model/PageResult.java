package com.li.yun.biao.common.model;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/5 6:09 PM
 * @description 分页查询结果
 */
@Data
public class PageResult<T> implements Serializable {
    private boolean success = true;
    /**
     * 总条数
     */
    private long total = 0L;
    /**
     * 当前页数
     */
    private long page;
    /**
     * 总页数
     */
    private long totalPage;
    /**
     * 当页数据
     */
    private Collection<T> dataList;

    public PageResult() {
    }

    public PageResult(Page<T> page) {
        this.total = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.page = page.getPageable().getPageNumber() + 1;
        this.dataList = page.getContent();
    }

    public PageResult(long total, long totalPage, Collection<T> dataList) {
        this.total = total;
        this.totalPage = totalPage;
        this.dataList = dataList;
    }
}
