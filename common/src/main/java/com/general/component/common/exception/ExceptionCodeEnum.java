package com.general.component.common.exception;

/**
 * 异常码
 *
 * @author littlesnail
 * @create 2023−03-07 8:03 PM
 */
public enum ExceptionCodeEnum {
    OK(10000, "system.message.ok"),
    PENDING(10001, "system.message.pending"),
    LOGIN_FAILED(20001, "system.message.login_failed"),
    UNAUTHORIZED(20002, "system.message.unauthorized"),
    FORBIDDEN(20003, "system.message.forbidden"),
    SYSTEM_UNDEFINED_ERROR(40000, "system.undefined.error"),
    SYSTEM_CONFIG_ERROR(40001, "system.config.error"),
    VALIDATE_ERROR(31000, "validate.error"),
    VALIDATE_ERRORS(31001, "validate.errors"),
    VALIDATE_ILLEGAL_JSON_HTTP_METHOD(31002, "validate.illegal_json_http_method"),
    VALIDATE_FIELD_REQUIRED(31003, "validate.field.required"),
    VALIDATE_FIELD_ERROR(31004, "validate.field.error"),
    VALIDATE_FIELD_LIST_ILLEGAL_COUNT(31004, "validate.field.list_illegal_count"),
    VALIDATE_NO_SLIDER_VERIFICATION_CODE(31005, "validate.no_slider_verification_code"),
    VALIDATE_NO_FILE(31006, "validate.no_file"),
    VALIDATE_SLIDER_VERIFICATION_CODE_ERROR(31007, "validate.slider_verification_code_error"),
    VALIDATE_NO_PHONE_ERROR(31008, "validate.no_phone_error"),


    SYSTEM_REQUEST_NOT_CACHED(40002, "system.request_not_cached"),
    SYSTEM_DATA_MULTI_COLUMN(41000, "system.data.multi_column"),
    SYSTEM_DATA_LOGIC_DELETE_NOT_SUPPORT(41003, "system.data.logic_delete_not_support");

    private int code;
    private String messageKey;

    private ExceptionCodeEnum(int code, String messageKey) {
        this.code = code;
        this.messageKey = messageKey;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessageKey() {
        return this.messageKey;
    }
}
