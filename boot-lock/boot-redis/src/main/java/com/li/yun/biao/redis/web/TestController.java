package com.li.yun.biao.redis.web;

import com.li.yun.biao.redis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 测试
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private TestService testService;

    @RequestMapping(value = "/distributedLockTest/{key}/{value}", method = {RequestMethod.POST, RequestMethod.GET})
    public String distributedLockTest(@PathVariable String key, @PathVariable String value) throws InterruptedException {
        return testService.distributedLockTest(key, value);
    }

}
