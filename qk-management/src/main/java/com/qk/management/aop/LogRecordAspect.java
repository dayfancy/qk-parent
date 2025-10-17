package com.qk.management.aop;

import cn.hutool.json.JSONUtil;
import com.qk.common.Result;
import com.qk.common.constant.ResultConstants;
import com.qk.common.util.CurrentUserContextHolders;
import com.qk.entity.OperateLog;
import com.qk.management.mapper.OperateMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: RightSquare
 * @Date: 2025/10/17 19:49
 * @Description: 业务系统日志切面类
 */
@Slf4j
@Aspect
@Component
@SuppressWarnings("all")
public class LogRecordAspect {
    @Autowired
    private OperateMapper operateMapper;

    @Pointcut("@annotation(com.qk.management.aop.annotation.LogAnno)")
    public void pt(){}


    @Around( "pt()")
    public Object log(ProceedingJoinPoint pjp){

        Object resultValue = null;
        try {
            long startTime = System.currentTimeMillis();



            /// //////////////////////////////////////////////////////////////
            /// //////////////////////////////////////////////////////////////
            resultValue = pjp.proceed();
            /// //////////////////////////////////////////////////////////////
            /// //////////////////////////////////////////////////////////////
            long endTime = System.currentTimeMillis();
            //获取操作人的Id
            Integer id = CurrentUserContextHolders.get();
            //操作类名
            String className = pjp.getTarget().getClass().getName();
            //操作方法名
            String methodName = pjp.getSignature().getName();
            //操作的方法的参数
            Object[] args = pjp.getArgs();
            //转成Json字符串
            String params = JSONUtil.toJsonStr(args);
            //操作返回值
            String result = JSONUtil.toJsonStr(resultValue);

            //创建一个对象
            OperateLog operateLog = OperateLog.builder()
                    .operateUserId( id)
                    .operateTime(LocalDateTime.now())
                    .className(className)
                    .methodName(methodName)
                    .methodParams( params)
                    .returnValue( result)
                    .costTime(endTime - startTime)
                    .build();
            operateMapper.insert(operateLog);

        } catch (Throwable e) {
            log.error("操作失败:{}", e.getMessage());
            resultValue = Result.error(ResultConstants.FAIL);
        }

        return resultValue;
    }
}
