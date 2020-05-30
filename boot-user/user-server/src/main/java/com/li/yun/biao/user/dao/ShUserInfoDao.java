package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShUserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @Author: liyunbiao
 * @Date: 2019/7/11 8:44 PM
 */
@Repository
public interface ShUserInfoDao extends JpaRepository<ShUserInfo, Long> {
    ShUserInfo findByMobile(String mobile);

    ShUserInfo findByMobileAndPassWord(String mobile, String passWord);

    List<ShUserInfo> findByMobileOrMobile(String mobile1, String mobile2);

    @Query("SELECT o FROM ShUserInfo o WHERE o.mobile = :mobile1  OR o.mobile = :mobile2 ")
    List<ShUserInfo> findTwoMobile(@Param("mobile1") String mobile1, @Param("mobile2") String mobile2);

    @Query(value = "SELECT * FROM User WHERE id > ?1", countQuery = "SELECT count(*) FROM User WHERE id > ?1", nativeQuery = true)
    Page<ShUserInfo> findPageByIdAfter(Long id, Pageable pageable);

}
