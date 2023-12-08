package com.general.component.common.enums;

/**
 * 删除的枚举
 *
 * @author Daniel-SESA735395
 * @date 2023/12/8
 */
public enum DataDeleteEnum {

    EFFECTIVE(1, "有效"),
    UN_EFFECTIVE(0, "逻辑删除");


    private int code;

    private String msg;

    DataDeleteEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
