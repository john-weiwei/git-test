package com.example.springproject.demo.service.impl;

import com.example.springproject.demo.service.IGreetingService;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 9:17
 * @description
 */
@Service
public class GreetingServiceImpl implements IGreetingService {

    @Override
    public void sayHello(@NotNull String msg, @NotNull String name) {
        System.out.println("问候语："+msg+","+name);
    }
}
