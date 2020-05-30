package com.li.yun.biao.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 启动项
 */
@SpringBootApplication
public class DistributeLockdApplication {
    public static void main(String[] args) {
        SpringApplication.run(DistributeLockdApplication.class, args);
    }
}
