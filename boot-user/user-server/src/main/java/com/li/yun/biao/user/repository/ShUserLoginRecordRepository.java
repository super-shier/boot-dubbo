package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShUserLoginRecord;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 10:58 AM
 * @description
 */
@Repository("shUserLoginRecordRepository")
public interface ShUserLoginRecordRepository extends CrudRepository<ShUserLoginRecord, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShUserLoginRecord, Long> {
}
