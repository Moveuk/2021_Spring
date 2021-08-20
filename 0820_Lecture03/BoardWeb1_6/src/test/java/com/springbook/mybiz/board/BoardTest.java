package com.springbook.mybiz.board;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.springbook.mybiz.board.BoardVO;
import com.springbook.mybiz.board.impl.BoardDAO;
import com.springbook.mybiz.board.impl.BoardServiceImpl;

// 테스트용 서블릿 작동함.
public class BoardTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// dao::insertBoard 작동
		BoardVO bVo = new BoardVO();
		bVo.setTitle("제목");
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(bVo);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
