package com.example.springproject.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author ZhangWeiWei
 * @date 2020/9/29 15:51
 * @description
 * 注入一个验证工厂（ValidatorFactory），或者一个验证器（Validator）
 */
@Configuration
public class AppConfig {

    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
