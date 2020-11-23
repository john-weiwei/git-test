package com.example.springproject.demo.interceptor;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import java.util.logging.Logger;

/**
 * @author ZhangWeiWei
 * @date 2020/10/12 16:49
 * @description aop示例，这是一个切面
 * 切面可以有方法和字段，还可以包含切入点、增强和引介
 */
//@Component
@Aspect
public class LogAspect {

    private Logger log = Logger.getLogger("LogAspect");

    /**
     * 环绕增强（通知）
     * 环绕通知，在方法执行之前和之后执行，如果需要
     * 以线程安全的方式（例如启动和停止计时器）在方法执行之前和之后共享状态，
     * 则通常使用环绕通知
     *
     * 环绕增强的第一个参数必须是ProceedingJoinPoint，调用它的
     * proceed()方法可以得到执行方法的相应体内容
     *
     * 环绕通知返回的值是该方法的调用者看到的返回值
     * @param proceedingJoinPoint
     * @return
     */
    @Around("com.example.springproject.demo.interceptor.CommonPointCuts.inController()")
    public Object aroundAspectMethod(ProceedingJoinPoint proceedingJoinPoint) {
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();//输出controller的执行结果
            System.out.println(result.toString());
            proceedingJoinPoint.getArgs();//获取请求参数
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        log.info("拦截了controller的执行");
        return result;
    }

    /**
     * service执行正常返回之后输出
     * returning属性中使用的名称必须与增强方法中的参数名称相对应。
     * 当方法返回时，该返回值将作为相应的参数值传递到通知方。
     * returning也限制了只能匹配到返回指定类型的值
     */
    @AfterReturning(pointcut = "com.example.springproject.demo.interceptor.CommonPointCuts.inService()",
    returning = "strValue")
    public void afterReturningMethod(String strValue) {
        strValue = "传递过来的参数值";
        log.info("拦截service执行返回之后输出【正常结束返回通知】");
    }

    /**
     * service执行正常或异常返回之后输出
     * 通过用于释放资源
     */
    @After("com.example.springproject.demo.interceptor.CommonPointCuts.inService()")
    public void after(JoinPoint joinPoint){
        log.info("拦截service执行返回之后输出【方法正常或异常结束通知】");
    }

    /**
     * before在service方法执行前拦截
     */
    @Before("com.example.springproject.demo.interceptor.CommonPointCuts.inService()")
    public void before() {
        log.info("在service执行之前拦截");
    }

    /**
     * service执行抛出异常后输出
     * throwing属性中使用的名称必须与增强方法中的参数名称相对应。
     * 当通过抛出异常退出方法执行时，该异常将作为相应的参数值传递
     * 给通知方法。throwing也限制了只能匹配到返回指定类型的值
     */
    @AfterThrowing(pointcut = "com.example.springproject.demo.interceptor.CommonPointCuts.inService()",
    throwing = "ex")
    public void afterException(Exception ex) {
        log.info("拦截抛出异常的业务");
    }

    /**
     * @Pointcut 只是定义了切入点
     */
    //匹配所以公共方法
    @Pointcut("execution(public * * ())")
    public void anyPublicOperation() {
    }

    //匹配控制器包下的所有方法
    @Pointcut("execution(* com.example.springproject.demo.controller.*.*(..)) ")
    public void inController() {

    }

    //在方法执行以上两个切点
    @After("anyPublicOperation() && inController()")
    public void doAround() {
    }
}
