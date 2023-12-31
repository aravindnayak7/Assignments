package com.stackroute.newz.aspect;

import java.util.Arrays;
import java.util.Date;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Annotate this class with @Aspect and @Component */

public class LoggerAspect {

	/*
	 * Write loggers for each of the methods of NewsController, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */

	/*
	 * Write loggers for each of the methods of controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
//	@Before("execution(com.stackroute.newz.controller.*)")
//	public void logBefore(){
//		System.out.println("@Before:"+new Date());
//	}
//	
//	@After("execution(com.stackroute.newz.controller.*)")
//	public void logAfter(){
//		System.out.println("@After:"+new Date());
//	}
//	
//	@AfterThrowing(pointcut = "execution(com.stackroute.newz.controller.*)",
//			throwing="exception")
//	public void logAfterThrowing(Exception exception){
//		System.out.println("@AfterReturning:"+new Date());
//		System.out.println("Exception caught:"+ exception.getMessage());
//	}
//	
//	@AfterReturning(pointcut = "execution(com.stackroute.newz.controller.*)",
//			returning="val")
//	public void logAfterReturning(Object val){
//		System.out.println("Method return value:"+ val);
//		System.out.println("@AfterReturning:"+new Date());
//	}

}
