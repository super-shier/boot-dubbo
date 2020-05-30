package com.li.yun.biao.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.li.yun.biao.common.model.AtlasModel;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.user.api.UsAreaService;
import com.li.yun.biao.user.dao.*;
import com.li.yun.biao.user.model.*;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.List;

@org.springframework.stereotype.Service("usAreaService")
@Service(version = "1.0.0", timeout = 3000, interfaceClass = UsAreaService.class)
public class UsAreaServiceImpl implements UsAreaService {
    @Resource
    private ShBaseProvinceDao shBaseProvinceDao;
    @Resource
    private ShBaseCityDao shBaseCityDao;
    @Resource
    private ShBaseAreaDao shBaseAreaDao;
    @Resource
    private ShBaseStreetDao shBaseStreetDao;
    @Resource
    private ShBaseVillageDao shBaseVillageDao;


    @Override
    public DubboResponse<Long> addBaseProvince(ShBaseProvince baseProvince) {
        return new DubboResponse<>(shBaseProvinceDao.save(baseProvince).getCode());
    }

    @Override
    public DubboResponse<Long> updateBaseProvince(ShBaseProvince baseProvince) {
        return new DubboResponse<>(shBaseProvinceDao.saveAndFlush(baseProvince).getCode());
    }

    @Override
    public DubboResponse<ShBaseProvince> getBaseProvinceByCode(Long code) {
        return new DubboResponse<>(shBaseProvinceDao.findById(code).orElse(null));
    }

    @Override
    public DubboResponse<ShBaseProvince> getBaseProvinceByName(String name) {
        return new DubboResponse<>(shBaseProvinceDao.findByName(name));
    }

    @Override
    public DubboResponse<List<ShBaseProvince>> getBaseProvinceList() {
        return new DubboResponse<>(shBaseProvinceDao.findAll(new Sort(Sort.Direction.ASC, "code")));
    }

    @Override
    public DubboResponse<Long> addBaseCity(ShBaseCity baseCity) {
        return new DubboResponse<>(shBaseCityDao.save(baseCity).getCode());
    }

    @Override
    public DubboResponse<Long> updateBaseCity(ShBaseCity baseCity) {
        return new DubboResponse<>(shBaseCityDao.saveAndFlush(baseCity).getCode());
    }

    @Override
    public DubboResponse<ShBaseCity> getBaseCityById(Long code) {
        return new DubboResponse<>(shBaseCityDao.findById(code).orElse(null));
    }

    @Override
    public DubboResponse<ShBaseCity> getCityByProvinceCodeAndCityName(Long provinceCode, String cityName) {
        return new DubboResponse<>(shBaseCityDao.findByProvinceCodeAndName(provinceCode, cityName));
    }

    @Override
    public DubboResponse<List<ShBaseCity>> getBaseCityList(Long provinceCode) {
        return new DubboResponse<>(shBaseCityDao.findByProvinceCode(provinceCode));
    }

    @Override
    public DubboResponse<Long> addBaseArea(ShBaseArea baseArea) {
        return new DubboResponse<>(shBaseAreaDao.save(baseArea).getCityCode());
    }

    @Override
    public DubboResponse<Long> updateBaseArea(ShBaseArea baseArea) {
        return new DubboResponse<>(shBaseAreaDao.saveAndFlush(baseArea).getCityCode());
    }

    @Override
    public DubboResponse<ShBaseArea> getBaseAreaByCode(Long code) {
        return new DubboResponse<>(shBaseAreaDao.findById(code).orElse(null));
    }

    @Override
    public DubboResponse<ShBaseArea> getAreaByAreaName(Long provinceCode, Long cityCode, String areaName) {
        return new DubboResponse<>(shBaseAreaDao.findByProvinceCodeAndCityCodeAndName(provinceCode, cityCode, areaName));
    }

    @Override
    public DubboResponse<List<ShBaseArea>> getBaseAreaList(Long provinceCode, Long cityCode) {
        return new DubboResponse<>(shBaseAreaDao.findByProvinceCodeAndCityCode(provinceCode, cityCode));
    }

    @Override
    public DubboResponse<Long> addBaseStreet(ShBaseStreet baseStreet) {
        return new DubboResponse<>(shBaseStreetDao.save(baseStreet).getCode());
    }

    @Override
    public DubboResponse<Long> updateBaseStreet(ShBaseStreet baseStreet) {
        return new DubboResponse<>(shBaseStreetDao.saveAndFlush(baseStreet).getCode());
    }

    @Override
    public DubboResponse<ShBaseStreet> getBaseStreetByCode(Long code) {
        return new DubboResponse<>(shBaseStreetDao.findById(code).orElse(null));
    }

    @Override
    public DubboResponse<ShBaseStreet> getStreetByParentsAndStreetName(Long provinceCode, Long cityCode, Long areaCode, String streetName) {
        return new DubboResponse<>(shBaseStreetDao.findByProvinceCodeAndCityCodeAndAreaCodeAndName(provinceCode, cityCode, areaCode, streetName));
    }

    @Override
    public DubboResponse<List<ShBaseStreet>> getBaseStreetList(Long provinceCode, Long cityCode, Long areaCode) {
        return new DubboResponse<>(shBaseStreetDao.findByProvinceCodeAndCityCodeAndAreaCode(provinceCode, cityCode, areaCode));
    }

    @Override
    public DubboResponse<Long> addBaseVillage(ShBaseVillage baseVillage) {
        return new DubboResponse<>(shBaseVillageDao.save(baseVillage).getCode());
    }

    @Override
    public DubboResponse<Long> updateBaseVillage(ShBaseVillage baseVillage) {
        return new DubboResponse<>(shBaseVillageDao.saveAndFlush(baseVillage).getCode());
    }

    @Override
    public DubboResponse<ShBaseVillage> getBaseVillageByCode(Long code) {
        return new DubboResponse<>(shBaseVillageDao.findById(code).orElse(null));
    }

    @Override
    public DubboResponse<List<ShBaseVillage>> getBaseVillageList(Long provinceCode, Long cityCode, Long areaCode, Long streetCode) {
        return new DubboResponse<>(shBaseVillageDao.findByProvinceCodeAndCityCodeAndAreaCodeAndStreetCode(provinceCode, cityCode, areaCode, streetCode));
    }

    @Override
    public DubboResponse<AtlasModel> getAtlasModelByLngLatOrIp(String lngLat, String ip) {
        return null;
    }
}
