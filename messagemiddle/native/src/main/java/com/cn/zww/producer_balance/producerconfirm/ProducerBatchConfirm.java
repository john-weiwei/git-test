package com.cn.zww.producer_balance.producerconfirm;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 13:23
 * @description 消息确认-批量确认
 */
public class ProducerBatchConfirm {
    public static final String EXCHANGE_NAME = "direct_producer_batch_confirm";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //开启消息批量确认
        channel.confirmSelect();

        String[] routeKeys = {"king","mark","james"};
        for (int i = 0; i < 3; i++) {
            String routeKey = routeKeys[i%3];
            String message = "HelloWorld_"+i;
            channel.basicPublish(EXCHANGE_NAME,routeKey,true,null,message.getBytes());
            System.out.println(" Sent Message: [" + routeKey +"]:'"
                    + message + "'");
        }
        channel.waitForConfirmsOrDie();
        //关闭信道、连接
        channel.close();
        connection.close();
    }
}
