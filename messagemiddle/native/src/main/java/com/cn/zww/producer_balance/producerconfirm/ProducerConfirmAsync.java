package com.cn.zww.producer_balance.producerconfirm;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 13:32
 * @description 消息确认-异步确认
 */
public class ProducerConfirmAsync {
    public static final String EXCHANGE_NAME = "direct_producer_async_confirm";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //开启消息批量确认
        channel.confirmSelect();

        //添加消息确认监听
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("ack:"+deliveryTag+"；multiple:"+multiple);
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("nack:"+deliveryTag+"；multiple:"+multiple);
            }
        });

        String[] routeKeys = {"king","mark","james"};
        for (int i = 0; i < 6; i++) {
            String routeKey = routeKeys[i%3];
            String message = "HelloWorld_"+i;
            channel.basicPublish(EXCHANGE_NAME,routeKey,true,null,message.getBytes());
            System.out.println(" Sent Message: [" + routeKey +"]:'"
                    + message + "'");
        }
        //采用异步确认消息，不需要关闭信道和连接
    }
}
