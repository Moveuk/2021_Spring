package com.springbook.view.board;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.view.controller.Controller;

public class DeleteBoardController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 삭제 처리");
		
		String seq = request.getParameter("seq");
		BoardVO bVo = new BoardVO();
		bVo.setSeq(Integer.parseInt(seq));

		BoardDAO bDao = new BoardDAO();
		bDao.deleteBoard(bVo);
		
		return "getBoardList.do";
	}

}
