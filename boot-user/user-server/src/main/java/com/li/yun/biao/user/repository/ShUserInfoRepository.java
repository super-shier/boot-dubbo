package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShUserInfo;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/7/22 4:31 PM
 * @description
 */
@Repository("shUserInfoRepository")
public interface ShUserInfoRepository extends CrudRepository<ShUserInfo, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShUserInfo, Long> {
}
