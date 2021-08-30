# Lecture09  BoardWeb3
Key Word : 트랜잭션, Web에서의 Spring 사용 예제를 위한 기본 구성      

<hr/>

 ## 기존 프로젝트에 이어서..
 
 [BoardWeb3 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0827_Lecture08/BoardWeb3)
 
 
 <br><hr/>
 
## 1. Web에서의 Spring사용 - Model1

### 1.2 게시물 리스트 - jsp 버전 이어서..

 제목 클릭시 상세보기 페이지로 이동.

```jsp
<%
			for (BoardVO board : boardList) {
			%>
			<tr>
				<td><%=board.getSeq()%></td>
				<td><a href="getBoard.jsp?seq=<%=board.getSeq()%>"><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getRegDate()%></td>
				<td><%=board.getCnt()%></td>
			</tr>
<%}%>
```

**결과 화면**
![image](https://user-images.githubusercontent.com/84966961/131270784-30b30110-cd2b-42df-bb72-933aa9e50685.png)

 
 <br><hr/>
 
### 1.3 게시물 상세보기

```jsp
<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String seq = request.getParameter("seq");
BoardVO bVo = new BoardVO();
bVo.setSeq(Integer.parseInt(seq));

BoardDAO bDao = new BoardDAO();
bVo = bDao.getBoard(bVo);
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
		<a href="logout_proc.jsp">Log-out</a>
		<hr />
		<form action="updateBoard_proc.jsp" method="post"">
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
		<a href="insertBoard.jsp">글등록</a>&nbsp;&nbsp;&nbsp;
		<a href="deleteBoard.jsp?seq=<%=bVo.getSeq()%>">글삭제</a>&nbsp;&nbsp;&nbsp;
		<a href="getBoardList.jsp">글목록</a>
	</center>
</body>
</html>
```


**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/131274753-fef5cfde-e6eb-4409-af43-ab6abddb2be1.png)






 
 <br><hr/>
 
### 1.4 게시물 삭제

```jsp
<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String seq = request.getParameter("seq");
BoardVO bVo = new BoardVO();
bVo.setSeq(Integer.parseInt(seq));

BoardDAO bDao = new BoardDAO();
bDao.deleteBoard(bVo);
response.sendRedirect("getBoardList.jsp");
%>
```



 
 <br><hr/>
 
### 1.5 게시물 등록

게시물 정보를 request로 받아올 때는 항시 인코딩 세팅에 대해 확인해주어야 한다. 그렇지 않으면 다음과 같이 문자가 깨지게 된다.

```jsp
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
```


**결과 화면**    

![image](https://user-images.githubusercontent.com/84966961/131277296-b9b4c6fa-9ccb-4dbf-95a8-e2eea59b063c.png)

 
 <br><hr/>
 
### 1.6 게시물 수정

```jsp
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
```


**결과 화면**    
 
 <br><hr/>
 
### 1.7 게시물 수정

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
session.invalidate();
response.sendRedirect("getBoardList.jsp");
%>
```
 
 <br><hr/>
 
## 2. Web에서의 Spring사용 - MVC2

요청을 받아 바로 처리하는 Model1은 dao dto에 대한 코드들이 혼재되어있다.   

이번에는 MVC2 방식을 이용하여 다음과 같은 구성으로 web을 구현해보자.

![image](https://user-images.githubusercontent.com/84966961/131281922-813c6d1e-dcf5-4ff7-9eba-f4c64463909f.png)


[BoardWeb3.1 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0830_Lecture09/BoardWeb3.1)
 
 
 <br><hr/>
 
### 2.1 URI 멀티 바인딩(*.do)

```java
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		System.out.println(path);
	}
}
```


**결과 화면**    
 
![image](https://user-images.githubusercontent.com/84966961/131282874-e4753f44-99fc-41de-ac6a-bab4b08bec5f.png)

요청된 주소값 : http://localhost:8181/biz/login.do


 
 <br><hr/>
 
### 2.2    









