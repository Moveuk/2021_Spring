package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public void insertBoard(BoardVO bVo) {
		boardDAO.insertBoard(bVo);
	}

	@Override
	public void updateBoard(BoardVO bVo) {
		boardDAO.updateBoard(bVo);
		
	}

	@Override
	public void deleteBoard(BoardVO bVo) {
		boardDAO.deleteBoard(bVo);
		
	}

	@Override
	public BoardVO getBoard(BoardVO bVo) {
		return boardDAO.getBoard(bVo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO bVo) {
		return boardDAO.getBoardList(bVo);
	}

}
