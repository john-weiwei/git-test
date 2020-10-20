package com.cn.zww.exchange.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 22:05
 * @description fanout 交换器
 */
public class FanoutProducer {

    public static final String EXCHANGE_NAME = "fanout_logs";
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args) {
        //连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        //连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            //信道
            channel = connection.createChannel();
            //声明交换器
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
            //声明路由键
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
