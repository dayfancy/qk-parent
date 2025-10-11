package com.qk.management.web.interceptor;

import cn.hutool.core.util.ObjectUtil;
import com.qk.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 17:10
 * @Description:
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class LoginCheckInterceptor implements HandlerInterceptor {
    private static final String LOGIN_PATH = "/login";
    private static final String TOKEN_HEADER_NAME = "token";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN_HEADER_NAME);
        if (ObjectUtil.isEmpty(token)){
            log.info("请求头中没有token");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
        try {
            Claims claims = JwtUtil.parseToken(token);
            Object username = claims.get("username");
            log.info("用户名是:{}", username);
            Object id = claims.get("id");
            log.info("用户id是:{}", id);
            return true;

        } catch (Exception e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }
}
