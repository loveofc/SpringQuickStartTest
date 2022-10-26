package com.springbook.biz.common;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardVO;


@Service
@Aspect
public class AfterThrowingAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@AfterThrowing(pointcut="getPointcut()", throwing="returnObj")	
	public void exceptionLog(JoinPoint jp, Exception returnObj) {
		String method = jp.getSignature().getName();
		System.out.println("After Throwing Advice - 비즈니스 로직 예외 발생 후 동작");
		System.out.println("메소드 이름 : "+method+" 예외 메시지 "+returnObj.getMessage());
	}
}
