package com.qk.management.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * @Author: RightSquare
 * @Date: 2025/10/11 10:55
 * @Description:
 */
@WebFilter("/*")
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("-----------过滤器执行了---------------------");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
