package com.li.yun.biao.user.service;

import cn.binarywang.tools.generator.BankCardNumberGenerator;
import cn.binarywang.tools.generator.ChineseIDCardNumberGenerator;
import cn.binarywang.tools.generator.ChineseMobileNumberGenerator;
import cn.binarywang.tools.generator.ChineseNameGenerator;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.li.yun.biao.common.annotation.OperationLogDetail;
import com.li.yun.biao.common.enums.EnumOperationType;
import com.li.yun.biao.common.enums.EnumOperationUnit;
import com.li.yun.biao.common.model.AtlasModel;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.common.utils.DateUtil;
import com.li.yun.biao.common.utils.MD5Util;
import com.li.yun.biao.common.utils.MapToolsUtil;
import com.li.yun.biao.user.api.UsUserService;
import com.li.yun.biao.user.common.cache.CacheService;
import com.li.yun.biao.user.common.rabbitmq.UserMqProducer;
import com.li.yun.biao.user.dao.ShUserBankCardDao;
import com.li.yun.biao.user.dao.ShUserInfoDao;
import com.li.yun.biao.user.dao.ShUserLoginRecordDao;
import com.li.yun.biao.user.dto.param.query.ShUserBankCardQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserInfoQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserLoginRecordQueryPageParam;
import com.li.yun.biao.user.model.ShBankCard;
import com.li.yun.biao.user.model.ShUserBankCard;
import com.li.yun.biao.user.model.ShUserInfo;
import com.li.yun.biao.user.model.ShUserLoginRecord;
import com.li.yun.biao.user.repository.ShUserBankCardRepository;
import com.li.yun.biao.user.repository.ShUserInfoRepository;
import com.li.yun.biao.user.repository.ShUserLoginRecordRepository;
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

@org.springframework.stereotype.Service("usUserService")
@Service(version = "1.0.0", timeout = 3000, interfaceClass = UsUserService.class)
public class UsUserServiceImpl implements UsUserService {
    private static final Logger logger = LoggerFactory.getLogger(UsUserServiceImpl.class);
    private static final Random random = new Random();
    private static final String CREATE_TIME = "createTime";
    private static final String TABLE_STATUS = "status";
    @Resource
    private CacheService cacheService;
    @Resource
    private ShUserInfoDao shUserInfoDao;
    @Resource
    private ShUserBankCardDao shUserBankCardDao;
    @Resource
    private ShUserLoginRecordDao shUserLoginRecordDao;
    @Resource
    private ShUserInfoRepository shUserInfoRepository;
    @Resource
    private ShUserLoginRecordRepository shUserLoginRecordRepository;
    @Resource
    private ShUserBankCardRepository shUserBankCardRepository;
    @Resource
    private UserMqProducer userMqProducer;

    @Override
    public DubboResponse<Boolean> automaticAddUserInfo() {
        ShUserInfo userInfo = new ShUserInfo();
        String mobile = ChineseMobileNumberGenerator.getInstance().generate();      //手机号
        String idNumber = ChineseIDCardNumberGenerator.getInstance().generate();    //身份证号
        String password = idNumber.substring(idNumber.length() - 6);
        String name = ChineseNameGenerator.getInstance().generate();
        while (name.length() > 3) {
            name = ChineseNameGenerator.getInstance().generate();
        }
        userInfo.setMobile(mobile);
        userInfo.setName(name);
        userInfo.setPassWord(MD5Util.encode(password));
        userInfo.setIdNumber(idNumber);
        userInfo.setStatus(1);
        userInfo.setLevel(1);
        String ip = MapToolsUtil.getRandomIp();
        AtlasModel atlasModel = MapToolsUtil.getAddressByIp(ip);
        String address = Objects.nonNull(atlasModel) ? atlasModel.getAddress() : "";
        String lonLat = MapToolsUtil.randomLonLat();
        atlasModel = MapToolsUtil.getAddressByGaoDe(lonLat);
        userInfo.setAddress(address);
        if (Objects.nonNull(atlasModel)) userInfo.setRegisterAddress(atlasModel.toString());
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        Long index = shUserInfoDao.save(userInfo).getUid();
        return new DubboResponse<>(index.compareTo(0L) > 0);
    }

    @OperationLogDetail(detail = "[{{userInfo}}]新增用户", level = 3, operationUnit = EnumOperationUnit.USER, operationType = EnumOperationType.INSERT)
    @Override
    public DubboResponse<Long> addUserInfo(ShUserInfo userInfo) {
        return new DubboResponse<>(shUserInfoDao.save(userInfo).getUid());
    }

    @Override
    public DubboResponse<Long> updateUserInfo(ShUserInfo shUserInfo) {
        return new DubboResponse<>(shUserInfoDao.saveAndFlush(shUserInfo).getUid());
    }

