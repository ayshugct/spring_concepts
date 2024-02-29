package com.ayshu.gct.spring.aop.concept.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Pointcut("within(com.ayshu.gct.spring.aop.concept.controller..*)"
			+ " || within(com.ayshu.gct.spring.aop.concept.service..*)"
			+ " || within(com.ayshu.gct.spring.aop.concept.repository..*)"
			)
	public void allMethodExec() {
		
	}

	/*
	 * The Around advice takes priority on the Before advice when executing same
	 * methods
	 */
	@Before(value="allMethodExec()")
	public void beforeMethodLog(JoinPoint joinPoint) {
		if(log.isDebugEnabled()) {
			log.debug("Before method " + joinPoint.getSignature() + ": ");
		}
	}
	
	@Around(value="allMethodExec()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
		if(log.isDebugEnabled()) {
			log.debug("Enter {}.{} with arguments = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
		}
		
		try {
			Object result = joinPoint.proceed();
			if(log.isDebugEnabled()) {
				log.debug("Exit {}.{} with arguments = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), joinPoint.getArgs());
			}
			return result;
		} catch(IllegalArgumentException e) {
			log.error("Illegal Argument {} in {}.{}", joinPoint.getArgs(), joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			throw e;
		}
	}
	
	/*
	 * The after advice takes priority on the Around advice when executing same
	 * methods
	 */
	@After(value="allMethodExec()")
	public void afterMethodLog(JoinPoint joinPoint) {
		if(log.isDebugEnabled()) {
			log.debug("After method " + joinPoint.getSignature() + ": ");
		}
	}
	
	/*
	 * The after returning advice takes priority over the after advice when
	 * executing same methods
	 */
	@AfterReturning(value="allMethodExec()", returning = "result")
	public void afterReturningMethod(JoinPoint joinPoint, Object result) {
		if(log.isDebugEnabled()) {
			log.debug("After returning method {} with results as {}", joinPoint.getSignature(), result);
		}
	}
	
	@AfterThrowing(pointcut="allMethodExec()", throwing="e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{} with cause {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), e.getMessage());
	}
}
