# Lecture08 BoardWeb2 Spring Transaction & BoardWeb3
Key Word : 트랜잭션, Web에서의 Spring 사용 예제를 위한 기본 구성      

<hr/>

 ## 기존 프로젝트에 이어서..
 
 [BoardWeb2 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0824_Lecture05/BoardWeb2)
 
 
 <br><hr/>
 
## 1. 트랜잭션

### 1.1 트랜잭션 처리를 위한 bean 등록

![image](https://user-images.githubusercontent.com/84966961/131094882-f13c80f0-5aad-4349-aac7-40e588256b6a.png)

![image](https://user-images.githubusercontent.com/84966961/131094943-b34dee13-56e3-492e-8523-c27efbd37758.png)




<br><br>

### 1.2 트랜잭션 처리

일부러 트랜잭션 오류가 나도록 insert를 2번 입력하게끔 메소드를 설정하여 오류를 내게 되면 txManager가 자동으로 예외를 받아 이전 상태로 Rollback 처리를 해준다.



 <br><br>
<hr>
 
# BoardWeb3 Web Spring 
 
 <br><br>
 
## 1. Web에서의 Spring사용

 기존 BoardWeb1.6 버전의 src 파일들을 복사하여 프로젝트 생성 후 정리해주었다.

<br><br>

### 1.1 로그인 폼 구성 

![image](https://user-images.githubusercontent.com/84966961/131095549-b6deb605-45d9-4c75-98f2-73296af4636e.png)   

```jsp
	<center>
		<h1>로그인</h1>
		<hr />
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange">아이디</td>
					<td>
						<input type="text" name="id" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">비밀번호</td>
					<td>
						<input type="text" name="password" />
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="로그인" />
					</td>
				</tr>
			</table>
		</form>
	</center>
```


<br><br><hr>

### 1.2 로그인 기능 처리 jsp

```jsp
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
```

<br><br><hr>

### 1.2 게시물 리스트 - jsp 버전

![image](https://user-images.githubusercontent.com/84966961/131095218-cf020b18-ea3f-4717-a2fc-be860b109f61.png)

```jsp
<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
BoardVO bVo = new BoardVO();
BoardDAO bDao = new BoardDAO();
List<BoardVO> boardList = bDao.getBoardList(bVo);
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
			테스트님 환영합니다...
			<a href="logout_proc.jsp">Log-out</a>
		</h3>
		<!-- 검색 시작 -->
		<form action="getBoardList.jsp" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="TITLE">제목
							<option value="CONTENT">내용
						</select>
						<input name="searchKeyword" type="text" />
						<input value="검색" type="submit" />
					</td>
				</tr>
			</table>
		</form>
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<th bgcolor="orange" width="100">번호</th>
				<th bgcolor="orange" width="100">제목</th>
				<th bgcolor="orange" width="100">작성자</th>
				<th bgcolor="orange" width="100">등록일</th>
				<th bgcolor="orange" width="100">조회수</th>
			</tr>
			<%
			for (BoardVO board : boardList) {
			%>
			<tr>
				<td><%=board.getSeq()%></td>
				<td><%=board.getTitle()%></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getRegDate()%></td>
				<td><%=board.getCnt()%></td>
			</tr>
			<%
			}
			%>
		</table>
		<a href="insertBoard.jsp">새글 등록</a>
	</center>
</body>
</html>
```
   
<br>
 

<br><br> <hr>

