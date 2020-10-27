package com.cn.zww.exchange.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author ZhangWeiWei
 * @date 2020/10/23 21:55
 * @description topic 类型的生产者
 */
public class TopicProducer {

    public static final String EXCHANGE_NAME = "topic_logs";
    public static final String HOST = "127.0.0.1";
    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        /*我们的课程，路由键最终格式类似于：king.kafka.A  king.kafka.B*/
        String[] techers={"king","mark","james"};
        for(int i=0;i<3;i++){
            String[]  modules={"kafka","jvm","redis"};
            for(int j=0;j<3;j++){
                String[]  servers={"A","B","C"};
                for(int k=0;k<3;k++){
                    // 发送的消息
                    String message = "Hello Topic_["+i+","+j+","+k+"]";
                    String routeKey = techers[i%3]+"."+modules[j%3]
                            +"."+servers[k%3];
                    channel.basicPublish(EXCHANGE_NAME,routeKey,
                            null, message.getBytes());
                    System.out.println(" [x] Sent '" + routeKey +":'"
                            + message + "'");
                }
            }
        }

        //关闭连接
        channel.close();
        connection.close();
    }
}
