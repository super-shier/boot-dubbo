package com.li.yun.biao.user.common.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: liyunbiao
 * @Date: 2019/12/9 2:02 PM
 * @description
 */
@Component
public class UserMqProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    //fanout类型queue
    private static final String FANOUT_MQ_QUEUE = "fanout.mq.queue";
    //direct用户交换机名
    private static final String USER_MQ_DIRECT_EXCHANGE = "user.mq.direct.exchange";
    //direct路由key
    private static final String USER_ROUTING_KEY = "user.routing.key";

    /**
     * 发送fanout类型消息
     */
    public void sendFanoutMsg(String massage) {
        rabbitTemplate.convertAndSend(FANOUT_MQ_QUEUE, massage);
    }

    /**
     * 发送direct类型消息
     */
    public void sendDirectMsg(String massage) {
        rabbitTemplate.convertAndSend(USER_MQ_DIRECT_EXCHANGE, USER_ROUTING_KEY, massage);
    }
}
