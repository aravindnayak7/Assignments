package com.stackroute.newz.aspect;

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

import com.stackroute.newz.model.News;


/* Annotate this class with @Aspect and @Component */
@Component
@Aspect
public class LoggerAspect {

	/*
	 * Write loggers for each of the methods of controller, any particular method
	 * will have all the four aspectJ annotation
	 * (@Before, @After, @AfterReturning, @AfterThrowing).
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);
	@Pointcut("execution(* com.stackroute.newz.controller.NewsController.*(..))")
	 public void allMethodsPointcut() {}

	 @Before(value = "execution(* com.stackroute.newz.controller.NewsController.*(..))")
	 public void beforeNewsExecution() {
	 	System.out.println("Executing controller method...");
	 }

	 @After(value = "execution(* com.stackroute.newz.controller.NewsController.*(..))")
	 public void afterMethodExecution() {
	 	System.out.println("Controller method execution completed.");
	 }

	 @Before(value = "execution(* com.stackroute.newz.controller.NewsController.*(..)) && args(newsObj)")
	 public void beforeMethodExecutionWithArgs(News newsObj) {
	 	System.out.println("Executing controller method with args...");
	 }

	 @After(value = "execution(* com.stackroute.newz.controller.NewsController.*(..)) && args(newsObj)")
	 public void afterNewsExecutionWithArgs(News newsObj) {
	 	System.out.println("Controller method with args execution completed.");
	 }
	
}
