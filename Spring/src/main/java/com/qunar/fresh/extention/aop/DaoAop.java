package com.qunar.fresh.extention.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by kingsley.zhang on 17-3-16.
 */
@Component
@Aspect
public class DaoAop {
    private static final Logger LOGGER = LoggerFactory.getLogger(DaoAop.class);

    @Pointcut("execution(* com.qunar.fresh.dao.*.*(..))")
    public void executeTime() {
    }

    @Around("executeTime()")
    public Object daoExecuteTime(ProceedingJoinPoint jp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object object = null;
        try {
            LOGGER.info("================com.qunar.fresh.dao包下的方法 {} 开始执行 ", jp.getSignature().getName());
            object = jp.proceed();
            long endTime = System.currentTimeMillis();
            LOGGER.info("================around " + jp + " 执行了 : " + (endTime - startTime) + "毫秒");
        } catch (Throwable throwable) {
            LOGGER.error(throwable.getMessage(), throwable);
            long endTime = System.currentTimeMillis();
            LOGGER.info("around " + jp + " 使用了 : " + (endTime - startTime) + "毫秒, Exception:{}", throwable.getMessage());
            throw throwable;
        }
        return object;
    }
}
