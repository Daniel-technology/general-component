package com.general.component.log.callback;

import com.general.component.log.model.LogMessage;

/**
 * @description: 日志回调
 * @author: liuyan
 * @create: 2022−10-23 8:55 PM
 */
public interface LogCallback<T extends LogMessage> {

    /**
     * 日志回调
     * @param logMsg    日志信息
     */
    default void callback(T logMsg) {
    }
}
