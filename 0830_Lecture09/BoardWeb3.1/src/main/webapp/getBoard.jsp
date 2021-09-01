<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
BoardVO bVo = (BoardVO)session.getAttribute("bVo");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>글 상세</h1>
		<a href="logout.do">Log-out</a>
		<hr />
		<form action="updateBoard.do" method="post">
			<input name="seq" type="hidden" value="<%=bVo.getSeq()%>">
			<table border="1" cellpadding="0" cellspacing=0>
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td>
						<input type="text" name="title" value="<%=bVo.getTitle()%>" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td><%=bVo.getWriter()%></td>
				</tr>
				<tr>
					<td bgcolor="orange">내용</td>
					<td>
						<textarea rows="10" cols="40" name="content"><%=bVo.getContent()%></textarea>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">등록일</td>
					<td><%=bVo.getRegDate()%></td>
				</tr>
				<tr>
					<td bgcolor="orange">조회수</td>
					<td><%=bVo.getCnt()%></td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 수정" />
					</td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="insertBoard.do">글등록</a>&nbsp;&nbsp;&nbsp;
		<a href="deleteBoard.do?seq=<%=bVo.getSeq()%>">글삭제</a>&nbsp;&nbsp;&nbsp;
		<a href="getBoardList.do">글목록</a>
	</center>
</body>
</html>