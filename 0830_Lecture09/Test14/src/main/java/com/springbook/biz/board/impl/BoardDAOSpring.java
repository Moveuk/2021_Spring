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
	
		return jdbcTemplate.query(BOARD_LIST, boardRowMapper);
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
	}
}	
