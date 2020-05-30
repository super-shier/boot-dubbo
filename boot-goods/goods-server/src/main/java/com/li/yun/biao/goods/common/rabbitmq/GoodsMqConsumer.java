package com.li.yun.biao.goods.common.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @Author: liyunbiao
 * @Date: 2019/12/9 2:24 PM
 * @description 消费mq
 */
@Component
public class GoodsMqConsumer {
    private final Logger logger = LoggerFactory.getLogger(GoodsMqConsumer.class);
    private static final String CHARSET_SET = "UTF-8";

    /**
     * 消费Fanout类型的交换机消息
     */
    @RabbitListener(queues = "fanout.mq.queue")
    @RabbitHandler
    public void fanoutProcess(Message massage) throws UnsupportedEncodingException {
        logger.info("********fanout类型massage:{}", JSON.toJSONString(massage));
        String id = massage.getMessageProperties().getMessageId();
        String body = new String(massage.getBody(), CHARSET_SET);
        logger.info("********fanout类型id:{},body:{}", id, body);
    }

    /**
     * 消费Direct类型的交换机消息
     */
    @RabbitListener(queues = "direct.mq.queue")
    @RabbitHandler
    public void directProcess(Message massage) throws UnsupportedEncodingException {
        logger.info("********direct类型massage:{}", JSON.toJSONString(massage));
        String id = massage.getMessageProperties().getMessageId();
        String body = new String(massage.getBody(), CHARSET_SET);
        logger.info("********direct类型id:{},body:{}", id, body);
    }
}
