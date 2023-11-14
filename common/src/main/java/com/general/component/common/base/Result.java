package com.general.component.common.base;

import com.general.component.common.exception.ExceptionCodeEnum;

/**
 * 返回值
 *
 * @author littlesnail
 * @create 2023−03-07 7:59 PM
 */
public class Result<T> {

    private int code;
    private String message;
    private T data;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Result() {
    }

    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return success(ExceptionCodeEnum.OK.getMessageKey(), null);
    }

    public static <T> Result<T> success(T data) {
        return success(ExceptionCodeEnum.OK.getMessageKey(), data);
    }

    public static <T> Result<T> success(String msg, T data) {
        return success(ExceptionCodeEnum.OK.getCode(), msg, data);
    }

    public static <T> Result<T> success(int code, String msg, T data) {
        return new Result(code, msg, data);
    }


    public static <T> Result<T> fail(int code, String msg, T data) {
        return new Result(code, msg, data);
    }

    public static <T> Result<T> fail() {
        return new Result(ExceptionCodeEnum.SYSTEM_UNDEFINED_ERROR.getCode(),
                ExceptionCodeEnum.SYSTEM_UNDEFINED_ERROR.getMessageKey(), null);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return fail(code, msg, null);
    }
}
