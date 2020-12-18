package com.cn.zww.producer_balance.backexchange;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 14:13
 * @description 消费者-备用交换器
 * 匹配所有路由键
 */
public class BackExConsumer {
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(BackExProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(BackExProducer.BAK_EXCHANGE_NAME, BuiltinExchangeType.FANOUT,
                true,false,null);
        //只绑定king路由键
        String routeKey = "#";
        String queueName = "fetchOther";
        //声明队列
        channel.queueDeclare(queueName,false,false,false,null);
        channel.queueBind(queueName,BackExProducer.BAK_EXCHANGE_NAME,routeKey);

        System.out.println(" [*] Waiting for messages......");
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                //记录日志到文件：
                System.out.println("Received [" + envelope.getRoutingKey()
                        + "] " + message);
            }
        };
        //消费消息
        channel.basicConsume(queueName,true,consumer);

//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("127.0.0.1");
//
//        // 打开连接和创建频道，与发送端一样
//        Connection connection = factory.newConnection();
//        final Channel channel = connection.createChannel();
//        channel.exchangeDeclare(BackExProducer.BAK_EXCHANGE_NAME,
//                BuiltinExchangeType.FANOUT,
//                true, false, null);
//        // 声明一个队列
//        String queueName = "fetchother";
//        channel.queueDeclare(queueName,
//                false,false,
//                false,null);
//
//        channel.queueBind(queueName,
//                BackExProducer.BAK_EXCHANGE_NAME, "#");
//
//        System.out.println(" [*] Waiting for messages......");
//
//        // 创建队列消费者
//        final Consumer consumerB = new DefaultConsumer(channel) {
//            @Override
//            public void handleDelivery(String consumerTag,
//                                       Envelope envelope,
//                                       AMQP.BasicProperties properties,
//                                       byte[] body)
//                    throws IOException {
//                String message = new String(body, "UTF-8");
//                //记录日志到文件：
//                System.out.println( "Received ["
//                        + envelope.getRoutingKey() + "] "+message);
//            }
//        };
//        channel.basicConsume(queueName, true, consumerB);
    }
}
