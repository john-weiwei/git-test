package com.cn.zww.consumer_balance.getmessage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 22:09
 * @description 消费者-拉取模式
 */
public class GetConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(GetProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        String queueName = "error-queue";
        channel.queueDeclare(queueName,false,false,false,null);

        //声明路由键，并绑定
        String routeKey = "error";
        channel.queueBind(queueName,GetProducer.EXCHANGE_NAME,routeKey);
        System.out.println("[*]waiting message");
        //无效拉取
        while (true) {
            //消息确认之后，会从RabbitMQ的队列中删除
            GetResponse getResponse = channel.basicGet(queueName,false); //autoAck：true自动确认消息
            if (null != getResponse) {
                System.out.println("Received["+getResponse.getEnvelope().getRoutingKey()+"]"+
                        new String(getResponse.getBody(),"UTF-8"));
            }
            //手动确认消息
            //channel.basicAck(0,true);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
