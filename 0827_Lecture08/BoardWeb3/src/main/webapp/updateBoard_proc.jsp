<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
BoardVO bVo = new BoardVO();
bVo.setSeq(Integer.parseInt(request.getParameter("seq")));
bVo.setTitle(request.getParameter("title"));
bVo.setContent(request.getParameter("content"));

BoardDAO bDao = new BoardDAO();
bDao.updateBoard(bVo);
response.sendRedirect("getBoardList.jsp");
%>