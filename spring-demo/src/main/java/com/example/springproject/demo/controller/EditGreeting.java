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
import java.util.HashMap;
import java.util.Map;

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
    public Object helloKity() {
        String result = helloService.sayHelloToYou();
        Map<String,Object> obj =  new HashMap<>();
        obj.put("code",100);
        obj.put("msg","成功");
        obj.put("result",result);
        return obj;
    }


    @GetMapping("/divisor")
    public Object divisor() {
        helloService.throwExcepMethod();
        Map<String,Object> obj =  new HashMap<>();
        obj.put("code",100);
        obj.put("msg","成功");
        obj.put("result","");
        return obj;
    }

    @GetMapping("/greeting/sayHello")
    public String sayHello(@RequestParam("msg") String msg, @RequestParam("name") String name) {
        System.out.println(msg);
        System.out.println(name);
        greetingService.sayHello(msg,name);
        return "Ok";
    }
}
