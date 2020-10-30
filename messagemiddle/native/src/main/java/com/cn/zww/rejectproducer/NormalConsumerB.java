package com.cn.zww.rejectproducer;

import com.cn.zww.consumer_balance.ackfalse.AckFalseProducer;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 22:12
 * @description
 */
public class NormalConsumerB {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RejectProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        String queueName = "forceError";
        channel.queueDeclare(queueName, false, false, false, null);

        String routeKey = "info";
        channel.queueBind(queueName, RejectProducer.EXCHANGE_NAME, routeKey);
        System.out.println("waiting for message........");
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received[" + envelope.getRoutingKey()
                            + "]" + message);
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (Exception e) {
                    // TODO: 2020/10/29 发生异常，拒绝消费，重新发送
                    channel.basicReject(envelope.getDeliveryTag(), true);

                }
            }
        };
        //消费消息，手动确认消息
        channel.basicConsume(queueName, false, consumer);
    }
}
