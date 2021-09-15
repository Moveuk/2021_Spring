package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.user.UserService;
import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.impl.UserDAO;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;   //UserServiceImpl
	
	@RequestMapping(value= "/login.do", method=RequestMethod.GET)
	public String loginView(/* @ModelAttribute("user") */UserVO vo) {
		
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}

	@RequestMapping(value= "/login.do", method=RequestMethod.POST)
	public String login(UserVO vo,HttpSession session) {
		
		if(vo.getId() == null || vo.getId().equals("")) {
			throw new IllegalArgumentException("아이디를 반드시 입력하세요");
		}
		
		
		UserVO user = userService.getUser(vo);
		
		System.out.println(user.getId());
		System.out.println(user.getPassword());
		System.out.println(user.getName());
		
		if (user != null) {
			
			session.setAttribute("userName", user.getName());
			
			return "getBoardList.do";
		}
		else
			return "login.jsp";
	}

}