package com.cn.zww.controller;

import com.cn.zww.fanout.FanoutSender;
import com.cn.zww.hello.DefaultSender;
import com.cn.zww.topic.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ZhangWeiWei
 * @Date:2020/11/16
 * @Description:
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitTest {

    @Autowired
    private DefaultSender defaultSender;
    @Autowired
    private TopicSender topicSender;
    @Autowired
    private FanoutSender fanoutSender;

    /**
     * 普通类测试
     */
    @GetMapping("/hello")
    public void hello() {
        defaultSender.send("hello msg!");
    }

    /**
     * topic 类交换器
     */
    @GetMapping("/topicTest")
    public void topicTest() {
        topicSender.send();
    }

    /**
     *
     */
    @GetMapping("/fanoutTest")
    public void fanoutTest() {
        fanoutSender.sendMessage("hello Msg:OK");
    }
}
