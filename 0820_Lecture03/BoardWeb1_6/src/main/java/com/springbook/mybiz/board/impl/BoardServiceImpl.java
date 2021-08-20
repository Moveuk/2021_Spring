package com.springbook.mybiz.board.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.mybiz.board.BoardService;
import com.springbook.mybiz.board.BoardVO;

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
