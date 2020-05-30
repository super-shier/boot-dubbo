package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:16 PM
 * @description
 */
@Repository
public interface ShRoleDao extends JpaRepository<ShRole, Long> {
}
