package com.qk.common.enums;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 15:59
 * @Description: ParamEnum
 */
public enum ParamEnum implements Code {
    PARAM_ERROR("参数错误", 10001),
    ;
    private final String msg;
    private final int code;

    ParamEnum(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
