package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShBaseProvince;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:14 PM
 * @description
 */
@Repository
public interface ShBaseProvinceDao extends JpaRepository<ShBaseProvince, Long> {
    ShBaseProvince findByName(@Param("name") String name);
}