package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.util.SqlSessionFactoryBean;

@Repository("boardDAO")
public class BoardDAO {
	private SqlSession mybatis;

	public BoardDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}

	public void insertBoard(BoardVO vo) {
		// board-mapping.xml에서 찾아서 알아서 매핑해준다.
		// vo에 #{}에 대한 값을 알아서 get 메소드로 자동 매핑 해준다.
		mybatis.insert("BoardDAO.insertBoard", vo);
		// 자동 커밋이므로 상관없지만 속성 설정을 이용해서 설정할 수 있다.
		mybatis.commit();
	}
	
	public void updateBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.updateBoard", vo);
		mybatis.commit();
	}
	
	public void deleteBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoard",vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		return mybatis.selectList("BoardDAO.getBoardList",vo);
	}
	
}
