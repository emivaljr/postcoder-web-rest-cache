package com.fexco.postcoder.webrestcache.infra.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by emival on 19/09/16.
 */
@Component
@Aspect
public class LogAspect {

    @Around("execution(* com.fexco.postcoder.webrestcache.restapi.*.*(..)) || execution(* com.fexco.postcoder.webrestcache.integration.*.*(..))")
    public Object logAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        Logger log = LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
        log.info(joinPoint.getSignature().getName()+" - begin ");
        Object result = joinPoint.proceed();
        log.info(joinPoint.getSignature().getName()+" - end ");
        return result;
    }
}
