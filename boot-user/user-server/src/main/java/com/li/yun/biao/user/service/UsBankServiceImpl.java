package com.li.yun.biao.user.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.collect.Lists;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.user.api.UsBankService;
import com.li.yun.biao.user.dao.ShBankCardDao;
import com.li.yun.biao.user.dao.ShBankLianhanghaoDao;
import com.li.yun.biao.user.dto.param.query.ShBankCardQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShBankLianHangHaoQueryPageParam;
import com.li.yun.biao.user.model.ShBankCard;
import com.li.yun.biao.user.model.ShBankLianhanghao;
import com.li.yun.biao.user.repository.ShBankCardRepository;
import com.li.yun.biao.user.repository.ShBankLianhanghaoRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service("usBankService")
@Service(version = "1.0.0", timeout = 3000, interfaceClass = UsBankService.class)
public class UsBankServiceImpl implements UsBankService {
    @Resource
    private ShBankCardDao shBankCardDao;
    @Resource
    private ShBankLianhanghaoDao shBankLianhanghaoDao;
    @Resource
    private ShBankCardRepository shBankCardRepository;
    @Resource
    private ShBankLianhanghaoRepository shBankLianhanghaoRepository;

    @Override
    public DubboResponse<Long> addBankCard(ShBankCard bankCard) {
        return new DubboResponse<>(shBankCardDao.save(bankCard).getId());
    }

    @Override
    public DubboResponse<Long> updateBankCard(ShBankCard bankCard) {
        return new DubboResponse<>(shBankCardDao.saveAndFlush(bankCard).getId());
    }

    @Override
    public DubboResponse<Void> deleteBankCard(Long id) {
        shBankCardDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShBankCard> getBankCardById(Long id) {
        return new DubboResponse<>(shBankCardDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<ShBankCard> getBankCardByBin(String bin) {
        return new DubboResponse<>(shBankCardDao.findByBin(bin));
    }

    @Override
    public DubboResponse<ShBankCard> getBankCardByCardNo(String cardNo) {
        if (StringUtils.isBlank(cardNo) || cardNo.length() < 6) return null;
        return new DubboResponse<>(shBankCardDao.findByBinAndCardNoLength(cardNo.substring(0, 6), cardNo.length()));
    }

    @Override
    public DubboResponse<PageResult<ShBankCard>> getBankCardResult(ShBankCardQueryPageParam queryPageParam) {
        Specification<ShBankCard> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (Objects.nonNull(queryPageParam.getBankId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bank_id"), queryPageParam.getBankId()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getBankCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bank_code"), queryPageParam.getBankCode()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getBankName())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bank_name"), queryPageParam.getBankName()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getBranchCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("branch_code"), queryPageParam.getBranchCode()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getBin())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bin"), queryPageParam.getBin()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getCardType())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("card_type"), queryPageParam.getCardType()));
                predicateList.add(predicate);
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShBankCard>(shBankCardRepository.findAll(specification, pageRequest)));
    }

    @Override
    public DubboResponse<Long> addBankLianhanghao(ShBankLianhanghao bankLianhanghao) {
        return new DubboResponse<>(shBankLianhanghaoDao.save(bankLianhanghao).getId());
    }

    @Override
    public DubboResponse<Long> updateBankLianhanghao(ShBankLianhanghao bankLianhanghao) {
        return new DubboResponse<>(shBankLianhanghaoDao.saveAndFlush(bankLianhanghao).getId());
    }

    @Override
    public DubboResponse<Void> deleteBankLianhanghao(Long id) {
        shBankLianhanghaoDao.deleteById(id);
        return new DubboResponse<>();
    }

    @Override
    public DubboResponse<ShBankLianhanghao> getBankLianhanghaoById(Long id) {
        return new DubboResponse<>(shBankLianhanghaoDao.findById(id).orElse(null));
    }

    @Override
    public DubboResponse<ShBankLianhanghao> getLianhanghaoByBankNumber(String bankNumber) {
        return new DubboResponse<>(shBankLianhanghaoDao.findByBankNumber(bankNumber));
    }

    @Override
    public DubboResponse<ShBankLianhanghao> getLianhanghaoByBankName(String bankName) {
        return new DubboResponse<>(shBankLianhanghaoDao.findByBankName(bankName));
    }

    @Override
    public DubboResponse<PageResult<ShBankLianhanghao>> getBankLianhanghaoResult(ShBankLianHangHaoQueryPageParam queryPageParam) {
        Specification<ShBankLianhanghao> specification = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (Objects.nonNull(queryPageParam.getBankId())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bank_id"), queryPageParam.getBankId()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getBank())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bank"), queryPageParam.getBank()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getBankNumber())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("bank_number"), queryPageParam.getBankNumber()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getProvinceCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("province_code"), queryPageParam.getProvinceCode()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getCityCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("city_code"), queryPageParam.getCityCode()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getAreaCode())) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("area_code"), queryPageParam.getAreaCode()));
                predicateList.add(predicate);
            }
            if (StringUtils.isNotBlank(queryPageParam.getAddress())) {
                predicateList.add(cb.like(root.get("address"), "%" + queryPageParam.getAddress() + "%"));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
        Sort sort = new Sort(queryPageParam.isDesc() ? Sort.Direction.DESC : Sort.Direction.ASC, (CollectionUtils.isEmpty(queryPageParam.getFieldName()) ? Lists.newArrayList("id") : queryPageParam.getFieldName()));
        PageRequest pageRequest = PageRequest.of(queryPageParam.getPage() - 1, queryPageParam.getPageSize(), sort);
        return new DubboResponse<>(new PageResult<ShBankLianhanghao>(shBankLianhanghaoRepository.findAll(specification, pageRequest)));
    }
}
