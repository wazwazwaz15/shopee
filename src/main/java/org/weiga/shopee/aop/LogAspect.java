package org.weiga.shopee.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    private ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Around("@annotation(org.weiga.shopee.annotation.LogExecution)")
    public Object logExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        threadLocal.set(System.currentTimeMillis());

        String methodName = joinPoint.getSignature().getName();
        logger.info("👉 開始執行方法: {}", methodName);


        Object result = joinPoint.proceed();

        long end = threadLocal.get() - System.currentTimeMillis();
        logger.info("✅ 結束執行方法: {}, 耗時: {} ms", methodName, end);
        threadLocal.remove();
        return result;
    }


}
