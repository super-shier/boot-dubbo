package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShBankCard;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:40 PM
 * @description
 */
@Repository("shBankCardRepository")
public interface ShBankCardRepository extends CrudRepository<ShBankCard, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShBankCard, Long> {
}