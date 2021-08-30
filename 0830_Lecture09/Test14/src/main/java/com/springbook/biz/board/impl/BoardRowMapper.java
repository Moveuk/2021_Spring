package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardVO;

@Service
public class BoardRowMapper implements RowMapper<BoardVO> {

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		BoardVO detail = new BoardVO();
		detail.setSeq(rs.getInt("seq"));
		detail.setTitle(rs.getString("title"));
		detail.setWriter(rs.getString("writer"));
		detail.setContent(rs.getString("content"));
		detail.setRegDate(rs.getDate("regDate"));
		detail.setCnt(rs.getInt("cnt"));
		
		return detail;
	}

}
