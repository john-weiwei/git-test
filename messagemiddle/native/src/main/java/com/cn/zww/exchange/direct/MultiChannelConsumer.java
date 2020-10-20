package com.cn.zww.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 21:12
 * @description  一个连接多个信道
 */
public class MultiChannelConsumer {

    public static class ConsumerWorker implements Runnable {

        final Connection connection;

        public ConsumerWorker(Connection connection) {
            this.connection = connection;
        }

        public void run() {
            try {
                //每个连接创建一个信道，意味着每个线程有一个信道
                Channel channel = connection.createChannel();
                //设置交换器
                channel.exchangeDeclare(DirectProducer.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
                //声明一个随机队列
                String queueName = channel.queueDeclare().getQueue();
                //将队列和路由键进行绑定
                String[] routeKeys = {"Allen","King","Mark"};
                //绑定队列
                for (String routeKey: routeKeys) {
                    channel.queueBind(queueName,DirectProducer.EXCHANGE_NAME,routeKey);
                }
                final String consumerName = Thread.currentThread().getName()+"-all";
                System.out.println("["+consumerName+"]Waiting message......");
                //声明消费者
                final Consumer consumer = new DefaultConsumer(channel){

                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routeKey = envelope.getRoutingKey();
                        String msg = new String(body,"UTF-8");
                        System.out.println(consumerName+"-Received:["+routeKey+"]:"+msg);
                    }
                };
                channel.basicConsume(queueName,true,consumer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(DirectProducer.HOST);
        try {
            Connection connection = connectionFactory.newConnection();
            for (int i = 0; i < 2; i++) {
                Thread thread = new Thread(new ConsumerWorker(connection));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
