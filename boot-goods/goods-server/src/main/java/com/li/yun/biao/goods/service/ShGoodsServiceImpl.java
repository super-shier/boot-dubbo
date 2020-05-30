package com.li.yun.biao.goods.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.li.yun.biao.common.annotation.OperationLogDetail;
import com.li.yun.biao.common.enums.EnumOperationType;
import com.li.yun.biao.common.enums.EnumOperationUnit;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.common.utils.DateUtil;
import com.li.yun.biao.common.utils.StringUtil;
import com.li.yun.biao.goods.api.ShGoodsService;
import com.li.yun.biao.goods.cache.CacheService;
import com.li.yun.biao.goods.dao.*;
import com.li.yun.biao.goods.dto.param.query.*;
import com.li.yun.biao.goods.model.*;
import com.li.yun.biao.goods.repository.*;
import com.li.yun.biao.goods.task.Task;
import com.li.yun.biao.user.api.UsAreaService;
import com.li.yun.biao.user.api.UsUserService;
import com.li.yun.biao.user.dto.param.query.ShUserInfoQueryPageParam;
import com.li.yun.biao.user.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.*;

@org.springframework.stereotype.Service("shGoodsService")
@Service(version = "1.0.0", timeout = 3000, interfaceClass = ShGoodsService.class)
public class ShGoodsServiceImpl implements ShGoodsService {
    private final Logger logger = LoggerFactory.getLogger(ShGoodsServiceImpl.class);
    @Resource
    private ShGoodsPhotoWallDao shGoodsPhotoWallDao;
    @Resource
    private ShGoodsCategoryDao shGoodsCategoryDao;
    @Resource
    private ShGoodsAddressDao shGoodsAddressDao;
    @Resource
    private ShGoodsDetailDao shGoodsDetailDao;
    @Resource
    private ShGoodsDao shGoodsDao;
    @Resource
    private ShGoodsAddressRepository shGoodsAddressRepository;
    @Resource
    private ShGoodsCategoryRepository shGoodsCategoryRepository;
    @Resource
    private ShGoodsDetailRepository shGoodsDetailRepository;
    @Resource
    private ShGoodsPhotoWallRepository shGoodsPhotoWallRepository;
    @Resource
    private ShGoodsRepository shGoodsRepository;
    @Resource
    private CacheService cacheService;
    @Resource
    private Task task;
    @Reference(version = "1.0.0", timeout = 3000)
    private UsUserService usUserService;
    @Reference(version = "1.0.0", timeout = 3000)
    private UsAreaService usAreaService;
    private static final Random random = new Random();


    @Override
    public DubboResponse<Boolean> automaticAddGoods() {
        List<ShGoodsCategory> categoryList = cacheService.getGoodsCategory();
        if (Objects.isNull(categoryList) || categoryList.isEmpty()) {
            logger.info("******分类信息获取失败");
            return new DubboResponse<>(false);
        }
        ShGoodsCategory category = categoryList.get(random.nextInt(categoryList.size()));
        Long total = shGoodsDao.queryGoodsCountByCategoryId(category.getId());
        ShGoods shGoods = new ShGoods();
        if (Objects.isNull(total)) total = 0L;
        shGoods.setName(category.getName() + total);
        shGoods.setCategoryId(category.getId());
        shGoods.setStatus(1);
        shGoods.setSummary(category.getName() + total + "测试");
        shGoods.setDescription(category.getName() + total + "测试" + DateUtil.DateTimeToStr(new Date()));
        total = shGoodsDao.queryGoodsCountByCategoryId(null);
        if (Objects.isNull(total)) total = 0L;
        shGoods.setSort(total.intValue());
        shGoods.setShowCase(random.nextInt(2));
        shGoods.setWarranty(1);
        shGoods.setDiscount((100 - random.nextInt(10)) / 100.0f);
        shGoods.setCreateTime(new Date());
        shGoods.setModifyTime(new Date());
        addGoods(shGoods);
        task.addCategoryGoodCount(category.getId(), 1);
        return new DubboResponse<>(true);
    }

