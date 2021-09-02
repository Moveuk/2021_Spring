# Lecture12  BoardWeb3.2
Key Word : EL식 표현 사용, web.xml, 기본 서블릿 파일 설정(servlet-mapping), 인코딩 필터링(filter-mapping), 인코딩 방식 설정(filter),       

<hr/>

 ## 기존 프로젝트에 이어서..
 
 기존 프로젝트에서 minor 버전 업을 하여 Spring의 DispatcherServlet을 활용하여 직접 붙이는 프로젝트를 만들어본다.
 
 <br>
 
 **v3.2 update**
 
 기존에는 직접 Spring의 구조를 알아보기 위해 Controller, DispatcherServlet 등을 직접 만들었지만 3.2에서는 만들었던 모든 것을 삭제하여 Spring의 기능을 사용하여 구현하도록 한다.   
    
 [BoardWeb3.2 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0902_Lecture09/BoardWeb3.2)
 
 
 <br><hr/>
 
## 1. EL식으로 변환

 기존 jsp에 java 코드로 작성되어 있었던 코드들을 EL표기법으로 변경해주었다.    
    
 ![image](https://user-images.githubusercontent.com/84966961/131763293-6609e8bd-da11-45dc-9a51-babb2f002d02.png)

 <br>

주석처리 후 EL식 변경
 
<br>
 
 ![image](https://user-images.githubusercontent.com/84966961/131763333-21662ecc-f67d-4b20-8c1a-008b9cbc3ef2.png)

 
 <br><hr/>

### 1.1 getboard 변경

```jsp
<%@page import="java.util.List"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
// BoardVO bVo = (BoardVO)session.getAttribute("bVo");
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
			<input name="seq" type="hidden" value="${bVo.getSeq }">
			<table border="1" cellpadding="0" cellspacing=0>
				<tr>
					<td bgcolor="orange" width="70">제목</td>
					<td>
						<input type="text" name="title"
							value="${bVo.getTitle }" />
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">작성자</td>
					<td>${bVo.getWriter }</td>
				</tr>
				<tr>
					<td bgcolor="orange">내용</td>
					<td>
						<textarea rows="10" cols="40" name="content">${bVo.getContent }</textarea>
					</td>
				</tr>
				<tr>
					<td bgcolor="orange">등록일</td>
					<td>${bVo.getRegDate }</td>
				</tr>
				<tr>
					<td bgcolor="orange">조회수</td>
					<td>${bVo.getCnt }</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="글 수정" />
					</td>
				</tr>
			</table>
		</form>
		<hr>
		<a href="insertBoard.do">글등록</a>
		&nbsp;&nbsp;&nbsp;
		<a href="deleteBoard.do?seq=${bVo.getSeq }">글삭제</a>
		&nbsp;&nbsp;&nbsp;
		<a href="getBoardList.do">글목록</a>
	</center>
</body>
</html>
```


 
 <br><hr/>

### 1.2 getboardList 변경

**변경전 -> 변경후**
```jsp
			<%
			for (BoardVO board : boardList) {
			%>
			<tr>
				<td><%=board.getSeq()%></td>
				<td><a href="getBoard.do?seq=<%=board.getSeq()%>"><%=board.getTitle()%></a></td>
				<td><%=board.getWriter()%></td>
				<td><%=board.getRegDate()%></td>
				<td><%=board.getCnt()%></td>
			</tr>
			<%
			}
			%>
```
&nbsp;||<br>
\／
```jsp
			<c:forEach items="${boardList}" var="board">
			<tr>
				<td>${board.getSeq()}</td>
				<td><a href="getBoard.do?seq=${board.getSeq()}">${board.getTitle()}</a></td>
				<td>${board.getWriter()}</td>
				<td>${board.getRegDate()}</td>
				<td>${board.getCnt()}</td>
			</tr>
			</c:forEach>
```




 
 <br><hr/>

## 2. web.xml 파일 설명

 
![image](https://user-images.githubusercontent.com/84966961/131766008-0cdc2027-24e2-42a7-bef2-012c7428097e.png)


 schema의 j를 J 대문자로 변경(java -> Java

![image](https://user-images.githubusercontent.com/84966961/131766211-28f71d9e-92a1-4fc9-b3b7-d976120f6499.png)

![image](https://user-images.githubusercontent.com/84966961/131766230-7605313e-b14d-40ca-a9b4-0372caa1cb59.png)




 
 <br><hr/>

### 2.1 web.xml 파일 수정

1. 다음 코드를 지운다.

```xml
	<!-- The definition of the Root Spring Container shared by all Servlets and Filters -->
	<context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>/WEB-INF/spring/root-context.xml</param-value>
	</context-param>
	
	<!-- Creates the Spring Container shared by all Servlets and Filters -->
	<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
	</listener>
```

 <br><br>
 
2. 이후 중간의 init을 날려주고 servlet-name을 실제 servlet 패키지에 해당하는 값을 넣어준다.   

`*.do`라는 url-pattern을 읽어 스프링의 DispatcherServlet가 처리하도록 설정해준다.   

또한 action의 이름은 어떤 이름으로든 정의할 수 있다.

<br>

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app version="2.5" xmlns="http://java.sun.com/xml/ns/javaee"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://Java.sun.com/xml/ns/javaee https://java.sun.com/xml/ns/javaee/web-app_2_5.xsd">

	<!-- Processes application requests -->
	<servlet>
		<servlet-name>action</servlet-name>
		<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
	</servlet>
		
	<servlet-mapping>
		<servlet-name>action</servlet-name>
		<url-pattern>*.do</url-pattern>
	</servlet-mapping>

</web-app>
```

 <br><br>
 
3. DispatcherServlet 변경

충돌이 나므로 정리해준다.

![image](https://user-images.githubusercontent.com/84966961/131766479-9c9e011d-dddf-4fbb-9f11-bc60de431413.png)



 <br><br>
 
4. 이렇게 설정해놓게 되면 지금까지의 과정을 Spring에서 자동으로 처리해준다.


5. 실행시 오류

 - 현재는 단순히 web.xml에서 디스패처로 보내주기만 하기 때문에 실행시 오류가 난다.
 - 우리가 web.xml에서 설정한 `action`이라는 이름에 따라 스프링은 다음과 같은 오류처럼 `action-servlet.xml`을 찾아서 서블릿의 역할을 수행할 수 있도록 도와준다.

<br>

 -> 그러므로 우리는 Spring에게 action-servlet.xml을 제공해 주어야 한다.

![image](https://user-images.githubusercontent.com/84966961/131766780-e8961660-212d-41b6-aae8-debd231f15e9.png)

![image](https://user-images.githubusercontent.com/84966961/131766975-5dc0d151-2198-40bd-bf98-2ec34d237ba8.png)

 <br><br>
 
5. action-servlet.xml 생성

 이 파일은 웹에서 사용할 빈들을 설정해주는 xml 파일이다.   
 
 이 후 우리는 이 파일을 사용하지 않을 것이므로 없애준다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">


</beans>
```

 <br><br>
 
6. 실행 후 PageNotFound 경고 문구 출력

빨간색 오류는 안나지만 PageNotFound 뜨면서 연동이 안되었음을 표시함.

![image](https://user-images.githubusercontent.com/84966961/131767103-9c235193-77ee-4256-8af5-b5514b85ab6b.png)

![image](https://user-images.githubusercontent.com/84966961/131767176-f5c58fe9-28cf-4c9e-8153-e6e42f0aa622.png)





 <br><br>
 
7. 새로운 presentation-layer.xml 파일 생성

폴더 경로를 설정하여 Spring Bean Configuration file 을 검색하여 선택 후 새로운 파일을 생성한다.    

![image](https://user-images.githubusercontent.com/84966961/131767470-2b762a43-0921-4300-96fa-d332889e797f.png)   

![image](https://user-images.githubusercontent.com/84966961/131767358-fa4a6a2b-92d2-44b7-9ac0-79446c9d6cb7.png)




 <br><br>
 
8. DispatcherServlet이 사용할 xml파일을 web.xml에 연동해준다.

init-param 이라는 태그를 통해 기본적으로 서블릿이 사용할 xml파일을 설정해 줄 수 있다.(기존에 설정하였던 action-servlet.xml은 무시되게 된다.)

![image](https://user-images.githubusercontent.com/84966961/131767668-af423e8d-3961-4a16-8146-40c6b8a80ab9.png)

![image](https://user-images.githubusercontent.com/84966961/131767762-f5d67e7a-38f6-440d-bddd-65aa9d598daf.png)



 
 <br><hr/>

### 2.2 web.xml 문자 처리에 대한 매핑 

1. 들어오는 url 패턴에 대한 필터링.

 `*.do`로 들어오는 url에 대한 필터링

```xml
	<filter-mapping>
		<filter-name>characterEncoding</filter-name>
		<url-parttern>*.do</url-parttern>
	</filter-mapping>
```


 <br><br>
 
2. 필터한 url에 대하여 encoding 방식 설정


파라미터를 넣어 필터링 되어 들어오는 모든 페이지들은 `UTF-8`로 인코딩되어 표시한다.

```xml
	<filter>
		<filter-name>characterEncoding</filter-name>
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
	</filter>
```

**결과 화면**

![image](https://user-images.githubusercontent.com/84966961/131768501-ee38308b-4dbb-4009-ab24-7f3b7c8a2da5.png)




 
 <br><hr/>

## 3. Spring 컨트롤러 사용 

기존 Controller 인터페이스와 DispatcherServlet을 삭제하여 Spring이 제공해주는 기능을 사용으로 변환한다.   
   
![image](https://user-images.githubusercontent.com/84966961/131769817-0ddfc904-7fdf-49aa-954e-0dc0b3dc93c5.png)

 <br><hr/>

### 3.1 Spring 컨트롤러 사용을 위한 리턴 타입 변환 : login

1. 리턴 타입을 Spring에서는 ModelAndView 클래스를 사용하여 처리를 하게된다.

![image](https://user-images.githubusercontent.com/84966961/131770927-620029f2-89b0-44cd-a14e-b56fba5b1b11.png)

<br><br>

2. 화면 네비게이션 설정에 Spring setViewName() 메소드를 이용한다.   
    
![image](https://user-images.githubusercontent.com/84966961/131771758-b060d985-50de-4880-bc42-3da0f185aed2.png)
    
 
 <br><hr/>

### 3.2 HandlerMapping 삭제 후 Spring 기능 처리

1. 기존 자작한 HandlerMapping 클래스를 삭제한다.    

![image](https://user-images.githubusercontent.com/84966961/131770986-cd94d60e-9877-48f9-a821-bad50240d38d.png)

<br><br>

2. config/presentation-layer.xml 에 우리는 Spring의 handler에 사용할 매핑들을 등록하여 url을 매핑해준다.

 - property 속성을 이용하여 setter를 사용하여 매핑을 한다.
 - 예를들어 "/login.do" 라는 값을 키로 두며 key를 밸류(값)으로 받도록 url을 매핑해준다.
 - 그 login은 `com.springbook.view.user.LoginController`을 가르키도록 bean 설정을 해준다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

	<bean class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping">
	
	</bean>
</beans>
```

 
 <br><hr/>

### 3.2 리스트 화면 전환

Spring의 ModelAndView는 정보 또한 함께 넘길 수 있다.    

```java
package com.springbook.view.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;
import com.springbook.biz.user.UserVO;

public class GetBoardListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("글 목록 검색 처리");

		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);

		// 스프링 Model And View를 사용하므로써 필요 없어짐.
//		HttpSession session = request.getSession();
//		session.setAttribute("boardList", boardList);
		
		ModelAndView mav = new ModelAndView();
		UserVO user = (UserVO) session.getAttribute("user");
		// 기존에는 리스트를 세션에 넘겼지만 mav는 내부에 저장할 수 있다.
		mav.addObject("boardList", boardList);
		if(user != null) {
			mav.setViewName("getBoardList.jsp");
		} else {
			mav.setViewName("login.jsp");
		}
		
		return mav;
	}

}
```



























