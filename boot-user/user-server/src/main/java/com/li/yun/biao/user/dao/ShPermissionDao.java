package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:16 PM
 * @description
 */
@Repository
public interface ShPermissionDao extends JpaRepository<ShPermission, Long> {
    ShPermission findByPermCodeAndPType(String permCode, Integer pType);

    @Query(nativeQuery = true, value = "SELECT * FROM sh_permission  WHERE sort = :sort limit 0,1")
    ShPermission findBySort(Integer sort);

    @Query(nativeQuery = true, value = "SELECT p.perm_code FROM sh_permission p WHERE p.id in (:permissionIdByRid) and p.state=1")
    List<String> findPermCodeByIds(@Param("permissionIdByRid") List<Long> permissionIdByRid);

    @Query(nativeQuery = true, value = "SELECT * FROM sh_permission p  WHERE p.pid = :pid and p.p_type= :pType and p.state=1 order by p.sort")
    List<ShPermission> findByPidAndPType(@Param("pid") Long pid, @Param("pType") Integer pType);

    @Query(nativeQuery = true, value = "SELECT * FROM sh_permission p  WHERE p.id in (:idList) and p.p_type= :pType and p.pid=0 and p.state=1 order by p.sort;")
    List<ShPermission> findPermissionListByIdList(@Param("idList") List<Long> idList, @Param("pType") Integer pType);
}
