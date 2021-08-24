package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 글등록
	public void insertBoard(BoardVO bVo) {
		System.out.println("insertBoard() 기능 처리");
//		String sql = "INSERT INTO board1(seq,title,writer,content) VALUES ((SELECT COUNT(*) FROM board1)+1,?,?,?)";
		// count의 문제점 중간에 데이터가 없어지면 max값과 count값이 달라져서 중복되어 PK에 대한 오류가 발생될 수 있음.
		String sql = "INSERT INTO board1(seq,title,writer,content)"
				+ " VALUES ((SELECT nvl(max(seq), 0)+1 FROM board1),?,?,?)";
		JDBCUtil ut = new JDBCUtil();
		
		try {
			conn = ut.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 테스트용 작동함.
//			pstmt.setString(1, "제목");
//			pstmt.setString(2, "글쓴이");
//			pstmt.setString(3, "내용");
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getWriter());
			pstmt.setString(3, bVo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ut.close(pstmt, conn);
		}
	}
}
