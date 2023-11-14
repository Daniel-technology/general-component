package com.general.component.common.exception;

/**
 * 自定义异常
 *
 * @author littlesnail
 * @date 2023−03-07 7:52 PM
 */
public class CustomException extends RuntimeException {

    /**
     * @Fields serialVersionUID :
     */
    private static final long serialVersionUID = 1L;
    /**
     * 错误信息
     */
    private String message;

    /**
     * 异常码
     */
    private int code;


    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomException() {
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
