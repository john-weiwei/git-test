package com.cn.zww.hello;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/16
 * @Description:
 */
@Component
@RabbitListener(queues = "sb.hello")
public class HelloReceiver {

    @RabbitHandler
    public void receiver(String message) {
        System.out.println("Receive : " + message);
    }
}
