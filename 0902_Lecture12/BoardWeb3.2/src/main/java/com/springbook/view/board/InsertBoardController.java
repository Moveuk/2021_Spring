package com.springbook.view.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

public class InsertBoardController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 삽입 처리");

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BoardVO bVo = new BoardVO();
		bVo.setTitle(request.getParameter("title"));
		bVo.setWriter(request.getParameter("writer"));
		bVo.setContent(request.getParameter("content"));

		BoardDAO bDao = new BoardDAO();
		bDao.insertBoard(bVo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("getBoardList.do");
		
		return mav;
	}

}
