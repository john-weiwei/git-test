package com.cn.zww.service.topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhangWeiWei
 * @date 2020/11/14 16:24
 * @description
 */
@Component
public class KingAllTopicService implements MessageListener {
    private Logger log = LoggerFactory.getLogger(KingAllTopicService.class);
    public void onMessage(Message message) {
        log.info("Get message : "+new String(message.getBody()));
    }
}
