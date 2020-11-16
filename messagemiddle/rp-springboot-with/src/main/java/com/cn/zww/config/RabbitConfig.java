package com.cn.zww.config;

import com.cn.zww.constant.RmConst;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/13
 * @Description:
 * rabbitmq 配置
 */
@Component
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.username}")
    private String userName;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.port}")
    private String port;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirm;

    /**
     * 构建连接工厂
     * 使用 amqp包下的连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host+":"+port);
        connectionFactory.setUsername(userName);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);

        // TODO: 2020/11/13 发送方消息确认
        connectionFactory.setPublisherConfirms(publisherConfirm);
        return connectionFactory;
    }

    /**
     * RabbitAdmin 封装对rabbitmq的操作
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * RabbitTemplate
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        // TODO: 2020/11/13 发送方确认
        rabbitTemplate.setConfirmCallback(confirmCallback());
        // TODO: 2020/11/13 失败通知
        rabbitTemplate.setReturnCallback(returnCallback());
        return rabbitTemplate;
    }

    // TODO: 2020/11/13 发送方确认
    /**
     * 发送方确认回调函数
     * @return
     */
    @Bean
    public RabbitTemplate.ConfirmCallback confirmCallback() {
        return (correlationData, ack, cause) -> {
            if (ack) {
                System.out.println(">>>发送方确认消息mq成功");
            } else {
                System.out.println(">>>>>发送方发送消息失败，考虑重发"+cause);
            }
        };
    }

    // TODO: 2020/11/13 失败通知
    /**
     * 失败通知
     * @return
     */
    @Bean
    public RabbitTemplate.ReturnCallback returnCallback() {
        return (message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("无法路由的消息");
            System.out.println("ReplyText : "+replyText);
            System.out.println("Reply Exchange : "+exchange);
            System.out.println("Reply Code : "+replyCode);
            System.out.println("RoutingKey : "+routingKey);
            String msg = new String(message.getBody());
            System.out.println("Message : "+msg);
        };
    }

    // 声明队列------使用RabbitMQ缺省交换器（TopicExchange）

    /**
     * 声明队列最简单的方式
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(RmConst.QUEUE_HELLO);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(RmConst.QUEUE_USER);
    }

//    =========topic交换器  start===========
//    声明队列
    @Bean
    public Queue queueEmailMessage() {
        return new Queue(RmConst.QUEUE_TOPIC_EMAIL);
    }

    @Bean
    public Queue queueUserMessage() {
        return new Queue(RmConst.QUEUE_TOPIC_USER);
    }

//    声明交换器
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RmConst.EXCHANGE_TOPIC);
    }

//    绑定队列到交换器
    @Bean
    public Binding bindingEmailExchange() {
        return BindingBuilder
                .bind(queueEmailMessage())
                .to(topicExchange())
                .with("sb.*.email");
    }

    @Bean
    public Binding bindingUserExchange() {
        return BindingBuilder
                .bind(queueUserMessage())
                .to(topicExchange())
                .with("sb.*.user");
    }
//   =========topic交换器 end=============

//    ===========fanout交换器 start=========
//    声明队列
    @Bean
    public Queue AMessage() {
        return new Queue("sb.fanout.A");
    }

//    声明交换器
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RmConst.EXCHANGE_FANOUT);
    }

//    绑定关系
    @Bean
    public Binding bindingFanoutMessage() {
        return BindingBuilder
                .bind(AMessage())
                .to(fanoutExchange());
    }
//    ===========fanout交换器 end=========



}







