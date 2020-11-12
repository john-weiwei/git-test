package com.cn.zww.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 15:34
 * @description 队列和交换器的多重绑定
 */
public class MultiBindConsumer {

    public static void main(String[] args) throws Exception {
        //连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(DirectProducer.HOST);
        //连接
        Connection connection = connectionFactory.newConnection();
        //信道
        Channel channel = connection.createChannel();
        //在信道中声明交换器
        channel.exchangeDeclare(DirectProducer.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        //获取随机队列
//        final String queueName = channel.queueDeclare().getQueue();
        final String queueName = "queue-all";
        channel.queueDeclare(queueName,false,false,false,null);
        //队列绑定到交换器时，允许绑定多个路由键到交换器上
        String[] routeKeys = {"Allen","King","Mark"};
        for (String routeKey: routeKeys) {
            channel.queueBind(queueName,DirectProducer.EXCHANGE_NAME,routeKey);
        }
        System.out.println("[*]Waiting message......");

        //声明消费者
        final Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                String msg = new String(body,"UTF-8");
                System.out.println("Received:["+routeKey+"]:"+msg);
            }
        };
        //开始正式消费消息
        channel.basicConsume(queueName,true,consumer);
    }
}
