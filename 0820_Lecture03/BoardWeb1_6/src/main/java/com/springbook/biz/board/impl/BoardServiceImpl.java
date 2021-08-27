package com.springbook.biz.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	// DAO를 사용하기 위한 객체 생성
	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardVO bVo) {
		boardDAO.insertBoard(bVo);
	}

}
