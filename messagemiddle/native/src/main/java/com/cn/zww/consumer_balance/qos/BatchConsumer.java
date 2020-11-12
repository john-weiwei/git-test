package com.cn.zww.consumer_balance.qos;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;

/**
 * @author ZhangWeiWei
 * @date 2020/10/28 22:34
 * @description 批量确认消息
 */
public class BatchConsumer extends DefaultConsumer {

    private int messageCount = 0;
    /**
     * Constructs a new instance and records its association to the passed-in channel.
     *
     * @param channel the channel to which this consumer is attached
     */
    public BatchConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body,"UTF-8");
        System.out.println("批量消费者---Received["+envelope.getRoutingKey()
                +"]"+message);
        messageCount++;
        if (messageCount % 50 == 0) {
            this.getChannel().basicAck(envelope.getDeliveryTag(),true);
            System.out.println("批量消息费进行消息的确认------------");
        }
        if (message.equalsIgnoreCase("stop")) {
            this.getChannel().basicAck(envelope.getDeliveryTag(),true);
            System.out.println("批量消费者进行最后业务消息的确认---------");
        }
    }
}
