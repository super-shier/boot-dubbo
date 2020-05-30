package com.li.yun.biao.goods.dao;

import com.li.yun.biao.goods.model.ShGoodsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 3:53 PM
 * @description
 */
@Repository
public interface ShGoodsDetailDao extends JpaRepository<ShGoodsDetail, Long> {
    List<ShGoodsDetail> findByGoodsId(Long goodsId);
}
