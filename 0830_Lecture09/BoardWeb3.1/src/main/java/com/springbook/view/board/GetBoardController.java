package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.view.controller.Controller;

public class GetBoardController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 상세 처리");

		String seq = request.getParameter("seq");
		BoardVO bVo = new BoardVO();
		bVo.setSeq(Integer.parseInt(seq));

		BoardDAO bDao = new BoardDAO();
		BoardVO board = bDao.getBoard(bVo);

		HttpSession session = request.getSession();
		session.setAttribute("bVo", board);

		return "getBoard";
	}

}
