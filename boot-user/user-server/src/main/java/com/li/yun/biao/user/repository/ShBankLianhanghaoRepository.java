package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShBankLianhanghao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 2:39 PM
 * @description
 */
@Repository("shBankLianhanghaoRepository")
public interface ShBankLianhanghaoRepository extends CrudRepository<ShBankLianhanghao, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShBankLianhanghao, Long> {
}
