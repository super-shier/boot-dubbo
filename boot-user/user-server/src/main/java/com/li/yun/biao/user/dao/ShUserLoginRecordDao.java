package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShUserLoginRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:05 PM
 * @description
 */
@Repository
public interface ShUserLoginRecordDao extends JpaRepository<ShUserLoginRecord, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM sh_user_login_record  WHERE uid = :uid order by id desc limit 0,1")
    ShUserLoginRecord findLastUserLoginRecord(@Param("uid") Long uid);

    @Query(nativeQuery = true, value = "SELECT * FROM sh_user_login_record  WHERE uid = :uid order by id asc limit 0,1")
    ShUserLoginRecord findFirstUserLoginRecord(@Param("uid") Long uid);
}
