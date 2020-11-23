package com.cn.zww.producer_balance.mandatory;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/24 14:31
 * @description 消息发送失败，返回失败通知
 * 在消息生产者中处理失败通知
 */
public class ProducerMandatory {
    public static final String EXCHANGE_NAME = "direct_mandatory";
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args)throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);


        //失败通知，回调
        channel.addReturnListener(new ReturnListener() {
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("返回replyCode："+replyCode);
                System.out.println("返回replyText："+replyText);
                System.out.println("返回exchange："+exchange);
                System.out.println("返回routingKey："+routingKey);
            }
        });

        String[] routeKeys = {"king","james","mark"};
        for (int index=0;index<3;index++) {
            String routekey = routeKeys[index%3];
            String message = "hello world_"+index;
            channel.basicPublish(EXCHANGE_NAME,routekey,true,null,message.getBytes());
            System.out.println("----------------------------------");
            System.out.println(" Sent Message: [" + routekey +"]:'"
                    + message + "'");
            Thread.sleep(200);
        }

        //关闭信道，关闭连接
        channel.close();
        connection.close();
    }
}
