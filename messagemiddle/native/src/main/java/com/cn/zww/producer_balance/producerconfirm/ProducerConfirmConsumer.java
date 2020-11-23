package com.cn.zww.producer_balance.producerconfirm;


import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 12:33
 * @description 消费方确认-消费者
 */
public class ProducerConfirmConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ProducerConfirm.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //一般消息确认：逐条确认
//        channel.exchangeDeclare(ProducerConfirm.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //批量消息确认
//        channel.exchangeDeclare(ProducerBatchConfirm.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //异步消息确认
        channel.exchangeDeclare(ProducerConfirmAsync.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String routeKey = "king";
        String queueName = channel.queueDeclare().getQueue();
//        channel.queueBind(queueName,ProducerConfirm.EXCHANGE_NAME,routeKey);
//        channel.queueBind(queueName,ProducerBatchConfirm.EXCHANGE_NAME,routeKey);
        channel.queueBind(queueName,ProducerConfirmAsync.EXCHANGE_NAME,routeKey);

        System.out.println("[*]waiting message");
        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String message = new String(body,"UTF-8");
                System.out.println("Received["+routeKey+"]:"+message);
            }
        };
        //消费消息
        channel.basicConsume(queueName,true,consumer);
    }
}
