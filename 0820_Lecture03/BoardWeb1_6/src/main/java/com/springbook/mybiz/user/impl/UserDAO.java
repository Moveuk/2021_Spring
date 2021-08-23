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
