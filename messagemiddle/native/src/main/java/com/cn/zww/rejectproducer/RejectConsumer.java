package com.cn.zww.rejectproducer;

import com.cn.zww.consumer_balance.ackfalse.AckFalseProducer;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 22:16
 * @description 拒绝接受消息的消费者
 */
public class RejectConsumer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RejectProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        String queueName = "forceError";
        channel.queueDeclare(queueName,false,false,false,null);

        String routeKey = "info";
        channel.queueBind(queueName, RejectProducer.EXCHANGE_NAME,routeKey);
        System.out.println("waiting for message........");
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               try {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received["+envelope.getRoutingKey()
                            +"]"+message);
                   throw new Exception("处理异常"+message);
               } catch (Exception e) {
                   e.printStackTrace();
                   //拒绝处理消息，requeue：false表示不重发信息
                   channel.basicReject(envelope.getDeliveryTag(),false);
               }
            }
        };
        //消费消息，手动确认消息
        channel.basicConsume(queueName,false,consumer);
    }
}
