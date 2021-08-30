package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	// 글 수정
	public void updateBoard(BoardVO bVo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		String sql = "UPDATE board1 SET title=?, content=? WHERE seq=?";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getContent());
			pstmt.setInt(3, bVo.getSeq());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO bVo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		String sql = "DELETE FROM board1 WHERE seq=?";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bVo.getSeq());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}
	
	// 글 상세보기
	public BoardVO getBoard(BoardVO bVo) {
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		String sql = "SELECT * FROM board1 WHERE seq=?";
		BoardVO detail = new BoardVO();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bVo.getSeq());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				detail.setSeq(rs.getInt("seq"));
				detail.setTitle(rs.getString("title"));
				detail.setWriter(rs.getString("writer"));
				detail.setContent(rs.getString("content"));
				detail.setRegDate(rs.getDate("regDate"));
				detail.setCnt(rs.getInt("cnt"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return detail;
	}
	
	// 글 리스트
	public List<BoardVO> getBoardList(BoardVO bVo) {
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		String sql = "SELECT * FROM board1 ORDER BY seq DESC";
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setSeq(rs.getInt("seq"));
				boardVO.setTitle(rs.getString("title"));
				boardVO.setWriter(rs.getString("writer"));
				boardVO.setContent(rs.getString("content"));
				boardVO.setRegDate(rs.getDate("regDate"));
				boardVO.setCnt(rs.getInt("cnt"));
				list.add(boardVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return list;
	}
}
