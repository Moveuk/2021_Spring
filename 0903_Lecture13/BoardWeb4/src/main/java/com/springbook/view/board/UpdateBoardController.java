package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class UpdateBoardController{

	@RequestMapping("/updateBoard.do")
	public ModelAndView updateBoard(BoardVO bVo,BoardDAO bDao,ModelAndView mav) {
		System.out.println("글 수정 처리");

		bDao.updateBoard(bVo);
		
		mav.setViewName("redirect:getBoardList.do");
		
		return mav;
	}

}
