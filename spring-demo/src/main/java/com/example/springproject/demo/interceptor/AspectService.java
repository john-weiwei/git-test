package com.example.springproject.demo.interceptor;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author ZhangWeiWei
 * @date 2020/10/13 17:00
 * @description
 */
//@Component
@Aspect("perthis(com.example.springproject.demo.interceptor.CommonPointCuts.inService())")
public class AspectService {

    private Logger log = Logger.getLogger("AspectService");

    @After("com.example.springproject.demo.interceptor.CommonPointCuts.inService()")
    public void recordService() {
        log.info("");
    }
}
