package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class GetBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 상세 처리");

		String seq = request.getParameter("seq");
		BoardVO bVo = new BoardVO();
		bVo.setSeq(Integer.parseInt(seq));

		BoardDAO bDao = new BoardDAO();
		bDao.updateBoardReadCnt(bVo);
		BoardVO board = bDao.getBoard(bVo);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("board",board);
		mav.setViewName("getBoard");
		
		return mav;
	}

}
