package com.cn.zww.consumer_balance.ackfalse;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 21:40
 * @description
 */
public class AckFalseConsumerB {
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(AckFalseProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        String queueName = "fouceTrueAck";
        channel.queueDeclare(queueName,false,false,false,null);

        String routeKey = "error";
        channel.queueBind(queueName,AckFalseProducer.EXCHANGE_NAME,routeKey);
        System.out.println("waiting for message........");
        final Consumer consumer = new DefaultConsumer(channel) {

            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received["+envelope.getRoutingKey()
                        +"]"+message);
                // TODO: 2020/10/29 确认消息消费
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        //消费消息
        channel.basicConsume(queueName,false,consumer);
    }
}
