package com.cn.zww.exchange.direct;

import com.rabbitmq.client.*;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 14:36
     * @description 消费者的一般用法
 */
public class NormalConsumer {
    public static void main(String[] args) throws Exception {
        //连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //连接
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        //声明一个队列
        String queueName = "Allen-queue";
        channel.queueDeclare(queueName,false,false,false,null);
        //将队列与路由键绑定
        String routeKey = "Allen";
        channel.queueBind(queueName,DirectProducer.EXCHANGE_NAME,routeKey);
        System.out.println("Waiting for message");
        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            //处理生产发送过来的消息
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String message = new String(body,"UTF-8");
                System.out.println("Received["+routeKey+"]"+message);
            }
        };
        //消息者正式开始在队列上消费
        //TODO true表示采用自动确认机制
        channel.basicConsume(queueName,true,consumer);
    }
}
