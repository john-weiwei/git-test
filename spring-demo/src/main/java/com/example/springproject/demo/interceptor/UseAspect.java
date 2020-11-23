package com.example.springproject.demo.interceptor;

import com.example.springproject.demo.service.IHelloService;
import com.example.springproject.demo.service.impl.HelloServiceImpl;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * @author ZhangWeiWei
 * @date 2020/10/13 16:35
 * @description
 */
//@Component
@Aspect
public class UseAspect {

    private Logger log = Logger.getLogger("UseAspect");

    @DeclareParents(value = "com.example.springproject.demo.service.*+",defaultImpl = HelloServiceImpl.class)
    private IHelloService helloService;

    @Before("com.example.springproject.demo.interceptor.CommonPointCuts.inService() && this(helloService)")
    public void aspectMethod(IHelloService helloService) {
        log.info("aspectMethod 在sayHello调用前执行");
        helloService.sayHelloToYou();
    }
}
