package com.example.springproject.demo.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author ZhangWeiWei
 * @date 2020/10/12 18:08
 * @description 公共切面
 */
@Aspect
public class CommonPointCuts {

    //如果定义了方法，则连接点位于服务包或者子包下面
    @Pointcut("within(com.example.springproject.demo.service..*) ")
    public void inService() {
    }

    //如果定义了方法，则连接点位于数据访问层包或者子包下面
    @Pointcut("within(com.example.springproject.demo.mapper..*) ")
    public void inDao() {
    }

    //匹配控制器包下的所有方法
    @Pointcut("execution(* com.example.springproject.demo.controller.*.*(..)) ")
    public void inController() {
    }
}
