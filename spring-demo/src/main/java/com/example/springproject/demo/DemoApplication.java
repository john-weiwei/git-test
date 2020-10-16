package com.example.springproject.demo;

import com.example.springproject.demo.enums.TypeEnum;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.annotation.security.RunAs;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
//启用AspectJ自动代理支持
@EnableAspectJAutoProxy
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        TypeEnum.FORBIDDEN.addArticle();
    }

    private static void testDB() {

    }

}
