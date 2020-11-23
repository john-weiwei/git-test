package com.zww.controller;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/9
 * @Description:
 * 消息生产者
 */
@Controller
@RequestMapping("rabbitmq")
public class RabbitMqController {

    private Logger log = Logger.getLogger(RabbitMqController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 通过fanout交换器发送消息
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/fanoutSender")
    public String fanoutSender(@RequestParam("message")String message) {
        String opt = "";
        try {
            for (int i = 0; i < 2; i++) {
                String msg = "Fanout,message_"+i+"is : "+message;
                log.info("**********send message ["+msg+"]");
                // TODO: 2020/11/12 生产者发送消息
                rabbitTemplate.send("fanout-exchange","",new Message(msg.getBytes(),new MessageProperties()));
            }
            opt = "suc";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }


    @ResponseBody
    @RequestMapping("/topicSender")
    public String topicSender(@RequestParam("message")String message){
        String opt="";
        try {
            String[] routekeys={"king","mark","james"};
            String[] modules={"kafka","jvm","redis"};
            for(int i=0;i<routekeys.length;i++){
                for(int j=0;j<modules.length;j++){
                    String routeKey = routekeys[i]+"."+modules[j];
                    String str = "Topic,the message_["+i+","+j+"] is [rk:"+routeKey+"][msg:"+message+"]";
                    log.info("**************************Send Message:["+str+"]");
                    //todo 生产者发送消息 属性可以自由配置
                    MessageProperties messageProperties = new MessageProperties();
                    rabbitTemplate.send("topic-exchange",
                            routeKey,
                            new Message(str.getBytes(), messageProperties));
                }
            }
            opt = "suc";
        } catch (Exception e) {
            opt = e.getCause().toString();
        }
        return opt;
    }

}
