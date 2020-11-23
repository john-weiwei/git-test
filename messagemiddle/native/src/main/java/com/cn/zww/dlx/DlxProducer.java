package com.cn.zww.dlx;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 22:33
 * @description 普通消息生产者
 */
public class DlxProducer {
    public static final String EXCHANGE_NAME = "dlx_make";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        String[] routeKeys = {"king","mark","james"};
        for (int i = 0; i < 3; i++) {
            String routeKey = routeKeys[i%3];
            String message = "Hello World_"+(i+1);
            channel.basicPublish(EXCHANGE_NAME,routeKey,null,message.getBytes());
            System.out.println(" [x] Sent '"+routeKey+"':'"
                    + message + "'");
        }
        channel.close();
        connection.close();
    }
}
