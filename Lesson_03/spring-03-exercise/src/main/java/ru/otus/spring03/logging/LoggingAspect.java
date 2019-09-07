package ru.otus.spring03.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ru.otus.spring03.dao.PersonDao;

@Aspect
@Component
public class LoggingAspect {
/*    @Before("execution(* ru.otus.spring03.dao.PersonDaoSimple.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
    }*/

    @Around("execution(* ru.otus.spring03.dao.PersonDaoSimple.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName());
        Object o = joinPoint.proceed();
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName() + " - Завершен.");
        return o;
    }
/*
    @After("execution(* ru.otus.spring03.dao.PersonDaoSimple.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Вызов метода : " + joinPoint.getSignature().getName() + " - Завершен.");
    }*/
}
