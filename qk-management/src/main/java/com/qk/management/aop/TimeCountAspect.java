package com.qk.management.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 11:37
 * @Description:
 */
@Slf4j
@Aspect
@Component
public class TimeCountAspect {
    //定义一个切点方法
    @Pointcut("execution(* com.qk.management.service.*.*(..))")
    public void timeCount(){}
    //定义一个通知方法
    @Around( "timeCount()")
    public Object TimeCountAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object proceed = pjp.proceed();

        long endTime = System.currentTimeMillis();
        //获取类名
        String className = pjp.getTarget().getClass().getName();
        //获取方法名
        String methodName = pjp.getSignature().getName();
        log.info(className+" 类中的 "+methodName+" 方法耗时：{}", endTime - startTime);


        return proceed;
    }

}
