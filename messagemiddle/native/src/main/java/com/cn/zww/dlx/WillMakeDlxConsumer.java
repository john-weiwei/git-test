package com.cn.zww.dlx;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author ZhangWeiWei
 * @date 2020/10/29 22:45
 * @description 产生死信消息的消费者
 */
public class WillMakeDlxConsumer {
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(DlxProducer.HOST);
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.exchangeDeclare(DlxProducer.EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("x-dead-letter-exchange",DlxProcessConsumer.DLX_EXCHANGE_NAME);
        //TODO 死信路由键，会替换消息原来的路由键
//        map.put("x-dead-letter-routing-key", "deal");
        String queueName = "dlx_make";
        channel.queueDeclare(queueName,false,false,false,map);

        channel.queueBind(queueName,DlxProducer.EXCHANGE_NAME,"#");

        System.out.println("waiting for message......");
        final Consumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String routeKey = envelope.getRoutingKey();
                if ("king".equalsIgnoreCase(routeKey)) {
                    String message = new String(body, "UTF-8");
                    System.out.println("Received["+envelope.getRoutingKey()
                            +"]"+message);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                } else {
                    //拒绝消费
                    channel.basicReject(envelope.getDeliveryTag(),false);
                }
            }
        };
        //消费消息
        channel.basicConsume(queueName,false,consumer);
    }
}
