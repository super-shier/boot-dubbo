package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:16 PM
 * @description
 */
@Repository
public interface ShBankCardDao extends JpaRepository<ShBankCard, Long> {
    ShBankCard findByBin(String bin);

    ShBankCard findByBinAndCardNoLength(String bin, int cardNoLength);
}
