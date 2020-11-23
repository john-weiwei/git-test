package com.example.springproject.demo.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author ZhangWeiWei
 * @date 2020/10/14 16:04
 * @description
 */
public class DebugInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Before: invocation=["+methodInvocation+"]");
        Object val = methodInvocation.proceed();
        System.out.println("Invocation returned");
        return val;
    }
}
