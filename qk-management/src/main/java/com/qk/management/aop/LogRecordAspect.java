package com.qk.management.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 19:49
 * @Description: 业务系统日志切面类
 */
@Aspect
@Component
public class LogRecordAspect {

    @Pointcut("@annotation(com.qk.management.aop.annotation.LogAnno)")
    public void pt(){}


    @Around( "pt()")
    public Object log(ProceedingJoinPoint pjp){


        try {





            Object resultValue = pjp.proceed();





        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


        //TODO
        return null;
    }
}
