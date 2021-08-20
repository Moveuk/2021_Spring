package com.springbook.biz.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.springbook.biz.common.JDBCUtil;
import com.springbook.biz.user.UserVO;

@Repository("userDAO")
public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public void insertUser(UserVO uVo) {
		System.out.println("insertUser 기능 처리");
		String sql = "INSERT INTO users VALUES(?,?,?,?)";
		
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
