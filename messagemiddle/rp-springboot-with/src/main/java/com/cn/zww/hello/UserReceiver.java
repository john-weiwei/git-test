package com.cn.zww.hello;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/16
 * @Description:
 */
@Component
@RabbitListener(queues = "sb.user")
public class UserReceiver implements ChannelAwareMessageListener {

//    @RabbitHandler
//    public void receive(String msg) {
//        System.out.println("Receive user : " + msg);
//    }

    /**
     * 手动消费消息
     *
     * @param message
     * @param channel
     * @throws Exception
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        try {
            //手动确认
            String msg = new String(message.getBody());
            System.out.println("UserReceiver 接收到消息：" + msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("UserReceiver 消息已消费");
        } catch (Exception e) {
            //发生确认异常
            System.out.println(e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
            System.out.println("UserReceiver 拒绝消息，要求mq重发");
            throw e;
        }
    }
}
