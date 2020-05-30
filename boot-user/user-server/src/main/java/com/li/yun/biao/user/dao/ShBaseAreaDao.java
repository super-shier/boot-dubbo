package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShBaseArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/7 5:15 PM
 * @description
 */
@Repository
public interface ShBaseAreaDao extends JpaRepository<ShBaseArea, Long> {
    ShBaseArea findByProvinceCodeAndCityCodeAndName(@Param("provinceCode") Long provinceCode, @Param("cityCode") Long cityCode, @Param("name") String name);

    List<ShBaseArea> findByProvinceCodeAndCityCode(@Param("provinceCode") Long provinceCode, @Param("cityCode") Long cityCode);
}
