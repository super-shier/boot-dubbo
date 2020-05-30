package com.li.yun.biao.schedule.job.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.li.yun.biao.common.model.DubboResponse;
import com.li.yun.biao.user.api.UsUserService;
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
public class UserSchedule {
    private static final Logger logger = LoggerFactory.getLogger(UserSchedule.class);
    @Reference(version = "1.0.0", timeout = 300)
    private UsUserService usUserService;

    //每隔 10 秒执行一次登录
    @Scheduled(fixedRate = 10000)
    public void automaticAddUserLoginRecord() {
        DubboResponse<Boolean> response = usUserService.automaticAddUserLoginRecord();
        logger.info("每隔 10 秒执行一次登录,当前时间为time:{},执行结果为flag:{}", DateTimeToStr(new Date()), response);
    }

    //每隔 2 分钟执行一次注册
    @Scheduled(fixedRate = 120000)
    public void automaticAddUserInfo() {
        DubboResponse<Boolean> response = usUserService.automaticAddUserInfo();
        logger.info("每隔 2 分钟执行一次注册,当前时间为time:{},执行结果为flag:{}", DateTimeToStr(new Date()), response);
    }

    //每隔 10 分钟执行一次填卡
    @Scheduled(fixedRate = 600000)
    public void queryUserInfoPageResult() {
        DubboResponse<Boolean> response = usUserService.automaticAddUserBankCard();
        logger.info("每隔 10 分钟执行一次填卡,当前时间为time:{},执行结果为flag:{}", DateTimeToStr(new Date()), response);
    }

}
