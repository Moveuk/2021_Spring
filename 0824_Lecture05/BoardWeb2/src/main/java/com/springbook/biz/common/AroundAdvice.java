package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
//		System.out.println("[BEFORE] : 비즈니스 메소드 수행 전에 처리할 내용...");
//		Object returnObj = pjp.proceed();
//		System.out.println("[AFTER] : 비즈니스 메소드 수행 후에 처리할 내용...");
//		
//		System.out.println(returnObj);
//		
//		return returnObj;
		
		// 비즈니스 로직 실행 시간 측정
		StopWatch stopWatch = new StopWatch();
		// 비지니스 로직 전 시작
		stopWatch.start();
		// 비지니스 로직 실행
		Object obj = pjp.proceed();
		// 스탑워치 종료
		stopWatch.stop();
		System.out.println("() 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)밀리초");
		
		return obj;
	}
}
