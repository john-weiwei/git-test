package com.example.springproject.demo.controller;

import com.example.springproject.demo.entity.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ZhangWeiWei
 * @date 2020/9/17 18:22
 * @description
 */
@RestController
public class GreetingController {
    private static final String temp = "Hello,%s";
    private final AtomicInteger count = new AtomicInteger();
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name",defaultValue = "World") String name) {
        return new Greeting(count.incrementAndGet(),String.format(temp,name));
    }
}
