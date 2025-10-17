package com.qk.management.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.qk.vo.portal.PortalVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.qk.management.service.impl.ReportOveviewServiceImpl.CACHE_PORTAL_KEY_PREFIX;


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
    public Object around(ProceedingJoinPoint pjp) {


        Object resultValue = null;
        try {
            //1.定义缓存key
            String redisKey = CACHE_PORTAL_KEY_PREFIX + "reportOverview";
            //2.1从redis中查询缓存 缓存命中
            String jsonStr = null;
            try {
                jsonStr = redisClient.opsForValue().get(redisKey);
                if (ObjectUtil.isNotEmpty(jsonStr)) {
                    return JSONUtil.toBean(jsonStr, PortalVO.class);
                }
            } catch (Exception e) {
                log.info("从redis中查询缓存失败", e);
            }


            resultValue = pjp.proceed();


            try {
                //从数据库查询的数据添加到Redis中
                String json = JSONUtil.toJsonStr(resultValue);
                redisClient.opsForValue().set(redisKey, json);
            } catch (Exception e) {
                log.info("从数据库查询的数据添加到Redis中失败", e);
            }


        } catch (Throwable e) {
            log.info("around方法执行异常", e);
            throw new RuntimeException(e);
        }


        return resultValue;
    }
}
