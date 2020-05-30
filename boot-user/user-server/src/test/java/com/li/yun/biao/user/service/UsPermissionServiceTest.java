package com.li.yun.biao.user.service;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.enums.EnumPermissionMenuType;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.user.BaseUserTest;
import com.li.yun.biao.user.api.UsPermissionService;
import com.li.yun.biao.user.model.ShUserMenuVo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/12 1:51 PM
 * @description
 */
public class UsPermissionServiceTest extends BaseUserTest {
    private static final Logger logger = LoggerFactory.getLogger(UsPermissionServiceTest.class);
    @Resource
    private UsPermissionService usPermissionService;

    @Test
    public void getPermCode() {
        DubboResponse<List<String>> response = usPermissionService.getPermCode(1000000l, 0);
        logger.info("********response:{}", response);
    }

    @Test
    public void getUserMenuVoByUid() {
        DubboResponse<List<ShUserMenuVo>> response = usPermissionService.getUserMenuVoByUid(1000000l, EnumPermissionMenuType.JAVA_WEB.type);
        logger.info("********response:{}", JSON.toJSONString(response));
    }
}
