package com.springbook.view.user;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		System.out.println("로그아웃 처리");

//		HttpSession session = request.getSession();
		session.invalidate();
		
//		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.jsp");
		
		return mav;
	}

}
