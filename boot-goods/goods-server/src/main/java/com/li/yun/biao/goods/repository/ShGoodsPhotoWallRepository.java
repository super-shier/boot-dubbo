package com.li.yun.biao.goods.repository;

import com.li.yun.biao.goods.model.ShGoodsPhotoWall;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 3:42 PM
 * @description
 */
@Repository("shGoodsPhotoWallRepository")
public interface ShGoodsPhotoWallRepository extends CrudRepository<ShGoodsPhotoWall, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShGoodsPhotoWall, Long> {
}
