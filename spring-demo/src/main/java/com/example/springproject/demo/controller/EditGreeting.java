package com.example.springproject.demo.controller;

import com.example.springproject.demo.service.IGreetingService;
import com.example.springproject.demo.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;

/**
 * @author ZhangWeiWei
 * @date 2020/9/18 18:38
 * @description
 */
@RestController
//@SessionAttributes("greeting") //
public class EditGreeting {

    @Autowired
    private IGreetingService greetingService;

    @Resource
    private IHelloService helloService;

    @GetMapping("/hello")
    public String helloKity() {

        return "hi";
    }

    @GetMapping("/greeting/sayHello")
    public String sayHello(@RequestParam("msg") String msg, @RequestParam("name") String name) {
        System.out.println(msg);
        System.out.println(name);
        greetingService.sayHello(msg,name);
        return "Ok";
    }
}
