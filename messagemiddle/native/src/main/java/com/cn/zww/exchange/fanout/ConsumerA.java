package com.cn.zww.exchange.fanout;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 22:11
 * @description fanout消费者-绑定多个路由键
 */
public class ConsumerA {
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(FanoutProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //随机队列名称
        String queueName = channel.queueDeclare().getQueue();
        //声明交换器
        channel.exchangeDeclare(FanoutProducer.EXCHANGE_NAME,BuiltinExchangeType.FANOUT);
        //路由键
        String[] routeKeys = {"Allen","King","Mark"};
        for (int i = 0; i < 3; i++) {
            //将路由键绑定在队列上
            channel.queueBind(queueName,FanoutProducer.EXCHANGE_NAME,routeKeys[i]);
        }
        System.out.println("["+queueName+"]Waiting message");
        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String msg = new String(body,"UTF-8");
                System.out.println("Received["+routeKey+"]"+msg);
            }
        };
        //消息采用自动确认机制
        channel.basicConsume(queueName,true,consumer);
    }
}
