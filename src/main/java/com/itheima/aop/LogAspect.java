package com.itheima.aop;

import com.alibaba.fastjson.JSONObject;
import com.itheima.Utils.JwtUtils;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.el.parser.Token;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private HttpServletRequest request;
    @Around("@annotation(com.itheima.annotation.LogAnnotation)")
    public Object recordlog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long strattime = System.currentTimeMillis();
        //调用原始方法
        Object proceed = proceedingJoinPoint.proceed();
        Long endtime = System.currentTimeMillis();
        //记录日志


        //获取操作人id
        String token = request.getHeader("token");
        Claims claims = JwtUtils.parseJWT(token);
        Integer operateUser = (Integer) claims.get("id");
        //获取操作时间
        LocalDateTime operateTime = LocalDateTime.now();
        //操作类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //操作方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //操作方法参数
        String methodParams = proceedingJoinPoint.getArgs().toString();
        //操作方法返回值
        String returnValue = JSONObject.toJSONString(proceed);
        //操作耗时
        Long costTime = endtime-strattime;


        OperateLog operateLog = new OperateLog(null,operateUser,operateTime,className,methodName,methodParams,returnValue,costTime);
        operateLogMapper.insert(operateLog);
        log.info("aop记录操作日志:{}",operateLog);
        return proceed;
    }
}
