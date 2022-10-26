package com.springbook.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Aspect
public class AroundAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		String method = pjp.getSignature().getName();
		StopWatch sw = new StopWatch();
		System.out.println("Around Advice 전 - 비즈니스 로직 전,후 두 시점 모두 실행");
		sw.start();
		Object returnObj = pjp.proceed();
		sw.stop();
		System.out.println("Around Advice 후 - 비즈니스 로직 전,후 두 시점 모두 실행");
		System.out.println(method+"() 메소드 수행에 걸린 시간 : "+ sw.getTotalTimeMillis()+"(ms)초");
		return returnObj;
	}
}
