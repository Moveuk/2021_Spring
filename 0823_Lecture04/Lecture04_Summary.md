# Lecture04 BoardWeb에 User 구성, Test13
Key Word : CRUD 기능 추가, Test13

<hr/>

 ## BoardWeb에 User 구성
    
<br>
    
지난 시간 따로 프로젝트를 만들었던 User를 합쳤다.    
    
![image](https://user-images.githubusercontent.com/84966961/130414097-a05f1d1d-4a26-4b6b-ab6f-50a695eef781.png)    
    
    
또한, Bean을 등록해서 사용하는 법과 component scan 방식을 사용할 때는 @annotation을 추가하여야 한다는 사실을 기억하자.   
   

**UserDAO.java**   
```java
package com.springbook.mybiz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.springbook.mybiz.common.JDBCUtil;
import com.springbook.mybiz.user.UserVO;

@Repository("userDAO")
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	// 회원정보 조회
	public UserVO getUser(UserVO uVo) {
		UserVO user = null;
		
		System.out.println("getUser 기능 처리");
		String sql = "SELECT * FROM users1 WHERE id = ? and password = ?";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uVo.getId());
			pstmt.setString(2, uVo.getPassword());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user = new UserVO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRole(rs.getString("role"));
			} else {
				System.out.println("빈값입니다.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, pstmt, conn);;
		}
		
		return user;
	}
	
	public void insertUser(UserVO uVo) {
		System.out.println("insertUser 기능 처리");
		String sql = "INSERT INTO users1 VALUES(?,?,?,?)";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uVo.getId());
			pstmt.setString(2, uVo.getPassword());
			pstmt.setString(3, uVo.getName());
			pstmt.setString(4, uVo.getRole());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
}
```

 <br><br><hr/>


 ## Test13 : BoardWeb CRUD 기능 완성
    

  기능 구현   
![image](https://user-images.githubusercontent.com/84966961/130413577-49042fc3-9e9b-4a6a-84f9-7a2c22f7bd55.png)    




**BoardDAO.java**   
```java
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
	
	// 글 등록
	public void insertBoard(BoardVO bVo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		String sql = "INSERT INTO board1(seq,title,writer,content)"
				+ " VALUES ((SELECT nvl(max(seq), 0)+1 FROM board1),?,?,?)";
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getWriter());
			pstmt.setString(3, bVo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
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
```

**결과 화면**    
   
 ![image](https://user-images.githubusercontent.com/84966961/130413460-561d4612-2259-461a-975a-22c00e282c49.png)   
![image](https://user-images.githubusercontent.com/84966961/130413487-bd65e4f6-ab2e-49d6-ada6-bbc20deda95f.png)   




 <br><br><hr/>


