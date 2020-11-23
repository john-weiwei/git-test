package com.cn.zww.producer_balance.backexchange;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 14:01
 * @description  消费者-主交换器消费者
 */
public class MainConsumer {
    public static void main(String[] args)throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(BackExProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //不用再声明交换器
//        channel.exchangeDeclare(BackExProducer.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //声明队列
        String queueName = "backupexchange";
        channel.queueDeclare(queueName,false,false,false,null);

        //只绑定king路由键
        String routeKey = "king";
        channel.queueBind(queueName,BackExProducer.EXCHANGE_NAME,routeKey);

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
//
//        // 声明一个队列
//        String queueName = "backupexchange";
//        channel.queueDeclare(queueName,
//                false,false,
//                false,null);
//
//        String routekey="king";//只关注king级别的日志，然后记录到文件中去。
//        channel.queueBind(queueName,
//                BackExProducer.EXCHANGE_NAME, routekey);
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
