package com.li.yun.biao.goods.task;

import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.goods.api.ShGoodsService;
import com.li.yun.biao.goods.model.ShGoodsCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

@Component
public class Task {
    private final Logger logger = LoggerFactory.getLogger(Task.class);
    @Resource
    private ShGoodsService shGoodsService;

    @Async
    public void addCategoryGoodCount(Long id, Integer count) {
        DubboResponse<ShGoodsCategory> response = shGoodsService.selectGoodsCategoryById(id);
        if (!response.getSuccess() || Objects.isNull(response.getResponseObject())) {
            return;
        }
        ShGoodsCategory category = response.getResponseObject();
        if (Objects.isNull(category)) return;
        category.setCount(category.getCount() + count);
        category.setModifyTime(new Date());
        shGoodsService.updateGoodsCategory(category);
    }
}
