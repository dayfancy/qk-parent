package com.qk.common.exception;

import com.qk.common.enums.Code;

/**
 * @Author: RightSquare
 * @Date: 2025/10/10 16:02
 * @Description:
 */
public class CommonException extends BaseException{

    public CommonException(Code code) {
        super(code.getMsg());
    }
    public static void throwCommonException(Code code) {
        throw new CommonException(code);
    }
}
