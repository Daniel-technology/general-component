package com.general.component.common.exception;

import com.general.component.common.base.Result;

/**
 * 全局异常默认处理器
 *
 * @author littlesnail
 * @create 2023−03-07 7:56 PM
 */
public class DefultExceptionHandler {


    /**
     * 未知异常
     *
     * @param exception
     * @return
     */
    public Result<Object> defaulErrorHandler(Exception exception) {
        return Result.fail();
    }


    /**
     * @ExceptionHandler 匹配抛出自定义的异常类型 CustomException
     */
    public Result<Object> customExceptionHandler(CustomException exception) throws Exception {
        return Result.fail(exception.getCode(), exception.getMessage(), null);
    }


}
