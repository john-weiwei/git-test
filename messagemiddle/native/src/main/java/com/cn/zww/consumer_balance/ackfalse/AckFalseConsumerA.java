package com.cn.zww.consumer_balance.ackfalse;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 21:35
 * @description 消费者不对消息进行确认
 */
public class AckFalseConsumerA {
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(AckFalseProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        String queueName = "foucefalseAck";
        channel.queueDeclare(queueName,false,false,false,null);

        String routeKey = "error";
        channel.queueBind(queueName,AckFalseProducer.EXCHANGE_NAME,routeKey);
        System.out.println("waiting for message........");
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received["+envelope.getRoutingKey()
                        +"]"+message);
                // TODO: 2020/10/29 消息确认
            }
        };
        //消费消息，手动确认消息
        channel.basicConsume(queueName,false,consumer);
    }
}
