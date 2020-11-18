package com.cn.zww.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangWeiWei
 * @date 2020/11/18 21:45
 * @description
 */
@Component
@Aspect
public class RequestLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("execution(* com.cn.zww.controller..*(..))")
    public void requestServer() {
        //定义横切面
    }

    @Before("requestServer()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.info("=================start===============");
        LOGGER.info("IP: {}",request.getRemoteAddr());
        LOGGER.info("URL: {}",request.getRequestURI());
        LOGGER.info("RequestMethod: {}",request.getMethod());
        LOGGER.info("Class Name: {}.{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("RequestParam: {}",getRequestParam(proceedingJoinPoint));
        LOGGER.info("Result: {}",result);
        return result;
    }

    //获取参数对应的值
    private Map<String,Object> getRequestParam(ProceedingJoinPoint proceedingJoinPoint) {
        Map<String,Object> requestParms = new HashMap<>();

        //参数名
        String[] paramNames = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = proceedingJoinPoint.getArgs();
        for (int i = 0; i < paramNames.length; i++) {
            Object value = paramValues[i];
            //如果是文件对象
            if (value instanceof MultipartFile) {
                MultipartFile multipartFile = (MultipartFile) value;
                value = multipartFile.getOriginalFilename();//获取文件名
            }
            requestParms.put(paramNames[i],value);
        }
        return requestParms;
    }

    @After("requestServer()")
    public void doAfter() {
        LOGGER.info("=====================end=============");
    }
}
