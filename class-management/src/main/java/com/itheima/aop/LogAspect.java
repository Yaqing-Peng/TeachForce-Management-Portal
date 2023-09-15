package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Component
@Aspect//声明这是一个切面
public class LogAspect {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.itheima.anno.Log)")
    public Object recordLog(ProceedingJoinPoint pjp) throws Throwable{
        //1.记录操作人ID-当前登录员工的ID. 获取请求头中的jwt令牌,解析出员工ID
        String token  = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");

        //2.操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //3.操作类名
        String className = pjp.getTarget().getClass().getName();
        //4.操作方法名
        String methodName = pjp.getSignature().getName();
        //5.操作方法参数
        Object[] args = pjp.getArgs();
        String methodParams = Arrays.toString(args);

        //执行目标方法
        long begin = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();

        //6.操作方法返回值
        String returnValue = result.toString();

        //7.操作耗时
        long costTime = end - begin;

        //8.封装日志对象
        OperateLog operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, methodParams, returnValue, costTime);
        operateLogMapper.insert(operateLog);
        log.info("AOP记录操作日志: {}",operateLog);

        return result;
    }
}
