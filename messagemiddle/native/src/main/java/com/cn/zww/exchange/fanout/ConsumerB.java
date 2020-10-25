package com.cn.zww.exchange.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;
/**
 * @author ZhangWeiWei
 * @date 2020/10/23 20:48
 * @description fanout消费者-绑定一个不存在的路由键
 */
public class ConsumerB {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(FanoutProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(FanoutProducer.EXCHANGE_NAME,BuiltinExchangeType.FANOUT);
        String queueName = channel.queueDeclare().getQueue();
        //设置不存在的路由键
        String routeKey = "jack";
        channel.queueBind(queueName, FanoutProducer.EXCHANGE_NAME,routeKey);
        System.out.println("[*] waiting message");

        final Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String message = new String(body,"UTF-8");
                System.out.println("Received["+routeKey+"]"+message);
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }
}
