<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%
	/* List<BoardVO> boardList = (List)session.getAttribute("boardList"); */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>글목록</h1>
		<h3>
			${userName }님! 게시판에 오신걸 환영합니다... <a href="logout.do">Log-out</a>
		</h3>
		
		
		<!-- 검색 시작 -->
		<form action="getBoardList.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
					<select name="searchCondition">
						<c:forEach items="${conditionMap }" var="option">
							<option value="${option.value }">${option.key }
						</c:forEach>	
					</select> 
					<input name="searchKeyword" type="text" /> <input type="submit" value="검색" /></td>
				</tr>
			</table>
		</form>
		
		<table border=1 cellpadding=0 cellspacing=0 width=700>
			<tr>
				<th bgcolor="orange" widht=100>번호</th>
				<th bgcolor="orange" widht=100>제목</th>
				<th bgcolor="orange" widht=100>작성자</th>
				<th bgcolor="orange" widht=100>등록일</th>
				<th bgcolor="orange" widht=100>조회수</th>
			</tr>
			<c:forEach items="${boardList }" var="board">
			<tr>
				<td>${board.seq }</td>
				<td>
					<a href="getBoard.do?seq=${board.seq }">
						${board.title }
					</a>
				</td>
				<td>${board.writer }</td>
				<td>${board.regDate }</td>
				<td>${board.cnt }</td>
			</tr>
			</c:forEach>
		</table>
		<a href="insertBoard.jsp">새글 등록</a>
	</center>
</body>
</html>










