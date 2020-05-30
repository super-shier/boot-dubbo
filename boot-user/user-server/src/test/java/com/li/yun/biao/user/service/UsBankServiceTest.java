package com.li.yun.biao.user.service;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.user.api.UsBankService;
import com.li.yun.biao.user.model.ShBankCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/8 10:43 AM
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsBankServiceTest {
    private final Logger logger = LoggerFactory.getLogger(UsAreaServiceTest.class);
    @Resource
    private UsBankService usBankService;

    @Test
    public void getBankCardByCardNo() {
        String cardNo = "6223478137404360";
        DubboResponse<ShBankCard> response = usBankService.getBankCardByCardNo(cardNo);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

}
