package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.Log4jAdvice;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	// DAO를 사용하기 위한 객체 생성
	@Autowired
	private BoardDAO boardDAO;
	
	//private LogAdvice log; 새로운 로그로 교체
//	private Log4jAdvice log;
	
	public BoardServiceImpl() {
		// 현재는 우리가 직접 객체 생성 but @autowired 넣어주면 자동주입도 가능함.
		// CRUD 기능이 발생할 때마다 공통으로 사용하고 싶음.
//		log = new LogAdvice();
		
		// 새로운 로그시스템을 사용하고 싶어서 교체함
//		log.printLogging();
		// 이럴 경우 아래 메소드를 모두 바꿔야함
	}

	@Override
	public void insertBoard(BoardVO bVo) {
		// 새로운 로그시스템으로 전부 바꿔줘야함.
//		log.printLog();
//		log.printLogging();
		
		boardDAO.insertBoard(bVo);
	}

	@Override
	public void updateBoard(BoardVO bVo) {
//		log.printLog();
//		log.printLogging();
		
		boardDAO.updateBoard(bVo);
		
	}

	@Override
	public void deleteBoard(BoardVO bVo) {
//		log.printLog();
//		log.printLogging();
		
		boardDAO.deleteBoard(bVo);
		
	}

	@Override
	public BoardVO getBoard(BoardVO bVo) {
//		log.printLog();
//		log.printLogging();
		
		return boardDAO.getBoard(bVo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO bVo) {
//		log.printLog();
//		log.printLogging();
		
		return boardDAO.getBoardList(bVo);
	}

}