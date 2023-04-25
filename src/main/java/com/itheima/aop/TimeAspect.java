package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect//aop类
@Component
@Slf4j
public class TimeAspect {
    @Around("execution(* com.itheima.Service.*.*(..))")//切入点表达式
    public Object recordTime(ProceedingJoinPoint JoinPoint) throws Throwable {
        //记录开始时间
        long begin = System.currentTimeMillis();
        //调用原始方法
        Object proceed = JoinPoint.proceed();
        //记录结束时间
        long end = System.currentTimeMillis();
        log.info(JoinPoint.getSignature()+"方法执行耗时:{}ms",end-begin);

        return proceed;

    }
}
