package com.cn.zww.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author ZhangWeiWei
 * @date 2020/11/16 21:32
 * @description
 * 接收email消息的接受者
 */
@Component
@RabbitListener(queues = "sb.info.email")
public class TopicEmailMsgReceiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Topic Receive email : " + msg);
    }
}
