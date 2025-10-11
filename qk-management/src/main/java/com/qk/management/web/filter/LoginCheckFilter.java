package com.qk.management.web.filter;

import cn.hutool.core.util.ObjectUtil;
import com.qk.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 11:39
 * @Description:
 */
@Slf4j
//@WebFilter("/*")
@SuppressWarnings("all")
public class LoginCheckFilter implements Filter {
    private static final String LOGIN_PATH = "/login";
    private static final String TOKEN_HEADER_NAME = "token";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.request cast
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //2.get request url
        String requestPath = request.getRequestURI();
        log.info("请求路径是path:{}", requestPath);
        if (ObjectUtil.equals(requestPath, LOGIN_PATH)) {
            filterChain.doFilter(request, response);
            return;
        }
        //3. get request Header name = token
        String token = request.getHeader(TOKEN_HEADER_NAME);
        if (ObjectUtil.isEmpty(token)) {
            response.setStatus(401);
            return;
        }
        //4.verify token
        try {
            Claims claims = JwtUtil.parseToken(token);
            //5.get user and do not filter
            Object id = claims.get("id");
            log.info("用户id是:{}", id);
            Object username = claims.get("username");
            log.info("用户名是:{}", username);
            //let it go
            filterChain.doFilter(request, response);
            return;
        } catch (Exception e) {
            response.setStatus(401);
            return;
        }


    }
}
