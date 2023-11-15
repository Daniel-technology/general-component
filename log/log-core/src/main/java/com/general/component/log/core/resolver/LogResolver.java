package com.general.component.log.core.resolver;


import com.general.component.log.core.model.LogMessage;

/**
 * @description: 日志解析
 * @author: liuyan
 * @create: 2022−10-22 10:08 AM
 */
public interface LogResolver {

    /**
     * 日志解析前
     *
     * @param <T>
     * @return
     * @throws Exception
     */
    <T extends LogMessage> T beforeHandle() throws Exception;

    /**
     * 日志解析后
     *
     * @param <T>
     * @return
     * @throws Exception
     */
    <T extends LogMessage> T afterHandle() throws Exception;

    /**
     * 解析
     */
    void resolve();
}
