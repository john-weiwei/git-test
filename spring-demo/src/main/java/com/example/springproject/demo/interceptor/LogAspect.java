package com.example.springproject.demo.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ZhangWeiWei
 * @date 2020/10/12 16:49
 * @description aop示例，这是一个切面
 * 切面可以有方法和字段，还可以包含切入点、增强和引介
 */
@Aspect
public class LogAspect {

    @Pointcut("")
    public void aspectMethod() {

    }

    //匹配所以公共方法
    @Pointcut("execution(public * * ())")
    public void anyPublicOperation() {
    }

    //匹配控制器包下的所有方法
    @Pointcut("execution(* com.example.springproject.demo.controller.*.*(..)) ")
    public void inController() {
    }

    //在方法执行以上两个切点
    @Pointcut("anyPublicOperation() && inController()")
    public void doAround() {
    }
}
