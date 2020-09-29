package com.example.springproject.demo.service;

import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 9:17
 * @description
 */
public interface IGreetingService {

    void sayHello(@NotNull String msg, @NotNull String name);
}
