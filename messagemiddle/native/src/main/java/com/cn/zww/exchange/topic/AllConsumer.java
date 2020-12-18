package com.cn.zww.exchange.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/23 22:01
 * @description
 * '.'将路由键分为几个标识符
 * '*'是匹配一个
 * '#'是匹配一个或多个
 */
public class AllConsumer {
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(TopicProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明交换器
        channel.exchangeDeclare(TopicProducer.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        //随机队列
        String queueName = channel.queueDeclare().getQueue();
        //绑定路由键
        // # 匹配所有
//        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME,"#");
        // #.B 匹配所有是B结尾的
//        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME,"#.B");
        // #.kafka.B 匹配所有以kafka.B结尾的
//        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME,"#.kafka.B");
        // king.*.A 匹配所有以king开头，A结尾的
//        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME,"king.*.A");
        // king.# 匹配所有以king开头的
//        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME,"king.#");
        // king.kafka.A 匹配指定路由键
        channel.queueBind(queueName,TopicProducer.EXCHANGE_NAME,"king.kafka.A");

        //声明消费者
        final Consumer consumerAll = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String message = new String(body,"UTF-8");
                System.out.println(" AllConsumer Received "
                        + routeKey
                        + "':'" + message + "'");
            }
        };
        //消费消息
        channel.basicConsume(queueName,true,consumerAll);
    }
}
