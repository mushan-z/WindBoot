package com.wind.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author zhoubin
 * @time 2021/1/3 下午 12:21
 */
@Aspect
@Component
public class AspectDemo {

    @Pointcut("execution(* com.wind.aspect.service.impl.AspectTestServiceImpl.show(..))")
    public void myPoint(){

    }

    @Before("myPoint()")
    public void beforeShow(JoinPoint joinPoint){
        String name = (String)joinPoint.getArgs()[0];
        System.out.println("==before show "+name);
    }

    @After("myPoint()")
    public void afterShow(JoinPoint joinPoint){
        String name = (String)joinPoint.getArgs()[0];
        System.out.println("== after show "+name);
    }




    @Around("myPoint()")
    public void aroundShow(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String name = (String)proceedingJoinPoint.getArgs()[0];
        System.out.println("== around show before"+name);
        proceedingJoinPoint.proceed();
        System.out.println("== around show after"+name);
    }


    @Pointcut("execution(* com.wind.aspect.service.impl.AspectTestServiceImpl.showOut(..))")
    public void noInterPoint(){

    }

    @Before("noInterPoint()")
    public void beforeShowOut(JoinPoint joinPoint){
        String name = (String)joinPoint.getArgs()[0];
        System.out.println("==before showOut "+name);
    }

    @After("noInterPoint()")
    public void afterShowOut(JoinPoint joinPoint){
        String name = (String)joinPoint.getArgs()[0];
        System.out.println("== after showOut "+name);
    }




    @Around("noInterPoint()")
    public void aroundShowOut(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String name = (String)proceedingJoinPoint.getArgs()[0];
        System.out.println("== around showOut before"+name);
        proceedingJoinPoint.proceed();
        System.out.println("== around showOut after"+name);
    }




}
