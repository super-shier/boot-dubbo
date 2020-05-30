package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 3:42 PM
 * @description
 */
@Repository("shRoleRepository")
public interface ShRoleRepository extends CrudRepository<ShRole, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShRole, Long> {
}
