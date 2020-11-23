package com.cn.zww.producer_balance.mandatory;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/24 14:38
 * @description 失败通知的消费者
 */
public class ConsumerMandatory {

    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(ProducerMandatory.HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(ProducerMandatory.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String routeKey = "king";
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,ProducerMandatory.EXCHANGE_NAME,routeKey);

        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                //记录日志到文件：
                System.out.println( "Received ["+ envelope.getRoutingKey()
                        + "] "+message);
            }
        };

        //消费消息
        channel.basicConsume(queueName,true,consumer);
    }
}
