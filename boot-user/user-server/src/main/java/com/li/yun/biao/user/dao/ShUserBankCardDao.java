package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShUserBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:06 PM
 * @description
 */
@Repository
public interface ShUserBankCardDao extends JpaRepository<ShUserBankCard, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM sh_user_bank_card  WHERE uid = :uid order by id desc")
    List<ShUserBankCard> findShUserBankCardByUid(@Param("uid") Long uid);
}
