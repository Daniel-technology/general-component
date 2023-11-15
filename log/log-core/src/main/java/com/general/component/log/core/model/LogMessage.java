package com.general.component.log.core.model;

/**
 * @description: 日志解析
 * @author: liuyan
 * @create: 2022−10-22 10:11 AM
 */
public class LogMessage {

    /**
     * 方法名
     */
    protected String methodName;
    /**
     * 参数
     */
    protected String param;

    /**
     * 返回信息
     */
    protected Object returnObj;

    /**
     * 花费时间
     */
    protected long costMillis;

    /**
     * 扩展信息
     */
    protected String extMsg;

    public Object getReturnObj() {
        return returnObj;
    }

    public void setReturnObj(Object returnObj) {
        this.returnObj = returnObj;
    }

    public String getExtMsg() {
        return extMsg;
    }

    public void setExtMsg(String extMsg) {
        this.extMsg = extMsg;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getCostMillis() {
        return costMillis;
    }

    public void setCostMillis(long costMillis) {
        this.costMillis = costMillis;
    }
}
