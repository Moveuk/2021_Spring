package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOSpring {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String BOARD_INSERT = "insert into board1(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board1),?,?,?)";
	//private final String BOARD_INSERT = "insert into board1(seq, title, writer, content) values(?,?,?,?)";
	private final String BOARD_UPDATE = "update board1 set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board1 where seq=?";
	private final String BOARD_GET = "select * from board1 where seq=?";
	private final String BOARD_LIST = "select * from board1 order by seq desc";
	
	//CRUD
	
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getTitle(),vo.getWriter(),vo.getContent());
		//jdbcTemplate.update(BOARD_INSERT,vo.getSeq(), vo.getTitle(),vo.getWriter(),vo.getContent());
	}
	
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
		
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
	}
	
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
	}
	
	
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}
	
	public BoardVO getBoard(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		Object[] args = { vo.getSeq() };
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

}
