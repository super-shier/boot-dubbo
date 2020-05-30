package com.li.yun.biao.schedule.job.goods;

import com.alibaba.dubbo.config.annotation.Reference;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.goods.api.ShGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.li.yun.biao.common.utils.DateUtil.DateTimeToStr;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/1 3:41 PM
 * @description
 */
@Component
public class GoodsSchedule {
    private static final Logger logger = LoggerFactory.getLogger(GoodsSchedule.class);

    @Reference(version = "1.0.0", timeout = 3000)
    private ShGoodsService shGoodsService;

    //每隔 10 秒执行一次登录
//    @Scheduled(fixedRate = 10000)
//    public void automaticAddGoods() {
//        DubboResponse<Boolean> response = shGoodsService.automaticAddGoods();
//        logger.info("每隔 10 秒执行一次添加商品,当前时间为time:{},执行结果为flag:{}", DateTimeToStr(new Date()), response);
//    }

    //每隔 10 秒执行一次登录
    @Scheduled(fixedRate = 10000)
    public void automaticAddGoodsAddress() {
        DubboResponse<Boolean> response = shGoodsService.automaticAddGoodsAddress();
        logger.info("每隔 10 秒执行一次添加收货地址,当前时间为time:{},执行结果为flag:{}", DateTimeToStr(new Date()), response);
    }
}
