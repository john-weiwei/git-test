package com.cn.zww.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhangWeiWei
 * @date 2020/11/16 21:35
 * @description
 * user 队列的接受者
 */
@Component
@RabbitListener(queues = "sb.info.user")
public class TopicUserMsgReceiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Topic Receive user msg: " + msg);
    }
}
