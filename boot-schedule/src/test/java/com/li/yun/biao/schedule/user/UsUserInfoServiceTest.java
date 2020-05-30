package com.li.yun.biao.schedule.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.common.model.PageResult;
import com.li.yun.biao.schedule.BaseUserTest;
import com.li.yun.biao.user.api.UsUserService;
import com.li.yun.biao.user.dto.param.query.ShUserBankCardQueryPageParam;
import com.li.yun.biao.user.model.ShUserBankCard;
import org.assertj.core.util.Lists;
import org.junit.Test;

/**
 * @Author: liyunbiao
 * @Date: 2019/11/5 6:30 PM
 * @description
 */
public class UsUserInfoServiceTest extends BaseUserTest {
    @Reference(version = "1.0.0", timeout = 3000)
    private UsUserService usUserService;

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
