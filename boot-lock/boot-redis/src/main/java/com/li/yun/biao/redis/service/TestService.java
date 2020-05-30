package com.li.yun.biao.redis.service;

import com.li.yun.biao.redis.annotations.DistributedLock;
import org.springframework.stereotype.Service;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 测试
 */
@Service
public class TestService {


    @DistributedLock(name = "TestService_distributedLockTest", value = "#key.concat(#value)", action = DistributedLock.LockFailAction.CONTINUE)
    public String distributedLockTest(String key, String value) throws InterruptedException {

        //Thread.sleep(5000L);
        return "distributedLockTest===========key:" + key;

    }
}
