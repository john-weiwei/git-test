package com.cn.zww.exchange.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 22:11
 * @description
 */
public class ConsumerA {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(FanoutProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = channel.queueDeclare().getQueue();
        String[] routeKeys = {"Allen","King","Mark"};
        for (int i = 0; i < 3; i++) {
            channel.queueBind(queueName,FanoutProducer.EXCHANGE_NAME,routeKeys[i]);
        }
        System.out.println("["+queueName+"]Waiting message");
        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String msg = new String(body,"UTF-8");
                System.out.println("Received["+routeKey+"]"+msg);
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }
}
