package com.springbook.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class GetBoardListController {

	@RequestMapping(value = "/getBoardList.do")
	public ModelAndView getBoardList(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
		System.out.println("글 목록 검색 처리");

//		BoardVO vo = new BoardVO();
//		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);

		// 스프링 Model And View를 사용하므로써 필요 없어짐.
//		session.setAttribute("boardList", boardList);

//		ModelAndView mav = new ModelAndView();

		// 기존에는 리스트를 세션에 넘겼지만 mav는 내부에 저장할 수 있다.
		mav.addObject("boardList", boardList);
		mav.setViewName("getBoardList.jsp");

		return mav;
	}

}
