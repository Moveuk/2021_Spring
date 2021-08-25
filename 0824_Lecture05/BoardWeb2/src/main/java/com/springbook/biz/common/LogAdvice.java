package com.springbook.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

// 비즈니스 로직과 관련되어 사용되기 때문에 서비스로 빈을 등록함.
@Service
// aop Aspect화 xml에 사용한 코드와 동일하다.
@Aspect
public class LogAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*get(..))")
	public void getPointcut() {
		
	}
	
	// 공통 관심사 메소드
	@Before("allPointcut()")
	public void printLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
