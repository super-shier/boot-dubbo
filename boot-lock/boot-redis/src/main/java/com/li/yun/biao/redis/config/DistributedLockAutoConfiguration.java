package com.li.yun.biao.redis.config;

import com.li.yun.biao.redis.lock.IDistributedLock;
import com.li.yun.biao.redis.lock.RedisDistributedLock;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author: liyunbiao
 * @date: 2020/5/27 7:25 下午
 * @description 配置DistributeLock
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class DistributedLockAutoConfiguration {

    @Bean
    @ConditionalOnBean(RedisTemplate.class)
    public IDistributedLock redisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
        return new RedisDistributedLock(redisTemplate);
    }

}
