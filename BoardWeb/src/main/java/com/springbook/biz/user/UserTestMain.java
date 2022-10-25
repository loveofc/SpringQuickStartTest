package com.springbook.biz.user;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.springbook.biz.user.impl.UserService;

public class UserTestMain {
	public static void main(String[] args) {
		
		AbstractApplicationContext ctx =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService =(UserServiceImpl)ctx.getBean("userService");
		
		for(int i=6; i<=7;i++) {
			UserVO vo = new UserVO();
			vo.setId("user"+i);
			vo.setPassword("1234"+i);
			vo.setName("name"+i);
			vo.setRole("회원"+i);
			//입력 테스트
			userService.insertUser(vo);
			System.out.println("입력완료"+i);
		}
		
		//출력테스트
		List<UserVO> lists = userService.getUserList(null);
		for(UserVO v: lists) {
			System.out.println(v.toString());
		}
		
		ctx.close();
		
	}
}
