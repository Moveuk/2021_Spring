<%@page import="com.springbook.biz.user.impl.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.springbook.biz.user.UserVO" %>

<% 
	String id = request.getParameter("id");
	String password = request.getParameter("password");

	UserVO uVo = new UserVO();
	uVo.setId(id);
	uVo.setPassword(password);

	UserDAO userDAO = new UserDAO();
		
	if (userDAO.getUser(uVo).getId() != null) {
		response.sendRedirect("getBoardList.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
	
	
%>