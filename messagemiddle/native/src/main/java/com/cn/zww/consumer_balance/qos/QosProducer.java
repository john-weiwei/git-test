package com.cn.zww.consumer_balance.qos;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class QosProducer {

    public static final String EXCHANGE_NAME = "direct_log";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        for (int i = 0; i < 210; i++) {
            //发送的消息
            String message = "Hello World_"+(i+1);
            if (i==209) {
                message = "stop";
            }
            channel.basicPublish(EXCHANGE_NAME,"error",null,message.getBytes());
            System.out.println("[x] Sent 'error';"+message);
        }
        //关闭信道和连接
        channel.close();
        connection.close();
    }
}
