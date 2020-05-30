package com.li.yun.biao.user.common.cache;

import com.google.common.collect.Lists;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.user.api.UsBankService;
import com.li.yun.biao.user.api.UsUserService;
import com.li.yun.biao.user.dto.param.query.ShBankCardQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserInfoQueryPageParam;
import com.li.yun.biao.user.model.ShBankCard;
import com.li.yun.biao.user.model.ShUserInfo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Component
public class CacheService {
    @Resource
    private UsBankService usBankService;
    @Resource
    private UsUserService usUserService;

    @Cacheable(value = "userInfoCache", unless = "#result == null")
    public List<ShUserInfo> getUserInfoList() {
        ShUserInfoQueryPageParam queryPageParam = new ShUserInfoQueryPageParam();
        queryPageParam.setPage(1);
        queryPageParam.setPageSize(50);
        queryPageParam.setFieldName(Lists.newArrayList("modifyTime"));
        queryPageParam.setDesc(false);
        DubboResponse<PageResult<ShUserInfo>> response = usUserService.queryUserInfoPageResult(queryPageParam);
        if (response.getSuccess()) {
            return (List<ShUserInfo>) response.getResponseObject().getDataList();
        }
        return null;
    }

    @Cacheable(value = "bankCardCache", unless = "#result == null")
    public List<ShBankCard> getBankCardList() {
        ShBankCardQueryPageParam queryPageParam = new ShBankCardQueryPageParam();
        queryPageParam.setPage(new Random().nextInt(9));
        queryPageParam.setPageSize(500);
        queryPageParam.setFieldName(Lists.newArrayList("modifyTime"));
        queryPageParam.setDesc(false);
        DubboResponse<PageResult<ShBankCard>> response = usBankService.getBankCardResult(queryPageParam);
        if (response.getSuccess()) {
            return (List<ShBankCard>) response.getResponseObject().getDataList();
        }
        return null;
    }

}
