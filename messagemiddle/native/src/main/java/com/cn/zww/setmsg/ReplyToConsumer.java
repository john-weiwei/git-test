package com.cn.zww.setmsg;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/11/2 22:27
 * @description
 * 消费消息的消费者
 */
public class ReplyToConsumer {

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ReplyToProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();

        channel.exchangeDeclare(ReplyToProducer.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String queueName = "replyTo";
        channel.queueDeclare(queueName,false,false,false,null);

        String routeKey = "error";
        channel.queueBind(queueName,ReplyToProducer.EXCHANGE_NAME,routeKey);
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                AMQP.BasicProperties responseProp = new AMQP.BasicProperties()
                        .builder()
                        .replyTo(properties.getReplyTo())
                        .correlationId(properties.getMessageId())
                        .build();
                System.out.println("Received:"+message);
                channel.basicPublish("",responseProp.getReplyTo(),responseProp,("OK"+message).getBytes());
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }
}
