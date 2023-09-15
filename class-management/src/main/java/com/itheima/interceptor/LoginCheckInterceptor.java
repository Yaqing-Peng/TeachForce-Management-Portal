package com.itheima.interceptor;

import com.itheima.pojo.Result;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        //1.获取请求路径
        String url = req.getRequestURI();
        log.info("请求路径: {}",url);

        //2.判断是否是login相关的请求, 如果包含, 直接放行
        if(url.contains("/login")){
            return true;
        }
        //3.获取请求中的token
        String token = req.getHeader("Token");

        //4.判断token 是否存在, 如果不存在,返回错误提示(未登录)
        if(!StringUtils.hasLength(token)){
            log.info("token为空, 未登录");
            Result error = Result.error("NOT_LOGIN");
            resp.getWriter().write(error.toString());
            return false;
        }

        //5.如果存在, 解析token, 如果解析失败, 返回错误提示(未登录)
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            log.info("token解析失败, 未登录");
            Result error = Result.error("NOT_LOGIN");
            resp.getWriter().write(error.toString());
            return false;
        }

        //6.获取用户信息, 放行
        log.info("token解析成功, 已登录");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("AfterCompletion...");
    }
}
