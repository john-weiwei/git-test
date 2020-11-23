package com.cn.zww.consumer_balance.getmessage;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 22:00
 * @description 生产者-拉取模式
 */
public class GetProducer {
    public static final String EXCHANGE_NAME = "LAQU";
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        for (int i = 0; i <3 ; i++) {
            String message = "HelloWorld_"+i;
            channel.basicPublish(EXCHANGE_NAME,"error",false,null,message.getBytes());
            System.out.println(" [x] Sent 'error':'"
                    + message + "'");
        }
        //关闭信道和连接
        channel.close();
        connection.close();
    }
}
