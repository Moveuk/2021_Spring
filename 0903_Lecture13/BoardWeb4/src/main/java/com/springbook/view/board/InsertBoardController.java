package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class InsertBoardController {

	@RequestMapping(value = "/insertBoard.do")
	public String insertBoard(BoardVO bVo, BoardDAO bDao) {
//	public void handleRequest(command object... / 생략 : HttpServletRequest request) {
		// JSP의 Bean 태그 처럼 객체를 생성하게 된다.
		System.out.println("글 삽입 처리");

//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		BoardVO bVo = new BoardVO();
//		bVo.setTitle(request.getParameter("title"));
//		bVo.setWriter(request.getParameter("writer"));
//		bVo.setContent(request.getParameter("content"));

//------여기까지 커맨드 객체로 자동 설정된다.
		
//		BoardDAO bDao = new BoardDAO();
//------두번째 매개변수로 자동 DAO 객체 생성
		
		bDao.insertBoard(bVo);
		
		return "redirect:getBoardList.do";
		
	}

}
