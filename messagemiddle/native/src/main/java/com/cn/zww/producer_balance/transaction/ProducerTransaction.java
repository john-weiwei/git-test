package com.cn.zww.producer_balance.transaction;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/24 16:49
 * @description 拥有事务的生产者
 */
public class ProducerTransaction {
    public static final String EXCHANGE_NAME = "direct_mandatory";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        String[] routeKeys = {"king","james","mark"};
        String queueName = channel.queueDeclare().getQueue();

        //开启事务
        channel.txSelect();

        //失败消息回调
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("返回replyCode："+replyCode);
                System.out.println("返回replyText："+replyText);
                System.out.println("返回exchange："+exchange);
                System.out.println("返回routingKey："+routingKey);
            }
        });

        try {
            //发送消息
            for (int index = 0;index < 3;index++) {
                String routeKey = routeKeys[index%3];
                String message = "HelloWorld_"+index;
                channel.queueBind(queueName,EXCHANGE_NAME,routeKey);
                channel.basicPublish(EXCHANGE_NAME,routeKey,true,null,message.getBytes());
                System.out.println("----------------------------------");
                System.out.println(" Sent Message: [" + routeKey +"]:'"
                        + message + "'");
            }
            //提交事务
            channel.txCommit();
        } catch (IOException e) {
            e.printStackTrace();
            //事务回滚
            channel.txRollback();
        }
        //关闭信道，关闭连接
        channel.close();
        connection.close();
    }
}
