package com.li.yun.biao.goods.dao;

import com.li.yun.biao.goods.model.ShGoodsAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 3:54 PM
 * @description
 */
@Repository
public interface ShGoodsAddressDao extends JpaRepository<ShGoodsAddress, Long> {
    List<ShGoodsAddress> findByUidAndStatus(Long uid, Integer status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update sh_goods_address set status = 0 where uid = ?1 and status=1", nativeQuery = true)
    int updateGoodsAddressNotDefault(Long uid);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update sh_goods_address set status = 1 where id = ?1", nativeQuery = true)
    int updateGoodsAddressDefault(Long id);

    @Query(nativeQuery = true, value = "SELECT a.* FROM sh_goods_address a  WHERE a.uid = ?1 and a.status= 1 order by a.modify_time desc limit 0,1;")
    ShGoodsAddress findDefaultGoodsAddressByUid(Long uid);
}
