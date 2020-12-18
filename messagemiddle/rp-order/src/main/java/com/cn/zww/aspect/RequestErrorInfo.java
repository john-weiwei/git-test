package com.cn.zww.aspect;

/**
 * @author ZhangWeiWei
 * @date 2020/11/18 22:35
 * @description
 */
public class RequestErrorInfo {

    private String ip;
    private String url;
    private String httpMethod;
    private String className;
    private Object requestParams;
    private RuntimeException exception;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(Object requestParams) {
        this.requestParams = requestParams;
    }

    public RuntimeException getException() {
        return exception;
    }

    public void setException(RuntimeException exception) {
        this.exception = exception;
    }

    public RequestErrorInfo() {
    }
}
