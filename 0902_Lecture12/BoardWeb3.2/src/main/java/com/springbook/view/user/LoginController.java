package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

public class LoginController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그인 처리");

		// 1. 사용자 입력 정보 추출
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);

		// 3. 화면 네비게이션
		ModelAndView mav = new ModelAndView();
		// viewResolver가 prefix와 suffix를 붙여 사용하게 해준것처럼 mav도 가지고 있다.
		if(user != null) {
			mav.setViewName("getBoardList.do");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		} else {
			mav.setViewName("login.jsp");
		}
		
		
		return mav;
		
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
