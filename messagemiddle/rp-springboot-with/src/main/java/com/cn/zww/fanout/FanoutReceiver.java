package com.cn.zww.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/16
 * @Description:
 * fanout 消息接收端
 */
@Component
@RabbitListener(queues = "sb.fanout.A")
public class FanoutReceiver {

    @RabbitHandler
    public void receivedMsg(String hello) {
        System.out.println("FanoutReceived : "+hello);
    }
}
