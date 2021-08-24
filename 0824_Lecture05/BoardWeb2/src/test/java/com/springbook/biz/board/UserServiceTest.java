package com.springbook.biz.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

// 테스트용 클래스
public class UserServiceTest {

	public static void main(String[] args) throws IOException {
		System.out.println("Test 작동");
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService) factory.getBean("userService");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		UserVO uVo = new UserVO();
		
		System.out.println("아이디를 입력해 주세요.");
		uVo.setId(br.readLine());
		System.out.println("비밀번호를 입력해 주세요.");
		uVo.setPassword(br.readLine());
		
		if (uVo.getId().isBlank()) uVo.setId("admin");
		if (uVo.getPassword().isBlank()) uVo.setPassword("1234");
		
		UserVO user = userService.getUser(uVo);
		
		if(user != null) {
			System.out.println(user.getName()+"님 환영합니다.");
		} else {
			System.out.println("로그인 실패");
		}
		
		
		factory.close();
	}

}
