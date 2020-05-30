package com.li.yun.biao.user.service;

import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.common.utils.MD5Util;
import com.li.yun.biao.user.BaseUserTest;
import com.li.yun.biao.user.api.UsUserService;
import com.li.yun.biao.user.dto.param.query.ShUserBankCardQueryPageParam;
import com.li.yun.biao.user.dto.param.query.ShUserInfoQueryPageParam;
import com.li.yun.biao.user.model.ShUserBankCard;
import com.li.yun.biao.user.model.ShUserInfo;
import com.li.yun.biao.user.model.ShUserLoginRecord;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/5 6:30 PM
 * @description
 */
public class UsUserInfoServiceTest extends BaseUserTest {
    private final Logger logger = LoggerFactory.getLogger(UsUserInfoServiceTest.class);
    @Resource
    private UsUserService usUserService;

    @Test
    public void addUser() {
        ShUserInfo userInfo = new ShUserInfo();
        userInfo.setMobile("18686868756");
        userInfo.setIdNumber("341227198710121517");
        userInfo.setLevel(5);
        userInfo.setName("测试啊啊啊");
        userInfo.setStatus(1);
        userInfo.setPassWord(MD5Util.encode("123456"));
        userInfo.setCreateTime(new Date());
        userInfo.setModifyTime(new Date());
        DubboResponse<Long> response = usUserService.addUserInfo(userInfo);
        logger.info("********response:{}", JSON.toJSONString(response));
    }


    @Test
    public void queryUserInfoPageResult() {
        ShUserInfoQueryPageParam queryPageParam = new ShUserInfoQueryPageParam();
        queryPageParam.setPage(1);
        queryPageParam.setPageSize(50);
        queryPageParam.setFieldName(Lists.newArrayList("idNumber"));
        queryPageParam.setDesc(false);
        queryPageParam.setEndCreateTime(new Date());
        DubboResponse<PageResult<ShUserInfo>> response = usUserService.queryUserInfoPageResult(queryPageParam);
        logger.info("********response:{}", JSON.toJSONString(response));
    }

    @Test
    public void getUserInfoByUid() {
        DubboResponse<ShUserInfo> response = usUserService.getUserInfoByUid(1000000l);
        logger.info("********userInfo:{}", JSON.toJSONString(response));
    }

    @Test
    public void getUserLoginRecord() {
        DubboResponse<ShUserLoginRecord> lastResponse = usUserService.getUserFirstLoginRecord(1000000l, true);
        DubboResponse<ShUserLoginRecord> firstResponse = usUserService.getUserFirstLoginRecord(1000000l, false);
        logger.info("********lastUserLoginRecord:{}", JSON.toJSONString(lastResponse));
        logger.info("********firstUserLoginRecord:{}", JSON.toJSONString(firstResponse));
    }


    @Test
    public void getUserBankCardResultByQuery() {
        ShUserBankCardQueryPageParam queryPageParam = new ShUserBankCardQueryPageParam();
        queryPageParam.setPage(1);
        queryPageParam.setPageSize(50);
        queryPageParam.setFieldName(Lists.newArrayList("createTime"));
        queryPageParam.setDesc(false);
        DubboResponse<PageResult<ShUserBankCard>> response = usUserService.getUserBankCardResultByQuery(queryPageParam);
        logger.info("********response:{}", JSON.toJSONString(response));
    }
}
