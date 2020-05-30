package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:17 PM
 * @description
 */
@Repository
public interface ShRolePermissionDao extends JpaRepository<ShRolePermission, Long> {
    @Query(nativeQuery = true, value = "SELECT p.pid FROM sh_role_permission p  WHERE p.rid = :rid and p.p_type= :pType")
    List<Long> findPermissionIdByRidAndPType(@Param("rid") Long rid, @Param("pType") Integer pType);

    @Transactional
    @Modifying
    @Query(value = "delete from sh_role_permission p where p.rid = :pid and p.pid = :rid and p.p_type = :pType", nativeQuery = true)
    Integer deleteRolePermissionByPidAndRid(@Param("pid") Long pid, @Param("rid") Long rid, @Param("pType") Integer pType);

    @Query(nativeQuery = true, value = "SELECT * FROM sh_role_permission p  WHERE p.pid = :pid and p.rid = :rid and p.p_type = :pType")
    ShRolePermission findRolePermissionByPidAndRid(@Param("pid") Long pid, @Param("rid") Long rid, @Param("pType") Integer pType);
}
