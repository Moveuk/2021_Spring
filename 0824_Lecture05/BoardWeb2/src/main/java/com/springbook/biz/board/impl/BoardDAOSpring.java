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
	@Autowired
	private BoardRowMapper boardRowMapper;
	
	// DAO를 직접 생성하는 것이 아니라 우리가 사용할 쿼리문만 정리함.
	// final 객체는 매개변수로 method에 들어감.
	private final String BOARD_INSERT = "INSERT INTO board1(seq,title,writer,content) "
			+ "VALUES ((SELECT nvl(max(seq), 0)+1 FROM board1),?,?,?)";
	private final String BOARD_UPDATE = "UPDATE board1 SET title=?, content=? WHERE seq=?";
	private final String BOARD_DELETE = "DELETE FROM board1 WHERE seq=?";
	private final String BOARD_GET = "SELECT * FROM board1 WHERE seq=?";
	private final String BOARD_LIST = "SELECT * FROM board1 ORDER BY seq DESC";
	
	
	//CRUD
	
	public void insertBoard(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, bVo.getTitle(), bVo.getWriter(), bVo.getContent());
	}
	
	public List<BoardVO> getBoardList(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
	
		// BOARD_LIST가 되고난 데이터를 BoardRowMapper로 보내 정리하여 리턴함
		return jdbcTemplate.query(BOARD_LIST, boardRowMapper);
		// 현재 List를 만들고 add하는 객체가 없으나 
		// query메소드가 내부적으로 List형태로 받아 리턴해주고 있음.
	}
	
	public void updateBoard(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
	
		jdbcTemplate.update(BOARD_UPDATE, bVo.getTitle(), bVo.getContent(), bVo.getSeq());
	}
	
	public void deleteBoard(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
		
		jdbcTemplate.update(BOARD_DELETE, bVo.getSeq());
	}

	public BoardVO getBoard(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");

		Object[] args = {bVo.getSeq()};
		return jdbcTemplate.queryForObject(BOARD_GET,args,new BoardRowMapper());
//		return jdbcTemplate.queryForObject(BOARD_GET, new Object [bVo.getSeq()], boardRowMapper);
	}
}	
