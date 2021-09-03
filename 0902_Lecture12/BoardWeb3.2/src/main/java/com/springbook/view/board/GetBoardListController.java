package com.springbook.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 목록 검색 처리");

		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);

		// 스프링 Model And View를 사용하므로써 필요 없어짐.
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("user");
//		session.setAttribute("boardList", boardList);
		
		ModelAndView mav = new ModelAndView();
		// 기존에는 리스트를 세션에 넘겼지만 mav는 내부에 저장할 수 있다.
		mav.addObject("boardList", boardList);
		if(user != null) {
			mav.setViewName("getBoardList");
		} else {
			mav.setViewName("login.jsp");
		}
		
		return mav;
	}

}
