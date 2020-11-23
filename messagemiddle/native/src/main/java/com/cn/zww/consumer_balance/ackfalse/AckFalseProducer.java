package com.cn.zww.consumer_balance.ackfalse;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZhangWeiWei
 * @date 2020/10/28 22:54
 * @description 普通消息生产者
 */
public class AckFalseProducer {
    public static final String EXCHANGE_NAME = "ack_false_log";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        //定义路由键
        String routeKey = "error";
        for (int i = 0; i < 3; i++) {
            // 发送的消息
            String message = "Hello World_"+(i+1);
            //参数1：exchange name
            //参数2：routing key
            channel.basicPublish(EXCHANGE_NAME, routeKey,
                    null, message.getBytes());
            System.out.println(" [x] Sent 'error':'"
                    + message + "'");
        }
        //关闭信道
        channel.close();
        connection.close();
    }
}
