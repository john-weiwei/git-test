package com.cn.zww.hello;

import com.cn.zww.constant.RmConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/16
 * @Description:
 */
@Component
public class DefaultSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        String message = msg + "-----" + System.currentTimeMillis();
        System.out.println("Sender : "+msg);
//        rabbitTemplate.convertAndSend(RmConst.QUEUE_HELLO,message);
        this.rabbitTemplate.convertAndSend(RmConst.QUEUE_USER,message);
    }
}
