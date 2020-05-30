package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShBankLianhanghao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:16 PM
 * @description
 */
@Repository
public interface ShBankLianhanghaoDao extends JpaRepository<ShBankLianhanghao, Long> {
    ShBankLianhanghao findByBankNumber(String bankNumber);

    ShBankLianhanghao findByBankName(String bankName);
}
