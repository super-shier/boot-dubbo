package com.li.yun.biao.goods.repository;

import com.li.yun.biao.goods.model.ShGoodsAddress;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:39 PM
 * @description
 */
@Repository("shGoodsAddressRepository")
public interface ShGoodsAddressRepository extends CrudRepository<ShGoodsAddress, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShGoodsAddress, Long> {
}
