package com.cn.zww.rejectproducer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * @author ZhangWeiWei
 * @date 2020/10/29 21:49
 * @description 拒绝消息-普通消息生产者
 * 一个队列绑定多个消费者时，消息在消费者之间轮询被处理
 * 被拒绝的消息如果requeue参数为true，那么这条消息就会回到交换器，
 * 继续下一次的轮询，依次消费消息
 */
public class RejectProducer {
    public static final String EXCHANGE_NAME = "reject_producer_log";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory =  new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        String routeKey = "info";
        for (int i = 0; i < 3; i++) {
            String message = "Hello World_"+(i+1);
            channel.basicPublish(EXCHANGE_NAME,routeKey,null,message.getBytes());
            System.out.println(" [x] Sent 'error':'"
                    + message + "'");
        }
        //关闭信道和连接
        channel.close();
        connection.close();
    }
}
