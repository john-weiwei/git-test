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
@RabbitListener(queues = "sb.user")
public class UserReceiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("Receive : " + msg);
    }
}
