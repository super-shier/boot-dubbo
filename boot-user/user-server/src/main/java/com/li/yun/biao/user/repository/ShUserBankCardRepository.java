package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShUserBankCard;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:10 PM
 * @description
 */
@Repository("shUserBankCardRepository")
public interface ShUserBankCardRepository extends CrudRepository<ShUserBankCard, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShUserBankCard, Long> {
}
