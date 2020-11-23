package com.cn.zww.fanout;

import com.cn.zww.constant.RmConst;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/16
 * @Description:
 * fanout 交换器发送消息端
 */
@Component
public class FanoutSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String msg) {
        String message = msg + "----" + System.currentTimeMillis();
        System.out.println("FanoutSender : "+message);
        rabbitTemplate.convertAndSend(RmConst.EXCHANGE_FANOUT,"",msg);
    }
}
