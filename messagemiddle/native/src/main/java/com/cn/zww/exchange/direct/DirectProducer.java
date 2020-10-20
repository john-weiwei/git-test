package com.cn.zww.exchange.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 13:48
 * @description 消息生产者端
 */
public class DirectProducer {

    public static final String EXCHANGE_NAME = "direct_exchange";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {

        //连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Channel channel = null;
        Connection connection = null;
        try {
            //连接
            connection = connectionFactory.newConnection();
            //信道
            channel = connection.createChannel();
            //声明交换器，即使用的交换器类型
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
            //路由键
            String[] routeKeys = {"Allen","King","Mark"};
            //发送消息
            for (int i = 0; i < 6; i++) {
                String routeKey = routeKeys[i%3];
                String msg = "Hello,RabbitMQ"+(i+1);
                //发送消息
                channel.basicPublish(EXCHANGE_NAME,routeKey,null,msg.getBytes());
                System.out.println("Sent:"+routeKey+":"+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            //关闭信道和连接
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
