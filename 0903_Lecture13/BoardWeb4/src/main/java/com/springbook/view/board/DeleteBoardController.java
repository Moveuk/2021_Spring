package com.springbook.view.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class DeleteBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("글 삭제 처리");
		
		String seq = request.getParameter("seq");
		BoardVO bVo = new BoardVO();
		bVo.setSeq(Integer.parseInt(seq));

		BoardDAO bDao = new BoardDAO();
		bDao.deleteBoard(bVo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		
		return mav;
	}


}
