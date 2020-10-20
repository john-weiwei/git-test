package com.cn.zww.exchange.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/18 21:33
 * @description 一个队列多个消费者，则消息表现在消费者之间被轮询消费
 */
public class MultiConsumerOneQueue {

    public static class ConsumerWorker implements Runnable {

        final Connection connection;
        final String queueName;

        public ConsumerWorker(Connection connection,String queueName) {
            this.connection = connection;
            this.queueName = queueName;
        }

        public void run() {
            try {
                //每个连接创建一个信道，意味着每个线程有一个信道
                Channel channel = connection.createChannel();
                //设置交换器
                channel.exchangeDeclare(DirectProducer.EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
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
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(DirectProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        String queueName = "queue-all";
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new ConsumerWorker(connection,queueName));
            thread.start();
        }
    }
}
