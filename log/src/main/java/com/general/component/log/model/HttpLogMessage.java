package com.general.component.log.model;

import java.util.Map;

/**
 * @description: HttpLogMessage
 * @author: liuyan
 * @create: 2022−10-22 10:23 AM
 */
public class HttpLogMessage extends LogMessage {


    /**
     * 请求方法类型
     *
     * @see org.springframework.web.bind.annotation.RequestMethod;
     */
    private String requestMethodType;

    /**
     * 地址
     */
    private String region;

    /**
     * headers
     */
    private Map<String, Object> headers;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRequestMethodType() {
        return requestMethodType;
    }

    public void setRequestMethodType(String requestMethodType) {
        this.requestMethodType = requestMethodType;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

}
