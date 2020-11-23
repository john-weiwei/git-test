package com.cn.zww.service.fanout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @author ZhangWeiWei
 * @date 2020/11/14 16:11
 * @description
 */
public class H2_Service implements MessageListener {
    private Logger log = LoggerFactory.getLogger(H2_Service.class);
    public void onMessage(Message message) {
        log.info("Get message : "+new String(message.getBody()));
    }
}
