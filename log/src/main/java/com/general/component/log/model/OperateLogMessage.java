package com.general.component.log.model;

/**
 * @description: 操作日志
 * @author: liuyan
 * @create: 2022−10-24 12:16 PM
 */
public class OperateLogMessage extends LogMessage {

    /**
     * 请求方法类型
     *
     * @see org.springframework.web.bind.annotation.RequestMethod;
     */
    private String requestMethodType;

    private String userAgent;

    private String classPath;

    private String host;

    private String desc;

    private String userIp;

    private String exCode;

    private String exMessage;

    private String exDetail;

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestMethodType() {
        return requestMethodType;
    }

    public void setRequestMethodType(String requestMethodType) {
        this.requestMethodType = requestMethodType;
    }

    public String getExCode() {
        return exCode;
    }

    public void setExCode(String exCode) {
        this.exCode = exCode;
    }

    public String getExMessage() {
        return exMessage;
    }

    public void setExMessage(String exMessage) {
        this.exMessage = exMessage;
    }

    public String getExDetail() {
        return exDetail;
    }

    public void setExDetail(String exDetail) {
        this.exDetail = exDetail;
    }
}
