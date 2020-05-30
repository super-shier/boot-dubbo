package com.li.yun.biao.user.service;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.user.api.UsAreaService;
import com.li.yun.biao.user.model.ShBaseVillage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/8 10:26 AM
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsAreaServiceTest {
    private final Logger logger = LoggerFactory.getLogger(UsAreaServiceTest.class);
    @Resource
    private UsAreaService usAreaService;

    @Test
    public void getBaseVillageList() {
        DubboResponse<List<ShBaseVillage>> response = usAreaService.getBaseVillageList(34l, 3416l, 341623l, 341623107l);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

}
