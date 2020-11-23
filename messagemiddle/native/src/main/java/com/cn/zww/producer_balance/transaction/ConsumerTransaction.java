package com.cn.zww.producer_balance.transaction;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/24 16:57
 * @description
 */
public class ConsumerTransaction {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ProducerTransaction.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(ProducerTransaction.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String routeKey = "king";
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,ProducerTransaction.EXCHANGE_NAME,routeKey);

        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                //记录日志到文件：
                System.out.println( "Received ["+ envelope.getRoutingKey()
                        + "] "+message);
            }
        };
        //消费消息
        channel.basicConsume(queueName,true,consumer);
    }
}
