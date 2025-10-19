package com.qk.common.enums;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 15:59
 * @Description: ParamEnum
 */
public enum ParamEnum implements Code {
    PARAM_ERROR("参数错误", 10001),
    LOGIN_USER_NOT_EXIST("该用户不存在", 10002),
    LOGIN_USER_PASSWORD_ERROR("密码错误", 10003),
    //删除失败
    DELETE_ERROR("删除失败", 10004),
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
