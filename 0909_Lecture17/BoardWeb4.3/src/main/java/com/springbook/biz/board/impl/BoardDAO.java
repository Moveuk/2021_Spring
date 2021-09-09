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
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
   //글등록
	public void insertBoard(BoardVO vo) {
		System.out.println("insertBoard() 기능처리");
		
		String BOARD_INSERT = "insert into board1(seq, title, writer, content) "
				+ "values((select nvl(max(seq), 0)+1 from board1),?,?,?)";
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	// 글 목록 조회
			public List<BoardVO> getBoardList(BoardVO vo) {
				System.out.println("===> JDBC로 getBoardList() 기능 처리");
				
				List<BoardVO> boardList = new ArrayList<BoardVO>();
				String BOARD_LIST = "select * from board1 order by seq desc";
				
				try {
					conn = JDBCUtil.getConnection();
					stmt = conn.prepareStatement(BOARD_LIST);
					rs = stmt.executeQuery();
					
					while (rs.next()) {
						BoardVO board = new BoardVO();
						board.setSeq(rs.getInt("SEQ"));
						board.setTitle(rs.getString("TITLE"));
						board.setWriter(rs.getString("WRITER"));
						board.setContent(rs.getString("CONTENT"));
						board.setRegDate(rs.getDate("REGDATE"));
						board.setCnt(rs.getInt("CNT"));
						boardList.add(board);
					}		
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.close(rs, stmt, conn);
				}	
				return boardList;
			}
			
			
			public BoardVO getBoard(BoardVO vo) {
				System.out.println("===> JDBC로 getBoard() 기능 처리");
				
				BoardVO board = null;
				String BOARD_GET = "select * from board1 where seq=?";
				
				try {
					conn = JDBCUtil.getConnection();
					stmt = conn.prepareStatement(BOARD_GET);
					stmt.setInt(1, vo.getSeq());
					rs = stmt.executeQuery();
					
					if (rs.next()) {
						board = new BoardVO();
						board.setSeq(rs.getInt("SEQ"));
						board.setTitle(rs.getString("TITLE"));
						board.setWriter(rs.getString("WRITER"));
						board.setContent(rs.getString("CONTENT"));
						board.setRegDate(rs.getDate("REGDATE"));
						board.setCnt(rs.getInt("CNT"));
					}		
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.close(rs, stmt, conn);
				}	
				
				return board;
			}
			
			public void updateBoard(BoardVO vo) {
				System.out.println("===> JDBC로 updateBoard() 기능 처리");
				String BOARD_UPDATE = "update board1 set title=?, content=? where seq=?";
				
				try {
					conn = JDBCUtil.getConnection();
					stmt = conn.prepareStatement(BOARD_UPDATE);
					stmt.setString(1, vo.getTitle());
					stmt.setString(2, vo.getContent());
					stmt.setInt(3, vo.getSeq());
					stmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.close(stmt, conn);
				}
			}

			public void deleteBoard(BoardVO vo) {
				System.out.println("===> JDBC로 deleteBoard() 기능 처리");
				String BOARD_DELETE = "delete board1 where seq=?";
				
				try {
					conn = JDBCUtil.getConnection();
					stmt = conn.prepareStatement(BOARD_DELETE);
					stmt.setInt(1, vo.getSeq());
					stmt.executeUpdate();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCUtil.close(stmt, conn);
				}
			}
}
