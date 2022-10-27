package com.springbook.biz.common;

import java.util.List;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardVO;


@Service
@Aspect
public class AfterReturningAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@AfterReturning(pointcut="getPointcut()", returning="returnObj")
	public void afterRLog(JoinPoint jp, Object returnObj) {
		String method = jp.getSignature().getName();
		Object[] args = jp.getArgs();
		System.out.println("After Rturning Advice - 비즈니스 로직 수행 후 동작");
		System.out.println("메소드 이름 : "+method+" 아규먼트 정보 : "+args[0].toString());
//		List<BoardVO> lists = (List<BoardVO>)returnObj;
//		for(BoardVO vo : lists) {
//			System.out.println(vo.toString());
//		}
	}
}
