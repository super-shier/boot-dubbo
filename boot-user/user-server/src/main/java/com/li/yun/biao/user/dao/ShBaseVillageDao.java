package com.li.yun.biao.user.dao;

import com.li.yun.biao.user.model.ShBaseVillage;
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
public interface ShBaseVillageDao extends JpaRepository<ShBaseVillage, Long> {
    List<ShBaseVillage> findByProvinceCodeAndCityCodeAndAreaCodeAndStreetCode(@Param("provinceCode") Long provinceCode, @Param("cityCode") Long cityCode, @Param("areaCode") Long areaCode, @Param("streetCode") Long streetCode);
}
