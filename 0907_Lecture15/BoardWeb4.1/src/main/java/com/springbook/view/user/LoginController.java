package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {

	// 단순 페이지 이동을 위한 매핑 기능을 할 수도 있음.
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String loginView(/* @RequestMapping("user") */UserVO uVo, UserDAO userDAO) {
		System.out.println("get method를 이용한 로그인 처리");

		uVo.setId("admin");
		uVo.setPassword("1234");
		
		return "login.jsp";

	}
	
	
	// 로그인을 위한 post 처리 가능.
	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public String login(UserVO uVo, UserDAO userDAO, HttpSession session) {
		System.out.println("로그인 처리");

		UserVO user = userDAO.getUser(uVo);

		if(userDAO.getUser(uVo)!=null) {
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}

	}

}
