package com.qk.management.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.qk.management.aop.annotation.OverviewRedis;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


/**
 * @Author: RightSquare
 * @Date: 2025/10/17 20:44
 * @Description:
 */
@Slf4j
@Aspect
@Component
@SuppressWarnings("all")
public class ReportOverviewWithCacheAspect {
    @Autowired
    private StringRedisTemplate redisClient;

    @Pointcut("@annotation(com.qk.management.aop.annotation.OverviewRedis)")
    public void pt() {
    }


    @Around("pt()")
    public Object around(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Object resultValue = null;
        String redisKey = null;
        long expireTime = 0;
        TimeUnit expireTimeUnit = null;
        String jsonStr = null;
        Method method = null;
        try {
            Class<?> targetClass = pjp.getTarget().getClass();
            //根据方法获取注解
            //获取方法名
            String methodName = pjp.getSignature().getName();
            //根据方法名获取返回值类型
            method = targetClass.getMethod(methodName);
            OverviewRedis annotation = method.getAnnotation(OverviewRedis.class);
            //获取缓存的key = 前缀+方法名
            redisKey = annotation.keyPrefix() + pjp.getSignature().getName();
            //获取注解中的过期时间
            expireTime = annotation.expireTime();
            //获取注解中的过期时间单位
            expireTimeUnit = annotation.expireTimeUnit();
            //查询缓存
            jsonStr = redisClient.opsForValue().get(redisKey);
            //命中直接 返回 不执行 目标方法
        } catch (Exception e) {
            log.error("从redis中查询缓存失败", e);
        }

        if (ObjectUtil.isNotEmpty(jsonStr)) {
            return JSONUtil.toBean(jsonStr, method.getReturnType());
        }
        //没有命中 执行目标方法 并添加到数据库
        try {
            resultValue = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


        try {
            //添加到数据库
            redisClient.opsForValue().set(redisKey, JSONUtil.toJsonStr(resultValue), expireTime, expireTimeUnit);
        } catch (Exception e) {
            log.info("添加缓存失败", e.getMessage());
        }

        return resultValue;
    }
}
