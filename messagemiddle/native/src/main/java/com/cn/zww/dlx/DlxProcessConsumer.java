package com.cn.zww.dlx;


import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 22:36
 * @description 处理死信消息的消费者，死信交换器
 */
public class DlxProcessConsumer {
    public static final String DLX_EXCHANGE_NAME = "dlx_process";

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(DlxProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(DLX_EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        //声明队列
        String queueName = "dlx_queue";
        channel.queueDeclare(queueName,false,false,false,null);
        //绑定队列
        channel.queueBind(queueName,DLX_EXCHANGE_NAME,"#");

        System.out.println("waiting for message......");
        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Received["+envelope.getRoutingKey()
                        +"]"+message);
            }
        };
        //消费消息，自动确认消息
        channel.basicConsume(queueName,true,consumer);
    }
}
