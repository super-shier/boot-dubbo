package com.li.yun.biao.user.api;

import com.li.yun.biao.common.model.AtlasModel;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.user.model.*;

import java.util.List;

/**
 * 地区接口
 */
public interface UsAreaService {

    /**
     * 省份
     */
    DubboResponse<Long> addBaseProvince(ShBaseProvince baseProvince);

    DubboResponse<Long> updateBaseProvince(ShBaseProvince baseProvince);

    DubboResponse<ShBaseProvince> getBaseProvinceByCode(Long code);

    DubboResponse<ShBaseProvince> getBaseProvinceByName(String name);

    DubboResponse<List<ShBaseProvince>> getBaseProvinceList();

    /**
     * 城市
     */
    DubboResponse<Long> addBaseCity(ShBaseCity baseCity);

    DubboResponse<Long> updateBaseCity(ShBaseCity baseCity);

    DubboResponse<ShBaseCity> getBaseCityById(Long code);

    DubboResponse<ShBaseCity> getCityByProvinceCodeAndCityName(Long provinceCode, String cityName);

    DubboResponse<List<ShBaseCity>> getBaseCityList(Long provinceCode);

    /**
     * 地区
     */
    DubboResponse<Long> addBaseArea(ShBaseArea baseArea);

    DubboResponse<Long> updateBaseArea(ShBaseArea baseArea);

    DubboResponse<ShBaseArea> getBaseAreaByCode(Long code);

    DubboResponse<ShBaseArea> getAreaByAreaName(Long provinceCode, Long cityCode, String areaName);

    DubboResponse<List<ShBaseArea>> getBaseAreaList(Long provinceCode, Long cityCode);


    /**
     * 乡镇街道
     */
    DubboResponse<Long> addBaseStreet(ShBaseStreet baseStreet);

    DubboResponse<Long> updateBaseStreet(ShBaseStreet baseStreet);

    DubboResponse<ShBaseStreet> getBaseStreetByCode(Long code);

    DubboResponse<ShBaseStreet> getStreetByParentsAndStreetName(Long provinceCode, Long cityCode, Long areaCode, String streetName);

    DubboResponse<List<ShBaseStreet>> getBaseStreetList(Long provinceCode, Long cityCode, Long areaCode);

    /**
     * 村委
     */
    DubboResponse<Long> addBaseVillage(ShBaseVillage baseVillage);

    DubboResponse<Long> updateBaseVillage(ShBaseVillage baseVillage);

    DubboResponse<ShBaseVillage> getBaseVillageByCode(Long code);

    DubboResponse<List<ShBaseVillage>> getBaseVillageList(Long provinceCode, Long cityCode, Long areaCode, Long streetCode);

    /**
     * 获取地址信息
     *
     * @param lngLat 经纬度
     * @param ip     ip
     */
    DubboResponse<AtlasModel> getAtlasModelByLngLatOrIp(String lngLat, String ip);
}
