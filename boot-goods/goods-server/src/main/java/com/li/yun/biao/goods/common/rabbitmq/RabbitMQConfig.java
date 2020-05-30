package com.li.yun.biao.goods.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: liyunbiao
 * @Date: 2019/12/9 2:00 PM
 * @description mq配置
 */


@Configuration
public class RabbitMQConfig {
    //fanout用户交换机名
    private static final String USER_MQ_FANOUT_EXCHANGE = "user.mq.fanout.exchange";
    //fanout队列名
    private static final String FANOUT_MQ_QUEUE = "fanout.mq.queue";
    //direct用户交换机名
    private static final String USER_MQ_DIRECT_EXCHANGE = "user.mq.direct.exchange";
    //direct队列名
    private static final String DIRECT_MQ_QUEUE = "direct.mq.queue";
    //direct路由key
    private static final String USER_ROUTING_KEY = "user.routing.key";

    //创建fanout类型队列
    @Bean
    public Queue fanoutMqQueue() {
        return new Queue(FANOUT_MQ_QUEUE);
    }

    //创建fanout类型交换机
    @Bean
    public FanoutExchange userMqFanoutExchange() {
        return new FanoutExchange(USER_MQ_FANOUT_EXCHANGE);
    }

    //fanout类型队列与交换机进行绑定
    @Bean
    Binding bindingFanoutMq() {
        return BindingBuilder.bind(fanoutMqQueue()).to(userMqFanoutExchange());
    }

    //创建direct类型队列
    @Bean
    public Queue directMqQueue() {
        return new Queue(DIRECT_MQ_QUEUE);
    }

    //创建direct类型交换机
    @Bean
    public DirectExchange userMqDirectExchange() {
        return new DirectExchange(USER_MQ_DIRECT_EXCHANGE);
    }

    //direct类型队列与交换机进行绑定
    @Bean
    public Binding bindingDirectMq() {
        return BindingBuilder.bind(directMqQueue()).to(userMqDirectExchange()).with(USER_ROUTING_KEY);
    }

}
