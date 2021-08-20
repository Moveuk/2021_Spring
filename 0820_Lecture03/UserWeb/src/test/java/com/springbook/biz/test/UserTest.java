package com.springbook.biz.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;

public class UserTest {

	public static void main(String[] args) throws IOException {

		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		UserService userService = (UserService) factory.getBean("userService");
		UserVO uVo = new UserVO();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("아이디 : ");
		uVo.setId(br.readLine());
		System.out.println("비밀번호 : ");
		uVo.setPassword(br.readLine());
		System.out.println("이름 : ");
		uVo.setName(br.readLine());
		System.out.println("직책 : ");
		uVo.setRole(br.readLine());

		userService.insertUser(uVo);
		factory.close();
		
	}

}
