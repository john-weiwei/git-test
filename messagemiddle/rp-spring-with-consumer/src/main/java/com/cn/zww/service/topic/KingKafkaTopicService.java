package com.cn.zww.service.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhangWeiWei
 * @date 2020/11/14 16:29
 * @description
 */
@Component
public class KingKafkaTopicService implements MessageListener {
    private Logger log = LoggerFactory.getLogger(KingKafkaTopicService.class);
    public void onMessage(Message message) {
        log.info("Get message : "+new String(message.getBody()));
    }
}
