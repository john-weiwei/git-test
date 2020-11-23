package com.cn.zww.aspect;

import com.google.gson.Gson;
import com.oracle.javafx.jmx.json.JSONDocument;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
 * @date 2020/11/18 22:37
 * @description
 */
@Component
@Aspect
public class LogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.cn.zww.controller..*(..))")
    public void requestServer() {
        //定义横切面
    }

    @Around("requestServer()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Map<String,Object> requestParams = getRequestParams(proceedingJoinPoint);
        RequestInfo requestInfo = new RequestInfo();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = proceedingJoinPoint.proceed();
        requestInfo.setIp(request.getRemoteAddr());
        requestInfo.setUrl(request.getRequestURL().toString());
        requestInfo.setHttpMethod(request.getMethod());
        requestInfo.setClassMethod(String.format("%s.%s", proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName()));
        requestInfo.setRequestParams(requestParams);
        requestInfo.setResult(result);
        LOGGER.info("Request Info      : {}", new Gson().toJson(requestInfo));

        return result;
    }

    //组装请求参数和参数值
    private Map<String,Object> getRequestParams(ProceedingJoinPoint proceedingJoinPoint) {
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

    /**
     * 抛异常处理的日志
     * @param joinPoint
     * @param e
     * @return
     */
    @AfterThrowing(pointcut = "requestServer()",throwing = "e")
    public void doThrowing(JoinPoint joinPoint,RuntimeException e) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        RequestErrorInfo requestErrorInfo = new RequestErrorInfo();
        requestErrorInfo.setIp(request.getRemoteAddr());
        requestErrorInfo.setUrl(request.getRequestURI());
        requestErrorInfo.setHttpMethod(request.getMethod());
        requestErrorInfo.setClassName(String.format("%s.%s",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName()));
        requestErrorInfo.setException(e);
        requestErrorInfo.setRequestParams(getRequestParamsByJoinPoint(joinPoint));
        LOGGER.info("Error request info {}:"+ new Gson().toJson(requestErrorInfo));
    }

    private Map<String, Object> getRequestParamsByJoinPoint(JoinPoint joinPoint) {
        Map<String,Object> requestParms = new HashMap<>();
        //参数名
        String[] paramNames = ((MethodSignature)joinPoint.getSignature()).getParameterNames();
        //参数值
        Object[] paramValues = joinPoint.getArgs();

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
}
