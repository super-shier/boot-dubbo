package com.li.yun.biao.goods.repository;

import com.li.yun.biao.goods.model.ShGoodsCategory;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 3:43 PM
 * @description
 */
@Repository("shGoodsCategoryRepository")
public interface ShGoodsCategoryRepository extends CrudRepository<ShGoodsCategory, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShGoodsCategory, Long> {
}