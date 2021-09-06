package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class GetBoardController {

	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO bVo, BoardDAO bDao, ModelAndView mav) {
		System.out.println("글 상세 처리");

//		String seq = request.getParameter("seq");
//		BoardVO bVo = new BoardVO();
//		bVo.setSeq(Integer.parseInt(seq));

//		BoardDAO bDao = new BoardDAO();
		bDao.updateBoardReadCnt(bVo);
		BoardVO board = bDao.getBoard(bVo);
		
		// 해당 정보를 mav에 넣어 전송
//		HttpSession session = request.getSession();
//		session.setAttribute("bVo", board);
		
//		ModelAndView mav = new ModelAndView();
		mav.addObject("board",board);
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}

}