    @Override
    public DubboResponse<Void> deleteUserInfo(Long uid) {
        shUserInfoDao.deleteById(uid);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShUserInfo> getUserInfoByUid(Long uid) {
        return new DubboResponse<>(shUserInfoDao.findById(uid).orElse(null));
    }

    @Override
    public DubboResponse<ShUserInfo> getUserInfoByMobile(String mobile, String passWord) {
        return new DubboResponse<>(shUserInfoDao.findByMobileAndPassWord(mobile, passWord));
    }

    @OperationLogDetail(detail = "[{{queryParam}}]分页查询", level = 3, operationUnit = EnumOperationUnit.USER, operationType = EnumOperationType.SELECT)
    @Override
    public DubboResponse<PageResult<ShUserInfo>> queryUserInfoPageResult(ShUserInfoQueryPageParam queryParam) {
        Specification<ShUserInfo> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryParam.getUid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("uid"), queryParam.getUid()));
                predicateList.add(predicate);
            }
            if (!StringUtils.isEmpty(queryParam.getMobile())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("mobile"), queryParam.getMobile()));
                predicateList.add(predicate);
            }
            if (!StringUtils.isEmpty(queryParam.getStatus())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get(TABLE_STATUS), queryParam.getStatus()));
                predicateList.add(predicate);
            }

            if (!StringUtils.isEmpty(queryParam.getName())) {
                predicateList.add(cb.like(root.get("name"), "%" + queryParam.getName() + "%"));
            }
            if (Objects.nonNull(queryParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get(CREATE_TIME), queryParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get(CREATE_TIME), queryParam.getEndCreateTime()));
            }
            if (!CollectionUtils.isEmpty(queryParam.getUidList())) {
                //in 查询
                predicateList.add(cb.in(root.get("uid")).value(queryParam.getUidList()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryParam.getFieldName()) ? Lists.newArrayList("uid") : queryParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryParam.getPage() - 1, queryParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShUserInfo>(shUserInfoRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Boolean> automaticAddUserLoginRecord() {
        List<ShUserInfo> userInfoList = cacheService.getUserInfoList();
        if (Objects.isNull(userInfoList) || userInfoList.isEmpty()) return new DubboResponse<>(false);
        String lonLat = MapToolsUtil.randomLonLat();
        AtlasModel atlasModel = MapToolsUtil.getAddress(lonLat);
        if (Objects.isNull(atlasModel)) atlasModel = MapToolsUtil.getAddressByIp(MapToolsUtil.getRandomIp());
        if (Objects.isNull(atlasModel)) return new DubboResponse<>(false);
        ShUserLoginRecord userLoginRecord = new ShUserLoginRecord();
        ShUserInfo userInfo = userInfoList.get(random.nextInt(userInfoList.size()));
        userLoginRecord.setUid(userInfo.getUid());
        userLoginRecord.setLoginAddress(atlasModel.toString());
        userLoginRecord.setLoginTime(new Date());
        userLoginRecord.setReallyName(userInfo.getName());
        //ShGoods shGoods=goodsService.selectGoodsById(2);
        //logger.info("*******goods:{}",shGoods.getDescription());
        userMqProducer.sendDirectMsg(JSON.toJSONString(userLoginRecord));
        return new DubboResponse<>(shUserLoginRecordDao.save(userLoginRecord).getId() > 0);
    }


    @Override
    public DubboResponse<Long> addUserLoginRecord(ShUserLoginRecord loginRecord) {
        return new DubboResponse<>(shUserLoginRecordDao.save(loginRecord).getId());
    }

    @Override
    public DubboResponse<Long> updateUserLoginRecord(ShUserLoginRecord loginRecord) {
        return new DubboResponse<>(shUserLoginRecordDao.saveAndFlush(loginRecord).getId());
    }

    @Override
    public DubboResponse<Void> deleteUserLoginRecord(Long id) {
        shUserLoginRecordDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShUserLoginRecord> getUserFirstLoginRecord(Long uid, Boolean isLast) {
        if (Objects.nonNull(isLast) && isLast) {
            return new DubboResponse<>(shUserLoginRecordDao.findLastUserLoginRecord(uid));
        }
        return new DubboResponse<>(shUserLoginRecordDao.findFirstUserLoginRecord(uid));
    }

    @Override
    public DubboResponse<PageResult<ShUserLoginRecord>> getLoginRecordResultByQuery(ShUserLoginRecordQueryPageParam queryPageParam) {
        Specification<ShUserInfo> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryPageParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryPageParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getUid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("uid"), queryPageParam.getUid()));
                predicateList.add(predicate);
            }
            if (!StringUtils.isEmpty(queryPageParam.getReallyName())) {
                predicateList.add(cb.like(root.get("reallyName"), "%" + queryPageParam.getReallyName() + "%"));
            }
            if (!StringUtils.isEmpty(queryPageParam.getLoginAddress())) {
                predicateList.add(cb.like(root.get("loginAddress"), "%" + queryPageParam.getLoginAddress() + "%"));
            }
            if (Objects.nonNull(queryPageParam.getStartLoginTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get("loginTime"), queryPageParam.getStartLoginTime()));
            }
            if (Objects.nonNull(queryPageParam.getEndLoginTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get("loginTime"), queryPageParam.getEndLoginTime()));
            }
            if (!CollectionUtils.isEmpty(queryPageParam.getUidList())) {
                //in 查询
                predicateList.add(cb.in(root.get("uid")).value(queryPageParam.getUidList()));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShUserLoginRecord>(shUserLoginRecordRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Boolean> automaticAddUserBankCard() {
        if (random.nextBoolean()) return new DubboResponse<>(false);
        List<ShUserInfo> userInfoList = cacheService.getUserInfoList();
        if (Objects.isNull(userInfoList) || userInfoList.isEmpty()) {
            logger.info("添加银行卡异常=====================用户信息获取失败");
            return new DubboResponse<>(false);
        }
        ShUserInfo userInfo = userInfoList.get(random.nextInt(userInfoList.size()));
        List<ShBankCard> bankCards = cacheService.getBankCardList();
        if (Objects.isNull(bankCards) || bankCards.isEmpty()) return new DubboResponse<>(false);
        ShBankCard bankCard = bankCards.get(random.nextInt(bankCards.size()));
        ShUserBankCard userBankCard = new ShUserBankCard();
        String cardNo = BankCardNumberGenerator.generateByPrefix(Integer.valueOf(bankCard.getBin()));
        userBankCard.setStatus(1);
        userBankCard.setUid(userInfo.getUid());
        userBankCard.setAccountName(userInfo.getName());
        userBankCard.setBankCode(bankCard.getBankCode());
        userBankCard.setBankName(bankCard.getBankName());
        userBankCard.setBin(bankCard.getBin());
        userBankCard.setCardName(bankCard.getCardName());
        userBankCard.setCardNo(cardNo);
        userBankCard.setCardType(Objects.equals(bankCard.getCardType(), "借记卡") ? 1 : Objects.equals(bankCard.getCardType(), "贷记卡") ? 2 : 3);
        userBankCard.setAccountMobile(userInfo.getMobile());
        userBankCard.setIdNumber(userInfo.getIdNumber());
        userBankCard.setCreateTime(new Date());
        userBankCard.setModifyTime(new Date());
        String validDate = "";
        if (Objects.equals(2, userBankCard.getCardType())) {
            int year = Integer.valueOf(DateUtil.YearToStr(new Date())) + 5;
            int month = random.nextInt(12) + 1;
            if (month < 10) {
                validDate = month + "0" + year;
            } else {
                validDate = validDate + month + year;
            }
            String vcode = "";
            for (int i = 0; i < 3; i++) {
                vcode = vcode + random.nextInt(10);
            }
            userBankCard.setValidDate(validDate);
            userBankCard.setVCode(vcode);
        }
        return new DubboResponse<>(shUserBankCardDao.save(userBankCard).getId() > 0);
    }

    @Override
    public DubboResponse<Long> addUserBankCard(ShUserBankCard userBankCard) {
        return new DubboResponse<>(shUserBankCardDao.save(userBankCard).getId());
    }

    @Override
    public DubboResponse<Long> updateUserBankCard(ShUserBankCard userBankCard) {
        return new DubboResponse<>(shUserBankCardDao.saveAndFlush(userBankCard).getId());
    }

    @Override
    public DubboResponse<Void> deleteUserBankCard(Long id) {
        shUserBankCardDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<List<ShUserBankCard>> getUserBankCardUid(Long uid) {
        return new DubboResponse<>(shUserBankCardDao.findShUserBankCardByUid(uid));
    }

    @Override
    public DubboResponse<PageResult<ShUserBankCard>> getUserBankCardResultByQuery(ShUserBankCardQueryPageParam queryPageParam) {
        Specification<ShUserInfo> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // 创建 Predicate
            if (Objects.nonNull(queryPageParam.getId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("id"), queryPageParam.getId()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getUid())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("uid"), queryPageParam.getUid()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getStatus())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get(TABLE_STATUS), queryPageParam.getStatus()));
                predicateList.add(predicate);
            }
            if (!CollectionUtils.isEmpty(queryPageParam.getStatusList())) {
                //in 查询
                predicateList.add(cb.in(root.get(TABLE_STATUS)).value(queryPageParam.getStatusList()));
            }
            if (Objects.nonNull(queryPageParam.getCardNo())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("cardNo"), queryPageParam.getCardNo()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getCardType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("cardType"), queryPageParam.getCardType()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getAccountName())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("accountName"), queryPageParam.getAccountName()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getAccountMobile())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("accountMobile"), queryPageParam.getAccountMobile()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getIdNumber())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("idNumber"), queryPageParam.getIdNumber()));
                predicateList.add(predicate);
            }
            if (Objects.nonNull(queryPageParam.getStartCreateTime())) {
                predicateList.add(cb.greaterThanOrEqualTo(root.get(CREATE_TIME), queryPageParam.getStartCreateTime()));
            }
            if (Objects.nonNull(queryPageParam.getEndCreateTime())) {
                predicateList.add(cb.lessThanOrEqualTo(root.get(CREATE_TIME), queryPageParam.getEndCreateTime()));
            }

            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShUserBankCard>(shUserBankCardRepository.findAll(specification, pageRequest)));
    }

}
