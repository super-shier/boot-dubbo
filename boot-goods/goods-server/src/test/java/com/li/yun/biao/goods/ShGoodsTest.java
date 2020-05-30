package com.li.yun.biao.goods;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.goods.api.ShGoodsService;
import com.li.yun.biao.goods.dto.param.query.ShGoodsQueryPageParam;
import com.li.yun.biao.goods.model.ShGoods;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/30 10:14 AM
 * @description
 */
public class ShGoodsTest extends BaseUserTest {
    @Resource
    private ShGoodsService shGoodsService;

    @Test
    public void automaticAddGoods() {
        DubboResponse<Boolean> response = shGoodsService.automaticAddGoods();
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void selectGoodsById() {
        Long goodsId = 11137L;
        DubboResponse<ShGoods> response = shGoodsService.selectGoodsById(goodsId);
        logger.info("********response:{}", JSON.toJSONString(response));
    }


    @Test
    public void updateGoods() {
        Long goodsId = 11137L;
        DubboResponse<ShGoods> response = shGoodsService.selectGoodsById(goodsId);
        if (!response.getSuccess() || Objects.isNull(response.getResponseObject())) {
            logger.info("********response:{}", JSON.toJSONString(response));
            return;
        }
        ShGoods shGoods = response.getResponseObject();
        shGoods.setSummary("88888888");
        DubboResponse<Long> updateResponse = shGoodsService.updateGoods(shGoods);
        logger.info("********updateResponse:{}", JSON.toJSONString(updateResponse));
    }

    @Test
    public void getGoodsResultByQuery() {
        ShGoodsQueryPageParam queryPageParam = new ShGoodsQueryPageParam();
        queryPageParam.setCategoryId(5L);
        queryPageParam.setName("交通22");
        DubboResponse<PageResult<ShGoods>> response = shGoodsService.getGoodsResultByQuery(queryPageParam);
        logger.info("********updateResponse:{}", JSON.toJSONString(response));
    }
}
