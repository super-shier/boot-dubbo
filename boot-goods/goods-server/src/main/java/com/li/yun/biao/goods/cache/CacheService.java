package com.li.yun.biao.goods.cache;

import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.goods.api.ShGoodsService;
import com.li.yun.biao.goods.dto.param.query.ShGoodsCategoryQueryPageParam;
import com.li.yun.biao.goods.model.ShGoodsCategory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheService {
    @Resource
    private ShGoodsService shGoodsService;

    @Cacheable(value = "goodsCategoryCache", unless = "#result == null")
    public List<ShGoodsCategory> getGoodsCategory() {
        DubboResponse<PageResult<ShGoodsCategory>> response = shGoodsService.getGoodsCategoryResultByQuery(new ShGoodsCategoryQueryPageParam());
        if (response.getSuccess()) {
            return (List<ShGoodsCategory>) response.getResponseObject().getDataList();
        }
        return new ArrayList<>();
    }

}
