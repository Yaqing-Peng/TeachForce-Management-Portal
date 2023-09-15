package com.itheima.filter;

import com.github.pagehelper.util.StringUtil;
import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.itheima.utils.JwtUtils;

@Slf4j
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        //1.获取请求路径
        String url = req.getRequestURI();
        log.info("请求路径: {}",url);

        //2.判断是否是login相关的请求, 如果包含, 直接放行
        if(url.contains("/login")){
            filterChain.doFilter(req,resp);
            return;
        }
        //3.获取请求中的token
        String token = req.getHeader("Token");

        //4.判断token 是否存在, 如果不存在,返回错误提示(未登录)
        if(!StringUtils.hasLength(token)){
            log.info("token为空, 未登录");
            Result error = Result.error("NOT_LOGIN");
            resp.getWriter().write(error.toString());
            return;
        }

        //5.如果存在, 解析token, 如果解析失败, 返回错误提示(未登录)
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token解析失败, 未登录");
            Result error = Result.error("NOT_LOGIN");
            resp.getWriter().write(error.toString());
            return;
        }

        //6.获取用户信息, 放行
        log.info("token解析成功, 已登录");
        filterChain.doFilter(req,resp);

    }
}
