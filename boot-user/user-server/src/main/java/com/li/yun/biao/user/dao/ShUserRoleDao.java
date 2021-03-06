package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:17 PM
 * @description
 */
@Repository
public interface ShUserRoleDao extends JpaRepository<ShUserRole, Long> {
    ShUserRole findByUidAndPType( Long uid, Integer pType);
}
