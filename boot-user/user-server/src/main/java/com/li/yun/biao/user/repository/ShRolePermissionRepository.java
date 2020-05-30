package com.li.yun.biao.user.repository;

import com.li.yun.biao.user.model.ShRolePermission;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/11 3:43 PM
 * @description
 */
@Repository("shRolePermissionRepository")
public interface ShRolePermissionRepository extends CrudRepository<ShRolePermission, Long>, JpaSpecificationExecutor,
        PagingAndSortingRepository<ShRolePermission, Long> {
}
