package com.cn.zww.consumer_balance.qos;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/28 22:11
 * @description 普通消费者
 */
public class QosConsumer {

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(QosProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        String queueName = "errorQueue";
        channel.queueDeclare(queueName,true,false,false,null);
        String routeKey = "error";
        channel.queueBind(queueName,QosProducer.EXCHANGE_NAME,routeKey);
        System.out.println("[*]waiting message");
        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String message = new String(body,"UTF-8");
                System.out.println("Received:["+routeKey+"]:"+message);
                //消息确认：单条确认
                channel.basicAck(envelope.getDeliveryTag(),true);
            }
        };
        //采用qos的预期模式
        channel.basicQos(50,true);
        //消费消息
        channel.basicConsume(queueName,false,consumer);
        BatchConsumer batchConsumer = new BatchConsumer(channel);
        channel.basicConsume(queueName,false,batchConsumer);
    }
}
