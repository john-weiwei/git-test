package com.cn.zww.setmsg;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.AMQBasicProperties;

import java.io.IOException;
import java.util.UUID;

/**
 * @author ZhangWeiWei
 * @date 2020/11/2 22:05
 * @description
 * 发送消息的生产者
 */
public class ReplyToProducer {

    public static final String EXCHANGE_NAME = "reply_to";
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String routeKey = "error";

        // TODO: 2020/11/2  响应队列，消费者将响应的消息放入这个队列中
        String queueName = channel.queueDeclare().getQueue();
        // TODO: 2020/11/2 消息唯一id
        String msgId = UUID.randomUUID().toString();
        // TODO: 2020/11/2 设置消息中的应答属性
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .replyTo(queueName)
                .messageId(msgId)
                .build();
        //声明一个消息，用于接收消费者消费之后响应的信息
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                String routeKey = envelope.getRoutingKey();
                System.out.println("replyTo:["+routeKey+"],message:"+message);
            }
        };
        //消费消息
        channel.basicConsume(queueName,true,consumer);

        String msg = "HelloRabbit";
        // TODO: 2020/11/2 发送消息
        channel.basicPublish(EXCHANGE_NAME,routeKey,properties,msg.getBytes());
        System.out.println("Sent massage:"+msg);
    }
}
