package com.li.yun.biao.goods.repository;

import com.li.yun.biao.goods.model.ShGoods;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:40 PM
 * @description
 */
@Repository("shGoodsRepository")
public interface ShGoodsRepository extends CrudRepository<ShGoods, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShGoods, Long> {
}