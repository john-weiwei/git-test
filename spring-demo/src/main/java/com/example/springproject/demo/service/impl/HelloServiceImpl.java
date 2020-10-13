package com.example.springproject.demo.service.impl;

import com.example.springproject.demo.service.IHelloService;
import org.springframework.stereotype.Service;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 15:17
 * @description
 */
@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHelloToYou() {
        String hello = "hello man";
        System.out.println(hello);
        return hello;
    }

    @Override
    public String throwExcepMethod() {
        int dividend = 1;//被除数
        int divisor = 0;//除数
        int result = dividend / divisor;
        System.out.println(result);
        return String.valueOf(result);
    }
}
