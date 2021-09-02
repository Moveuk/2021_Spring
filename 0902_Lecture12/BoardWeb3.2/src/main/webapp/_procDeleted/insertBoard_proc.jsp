<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
BoardVO bVo = new BoardVO();
bVo.setTitle(request.getParameter("title"));
bVo.setWriter(request.getParameter("writer"));
bVo.setContent(request.getParameter("content"));

BoardDAO bDao = new BoardDAO();
bDao.insertBoard(bVo);
response.sendRedirect("getBoardList.jsp");
%>