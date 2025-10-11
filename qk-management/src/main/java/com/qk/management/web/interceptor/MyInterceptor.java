package com.qk.management.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 14:12
 * @Description: 演示拦截器入门
 *  1、创建拦截器类 放入spring 容器 实现HandlerInterceptor接口 重写三个方法
 *  2、配置拦截器 让拦截器生效
 *    2.1 @Configuration 注解 作用 标记当前类是一个配置类
 *  3、指定拦截路径
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("-----------------------之前-----------------------------");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("-----------------------之后-----------------------------");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("-----------------------完成-----------------------------");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
