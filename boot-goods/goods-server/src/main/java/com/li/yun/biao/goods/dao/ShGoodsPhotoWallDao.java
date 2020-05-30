package com.li.yun.biao.goods.dao;

import com.li.yun.biao.goods.model.ShGoodsPhotoWall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/16 3:53 PM
 * @description
 */
@Repository
public interface ShGoodsPhotoWallDao extends JpaRepository<ShGoodsPhotoWall, Long> {
}
