package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {

	@RequestMapping("/login.do")
	public String login(UserVO uVo, UserDAO userDAO, HttpSession session) {
		System.out.println("로그인 처리");

		// 1. 사용자 입력 정보 추출
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
//		UserVO uVo = new UserVO();
//		vo.setId(id);
//		vo.setPassword(password);
//		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(uVo);

		// 3. 화면 네비게이션
//		ModelAndView mav = new ModelAndView();
		// viewResolver가 prefix와 suffix를 붙여 사용하게 해준것처럼 mav도 가지고 있다.
		if(user!=null) {
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
		 	
		
		
		// 기존 네비게이션 삭제
//		if (user != null) {
//			System.out.println("로그인 작동");
//			return "getBoardList.do";
//		} else {
//			System.out.println("로그인 작동 실패");
//			return "login";
//		}

	}

}
