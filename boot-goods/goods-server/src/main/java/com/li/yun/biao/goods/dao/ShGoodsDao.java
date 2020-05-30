package com.li.yun.biao.goods.dao;

import com.li.yun.biao.goods.model.ShGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 3:52 PM
 * @description
 */
@Repository
public interface ShGoodsDao extends JpaRepository<ShGoods, Long> {
    @Query(nativeQuery = true, value = "SELECT count(1) FROM sh_goods g  WHERE if(?1 !=null ,g.category_id=?1,1=1);")
    Long queryGoodsCountByCategoryId(Long categoryId);
}
