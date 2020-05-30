package com.li.yun.biao.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class UserServerStart {
    public static void main(String[] args) {
        SpringApplication.run(UserServerStart.class, args);
    }

}
