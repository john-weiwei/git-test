package com.cn.zww.producer_balance.backexchange;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.HashMap;

/**
 * @author ZhangWeiWei
 * @date 2020/10/25 13:45
 * @description 生产者-绑定备用交换器
 */
public class BackExProducer {
    public static final String HOST = "127.0.0.1";
    public static final String EXCHANGE_NAME = "main-exchange";
    public static final String BAK_EXCHANGE_NAME = "ae";

    public static void main(String[] args) throws Exception {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        //声明备用交换器
        HashMap<String,Object> argsMap = new HashMap<String, Object>();
        argsMap.put("alternate-exchange",BAK_EXCHANGE_NAME);
        //主交换器 durable:true 表示当存在声明的这个交换器时，重新启动交换器
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",
                true,false,argsMap);

        //备用交换器
        channel.exchangeDeclare(BAK_EXCHANGE_NAME,BuiltinExchangeType.FANOUT,
                true,false,null);


        //发送消息
        String[] routeKeys = {"king","mark","james"};
        for (int i = 0; i < 6; i++) {
            String routeKey = routeKeys[i%3];
            String message = "HelloWorld_"+i;
            channel.basicPublish(EXCHANGE_NAME,routeKey,true,null,message.getBytes());
            System.out.println(" Sent Message: [" + routeKey +"]:'"
                    + message + "'");
        }

        //关闭信道和连接
        channel.close();
        connection.close();
    }
}