    @Override
    public DubboResponse<Long> addGoods(ShGoods goods) {
        return new DubboResponse<>(shGoodsDao.save(goods).getId());
    }

    @Override
    public DubboResponse<Long> updateGoods(ShGoods goods) {
        return new DubboResponse<>(shGoodsDao.saveAndFlush(goods).getId());
    }

    @Override
    public DubboResponse<ShGoods> selectGoodsById(Long id) {
        return new DubboResponse<>(shGoodsDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<PageResult<ShGoods>> getGoodsResultByQuery(ShGoodsQueryPageParam queryParam) {
        Specification<ShGoods> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryParam.getId()));
                predicateList.add(predicate);
            }
            if (!StringUtils.isEmpty(queryParam.getName())) {
                predicateList.add(cb.like(root.get("name"), "%" + queryParam.getName() + "%"));
            }
            if (Objects.nonNull(queryParam.getStatus())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("status"), queryParam.getStatus()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getStatusList())) {
                //in 查询
                predicateList.add(cb.in(root.get("status")).value(queryParam.getStatusList()));
            }
            if (Objects.nonNull(queryParam.getCategoryId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("categoryId"), queryParam.getCategoryId()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getCategoryIdList())) {
                predicateList.add(cb.in(root.get("categoryId")).value(queryParam.getCategoryIdList()));
            }
            if (Objects.nonNull(queryParam.getShowCase())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("showCase"), queryParam.getShowCase()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getWarranty())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("warranty"), queryParam.getWarranty()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), queryParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), queryParam.getEndCreateTime()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryParam.getFieldName()) ? Lists.newArrayList("id") : queryParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryParam.getPage() - 1, queryParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShGoods>(shGoodsRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Boolean> automaticAddGoodsCategory() {
        return new DubboResponse<>(true);
    }

    @Override
    public DubboResponse<Long> addGoodsCategory(ShGoodsCategory goodsCategory) {
        return new DubboResponse<>(shGoodsCategoryDao.save(goodsCategory).getId());
    }

    @Override
    public DubboResponse<Long> updateGoodsCategory(ShGoodsCategory goodsCategory) {
        return new DubboResponse<>(shGoodsCategoryDao.saveAndFlush(goodsCategory).getId());
    }

    @Override
    public DubboResponse<ShGoodsCategory> selectGoodsCategoryById(Long id) {
        return new DubboResponse<>(shGoodsCategoryDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<PageResult<ShGoodsCategory>> getGoodsCategoryResultByQuery(ShGoodsCategoryQueryPageParam queryParam) {
        Specification<ShGoodsCategory> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryParam.getId()));
                predicateList.add(predicate);
            }
            if (!StringUtils.isEmpty(queryParam.getName())) {
                predicateList.add(cb.like(root.get("name"), "%" + queryParam.getName() + "%"));
            }
            if (Objects.nonNull(queryParam.getPid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("pid"), queryParam.getPidList()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getPidList())) {
                //in 查询
                predicateList.add(cb.in(root.get("pid")).value(queryParam.getPidList()));
            }
            if (Objects.nonNull(queryParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), queryParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), queryParam.getEndCreateTime()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryParam.getFieldName()) ? Lists.newArrayList("id") : queryParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryParam.getPage() - 1, queryParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShGoodsCategory>(shGoodsCategoryRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Boolean> automaticAddGoodsDetail() {
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<Long> addGoodsDetail(ShGoodsDetail goodsDetail) {
        return new DubboResponse<>(shGoodsDetailDao.save(goodsDetail).getId());
    }

    @Override
    public DubboResponse<Long> updateGoodsDetail(ShGoodsDetail goodsDetail) {
        return new DubboResponse<>(shGoodsDetailDao.saveAndFlush(goodsDetail).getId());
    }

    @Override
    public DubboResponse<ShGoodsDetail> selectGoodsDetailById(Long id) {
        return new DubboResponse<>(shGoodsDetailDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<List<ShGoodsDetail>> getGoodsDetailList(Long goodsId) {
        return new DubboResponse<>(shGoodsDetailDao.findByGoodsId(goodsId));
    }

    @Override
    public DubboResponse<PageResult<ShGoodsDetail>> getGoodsDetailResultByQuery(ShGoodsDetailQueryPageParam queryParam) {
        Specification<ShGoodsDetail> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getGoodsId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("goodsId"), queryParam.getGoodsId()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getGoodsIdList())) {
                predicateList.add(cb.in(root.get("goodsId")).value(queryParam.getGoodsIdList()));
            }
            if (Objects.nonNull(queryParam.getMinPrice())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("price"), queryParam.getMinPrice()));
            }
            if (Objects.nonNull(queryParam.getMaxPrice())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("price"), queryParam.getMaxPrice()));
            }
            if (Objects.nonNull(queryParam.getNumber())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("number"), queryParam.getNumber()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getStatus())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("status"), queryParam.getStatus()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getStatusList())) {
                //in 查询
                predicateList.add(cb.in(root.get("status")).value(queryParam.getStatusList()));
            }
            if (Objects.nonNull(queryParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), queryParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), queryParam.getEndCreateTime()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryParam.getFieldName()) ? Lists.newArrayList("id") : queryParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryParam.getPage() - 1, queryParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShGoodsDetail>(shGoodsDetailRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Boolean> automaticAddGoodsPhotoWall() {
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<Long> addGoodsPhotoWall(ShGoodsPhotoWall goodsPhotoWall) {
        return new DubboResponse<>(shGoodsPhotoWallDao.save(goodsPhotoWall).getId());
    }

    @Override
    public DubboResponse<Long> updateGoodsPhotoWall(ShGoodsPhotoWall goodsPhotoWall) {
        return new DubboResponse<>(shGoodsPhotoWallDao.saveAndFlush(goodsPhotoWall).getId());
    }

    @Override
    public DubboResponse<ShGoodsPhotoWall> selectGoodsPhotoWallById(Long id) {
        return new DubboResponse<>(shGoodsPhotoWallDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<PageResult<ShGoodsPhotoWall>> getGoodsPhotoWallResultByQuery(ShGoodsPhotoWallQueryPageParam queryParam) {
        Specification<ShGoodsPhotoWall> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getGoodId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("goodId"), queryParam.getGoodId()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getGoodIdList())) {
                predicateList.add(cb.in(root.get("goodId")).value(queryParam.getGoodIdList()));
            }
            if (Objects.nonNull(queryParam.getCategoryId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("categoryId"), queryParam.getCategoryId()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getCategoryIdList())) {
                predicateList.add(cb.in(root.get("categoryId")).value(queryParam.getCategoryIdList()));
            }
            if (Objects.nonNull(queryParam.getActionType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("actionType"), queryParam.getActionType()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getStatus())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("status"), queryParam.getStatus()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getStatusList())) {
                //in 查询
                predicateList.add(cb.in(root.get("status")).value(queryParam.getStatusList()));
            }
            if (Objects.nonNull(queryParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), queryParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), queryParam.getEndCreateTime()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryParam.getFieldName()) ? Lists.newArrayList("id") : queryParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryParam.getPage() - 1, queryParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShGoodsPhotoWall>(shGoodsPhotoWallRepository.findAll(specification, pageRequest)));
    }

    @Override
    @OperationLogDetail(detail = "[{{goodsAddress}}]地址用户", level = 3, operationUnit = EnumOperationUnit.USER, operationType = EnumOperationType.INSERT)
    public DubboResponse<Boolean> automaticAddGoodsAddress() {
        //获取省份信息
        DubboResponse<List<ShBaseProvince>> provinceListResponse = usAreaService.getBaseProvinceList();
        if (!provinceListResponse.getSuccess()) {
            return new DubboResponse<>(provinceListResponse.getRespCode(), provinceListResponse.getRespMsg());
        }
        List<ShBaseProvince> provinceList = provinceListResponse.getResponseObject();
        if (CollectionUtils.isEmpty(provinceList)) {
            return new DubboResponse<>(false);
        }
        ShBaseProvince baseProvince = provinceList.get(random.nextInt(provinceList.size()));
        Long provinceCode = baseProvince.getCode();
        String provinceName = baseProvince.getName();

        //获取城市信息
        DubboResponse<List<ShBaseCity>> cityListResponse = usAreaService.getBaseCityList(provinceCode);
        if (!cityListResponse.getSuccess()) {
            return new DubboResponse<>(cityListResponse.getRespCode(), cityListResponse.getRespMsg());
        }
        List<ShBaseCity> cityList = cityListResponse.getResponseObject();
        if (CollectionUtils.isEmpty(cityList)) {
            return new DubboResponse<>(false);
        }
        ShBaseCity baseCity = cityList.get(random.nextInt(cityList.size()));
        Long cityCode = baseCity.getCode();
        String cityName = baseCity.getName();

        //获取地区信息
        DubboResponse<List<ShBaseArea>> areaListResponse = usAreaService.getBaseAreaList(provinceCode, cityCode);
        if (!areaListResponse.getSuccess()) {
            return new DubboResponse<>(areaListResponse.getRespCode(), areaListResponse.getRespMsg());
        }
        List<ShBaseArea> areaList = areaListResponse.getResponseObject();
        if (CollectionUtils.isEmpty(areaList)) {
            return new DubboResponse<>(false);
        }
        ShBaseArea baseArea = areaList.get(random.nextInt(areaList.size()));
        Long areaCode = baseArea.getCode();
        String areaName = baseArea.getName();

        //获取乡镇信息
        DubboResponse<List<ShBaseStreet>> streetListResponse = usAreaService.getBaseStreetList(provinceCode, cityCode, areaCode);
        if (!streetListResponse.getSuccess()) {
            return new DubboResponse<>(streetListResponse.getRespCode(), streetListResponse.getRespMsg());
        }
        List<ShBaseStreet> streetList = streetListResponse.getResponseObject();
        if (CollectionUtils.isEmpty(streetList)) {
            return new DubboResponse<>(false);
        }
        ShBaseStreet street = streetList.get(random.nextInt(streetList.size()));
        Long streetCode = street.getCode();
        String streetName = street.getName();

        //获取村委会信息
        DubboResponse<List<ShBaseVillage>> villageListResponse = usAreaService.getBaseVillageList(provinceCode, cityCode, areaCode, streetCode);
        if (!villageListResponse.getSuccess()) {
            return new DubboResponse<>(villageListResponse.getRespCode(), villageListResponse.getRespMsg());
        }
        List<ShBaseVillage> villageList = villageListResponse.getResponseObject();
        if (CollectionUtils.isEmpty(villageList)) {
            return new DubboResponse<>(false);
        }
        ShBaseVillage village = villageList.get(random.nextInt(villageList.size()));
        String villageName = village.getName();

        //查找用户信息
        ShUserInfoQueryPageParam queryPageParam = new ShUserInfoQueryPageParam();
        queryPageParam.setPage(1);
        queryPageParam.setPageSize(50);
        queryPageParam.setFieldName(Lists.newArrayList("modifyTime"));
        queryPageParam.setDesc(false);
        DubboResponse<PageResult<ShUserInfo>> userResponse = usUserService.queryUserInfoPageResult(queryPageParam);
        if (!userResponse.getSuccess()) {
            return new DubboResponse<>(userResponse.getRespCode(), userResponse.getRespMsg());
        }
        List<ShUserInfo> userInfoList = (List<ShUserInfo>) userResponse.getResponseObject().getDataList();
        if (CollectionUtils.isEmpty(userInfoList)) {
            return new DubboResponse<>(false);
        }
        ShUserInfo userInfo = userInfoList.get(random.nextInt(userInfoList.size()));

        //创建地址
        ShGoodsAddress goodsAddress = new ShGoodsAddress();
        goodsAddress.setUid(userInfo.uid);
        goodsAddress.setMobile(userInfo.getMobile());
        goodsAddress.setConsignee(userInfo.getName());
        goodsAddress.setProvinceCode(provinceCode);
        goodsAddress.setProvinceName(provinceName);
        goodsAddress.setCityCode(cityCode);
        goodsAddress.setCityName(cityName);
        goodsAddress.setAreaCode(areaCode);
        goodsAddress.setAreaName(areaName);
        goodsAddress.setAddress(streetName + villageName);
        goodsAddress.setCreateTime(new Date());
        goodsAddress.setModifyTime(goodsAddress.getCreateTime());
        return new DubboResponse<>(shGoodsAddressDao.save(goodsAddress).getId() > 0);
    }

    @Override
    public DubboResponse<Long> addGoodsAddress(ShGoodsAddress goodsAddress) {
        return new DubboResponse<>(shGoodsAddressDao.save(goodsAddress).getId());
    }

    @Override
    public DubboResponse<Long> updateGoodsAddress(ShGoodsAddress goodsAddress) {
        return new DubboResponse<>(shGoodsAddressDao.saveAndFlush(goodsAddress).getId());
    }

    @Override
    public DubboResponse<Boolean> updateGoodsAddressNotDefault(Long uid) {
        int index = shGoodsAddressDao.updateGoodsAddressNotDefault(uid);
        return new DubboResponse<>(index > 0);
    }

    @Override
    public DubboResponse<Boolean> updateGoodsAddressDefault(Long id) {
        int index = shGoodsAddressDao.updateGoodsAddressDefault(id);
        return new DubboResponse<>(index > 0);
    }

    @Override
    public DubboResponse<ShGoodsAddress> selectGoodsAddressById(Long id) {
        return new DubboResponse<>(shGoodsAddressDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<ShGoodsAddress> selectDefaultGoodsAddressByUid(Long uid) {
        return new DubboResponse<>(shGoodsAddressDao.findDefaultGoodsAddressByUid(uid));
    }

    @Override
    public DubboResponse<List<ShGoodsAddress>> selectGoodsAddressByUid(Long uid, Integer status) {
        return new DubboResponse<>(shGoodsAddressDao.findByUidAndStatus(uid, status));
    }

    @Override
    public DubboResponse<PageResult<ShGoodsAddress>> getGoodsAddressResultByQuery(ShGoodsAddressQueryPageParam queryParam) {
        Specification<ShGoodsAddress> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getUid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("uid"), queryParam.getUid()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getUidList())) {
                predicateList.add(cb.in(root.get("uid")).value(queryParam.getUidList()));
            }
            if (StringUtil.isNotBlank(queryParam.getConsignee())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("consignee"), queryParam.getConsignee()));
                predicateList.add(predicate);
            }
            if (StringUtil.isNotBlank(queryParam.getMobile())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("mobile"), queryParam.getMobile()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getProvinceCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("provinceCode"), queryParam.getProvinceCode()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getCityCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("cityCode"), queryParam.getCityCode()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getAreaCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("areaCode"), queryParam.getAreaCode()));
                predicateList.add(predicate);
            }
            if (StringUtil.isNotBlank(queryParam.getPostCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("postCode"), queryParam.getPostCode()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryParam.getStatus())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("status"), queryParam.getStatus()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryParam.getStatusList())) {
                //in 查询
                predicateList.add(cb.in(root.get("status")).value(queryParam.getStatusList()));
            }
            if (Objects.nonNull(queryParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("createTime"), queryParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("createTime"), queryParam.getEndCreateTime()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryParam.getFieldName()) ? Lists.newArrayList("id") : queryParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryParam.getPage() - 1, queryParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShGoodsAddress>(shGoodsAddressRepository.findAll(specification, pageRequest)));
    }
}
