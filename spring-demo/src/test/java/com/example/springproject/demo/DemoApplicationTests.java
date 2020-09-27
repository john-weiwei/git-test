package com.example.springproject.demo;

import com.example.springproject.demo.entity.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {

        int a = 0;
        System.out.println("运行test");
    }

    @Test
    void test() {
        String str = null;
        AtomicInteger atomicInteger = new AtomicInteger();
        Greeting greeting = new Greeting(atomicInteger.incrementAndGet(),"hi");
        System.out.println(greeting.toString());
        greeting.setShow(true);
        System.out.println(greeting.toString());

    }


}
