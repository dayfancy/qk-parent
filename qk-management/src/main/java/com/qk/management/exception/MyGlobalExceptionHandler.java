package com.qk.management.exception;

import com.qk.common.Result;
import com.qk.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: RightSquare
 * @Date: 2025/10/7 9:51
 * @Description:
 */
@Slf4j
@RestControllerAdvice
public class MyGlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        log.error("错误信息: {}",e.getMessage());
        return Result.error("您的网络有问题，请稍后重试！");
    }
    @ExceptionHandler(value = CommonException.class)
    public Result error(CommonException e) {
        return Result.error(e.getMessage());
    }


}
