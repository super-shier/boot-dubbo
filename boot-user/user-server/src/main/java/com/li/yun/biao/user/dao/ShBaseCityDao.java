package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShBaseCity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:14 PM
 * @description
 */
@Repository
public interface ShBaseCityDao extends JpaRepository<ShBaseCity, Long> {

    ShBaseCity findByProvinceCodeAndName(@Param("provinceCode") Long provinceCode, @Param("name") String name);

    List<ShBaseCity> findByProvinceCode(@Param("provinceCode") Long provinceCode);
}
