package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShUserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 3:44 PM
 * @description
 */
@Repository("shUserRoleRepository")
public interface ShUserRoleRepository extends CrudRepository<ShUserRole, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShUserRole, Long> {
}
