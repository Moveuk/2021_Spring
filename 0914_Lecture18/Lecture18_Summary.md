# Lecture18 BoardWeb4.3

Key Word : BoardWeb4.3, 예외 처리, @ControllerAdvice, @ExceptionHandler, 다국어 처리, messageSource_en.properties       

<hr/>

## BoardWeb4.3 이어서..

 User 파트를 스프링의 JDBCTemplate를 활용한 방식으로 변환하여 작동하도록 한다.

   
 [BoardWeb4.3 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0909_Lecture17/BoardWeb4.3)
 
 <br><hr/>

##  User 파트 Spring으로 전환하기 


### 사용자 배려 예외처리 


 만약 로그인 하는 도중 아이디를 미처 입력하지 않고 로그인을 시도했다면 예외를 던지도록 login controller에서 만들 수 있다.


![image](https://user-images.githubusercontent.com/84966961/133175363-fca4c021-0f92-4dc6-9b25-50ad9232ab27.png)    


 다만 이렇게, 서버에서 사소한 예외 처리를 하는 것은 서버에 부담을 줄 수 있기 때문에 사용자 브라우저에서 할 수 있는 체크인지 알아보고 자원을 효율적으로 사용하여야 할 것이다.   

 다음과 같이 빈 아이디값을 넣으면 사용자 브라우저상에서 오류가 뜨게된다.   


![image](https://user-images.githubusercontent.com/84966961/133175500-71f53ce2-e8da-4d1f-97e7-1f4ae8b9c84f.png)    


Spring 에서는 이런 예외처리를 하기 위해서 


 <br><hr/>

### 예외처리 클래스를 만들어 사용하기

1. presentation=layer.xml 에 클래스를 이용한 예외처리를 하기 위해 mvc 어노테이션 기능을 키고 태그를 넣어준다.

![image](https://user-images.githubusercontent.com/84966961/133175569-9c59615e-db55-4e85-ae03-1321b77d06d7.png)

![image](https://user-images.githubusercontent.com/84966961/133175643-ac177f42-a70c-478a-87e4-2d285cdb3b7a.png)


 <br><hr/>

2. 이 후 예외처리를 핸들할 핸들러 클래스를 하나 만들어준다.

 AOP 의 개념처럼 정해준 포인트 컷("com.springbook.view")에서 `@ExceptionHandler` 어노테이션에 지정한 익셉션(객체)이 들어오거나 발생하게 된다면 해당 메소드를 발생시켜 mav를 통해 페이지를 이동시키는 방식이다.


![image](https://user-images.githubusercontent.com/84966961/133175925-bdfcffa1-45ba-48a9-b0c0-bef4c2a8ddab.png)    

 ```java
 @ControllerAdvice("com.springbook.view")
// advice	: 관점지향 프로그래밍에서 공통관심사를 의미
//				어떤 객체가 필요하면 공통으로 사용하게 됨.
// pointcut : 공통관심사를 사용할 대상
//				"com.springbook.view"라는 이 클래스가 사용될 빈들을 지정해준다.
public class CommonExceptionHandler {

	// 단순히 advice설정만 한다고 되지 않기 때문에
	// 어떤 요청이 들어왔을 때(아마도 위의 포인트컷에서 특정 객체가 생성된다면 발생) 할 것인지
	// 정하고 동작하도록 해주는 어노테이션을 설정해야한다.
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handlerArithmeticException(Exception e) {
		// 메소드 이름은 아무렇게나 해도됨.

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e); // 예외에 대한 정보.
		mav.setViewName("/common/artihmeticError.jsp"); // 예외시 이동할 페이지

		return mav;
	}
<<<<<<< HEAD

	// 예외 연습 1
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handlerNullPointerException(Exception e) {
		// 메소드 이름 변경 가능
=======

	// 예외 연습 1
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handlerNullPointerException(Exception e) {
		// 메소드 이름 변경 가능

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e); // 예외에 대한 정보.
		mav.setViewName("/common/nullPointerError.jsp"); // 예외시 이동할 페이지

		return mav;
	}

	// 예외 연습 2
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e); // 예외에 대한 정보.
		mav.setViewName("/common/error.jsp"); // 예외시 이동할 페이지

		return mav;
	}

}
```


 <br><hr/>


### 예외처리 뷰페이지 작성

사용자에게 보여줄 페이지를 작성한다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 에러 페이지라는 표시 -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100" % border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" bgcolor="orange">
				<b>기본 에러 화면입니다.</b>
			</td>
		</tr>
	</table>
	<br>
	<table width="100" % border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" bgcolor="orange">
				Message : ${exception.message }
			</td>
		</tr>
	</table>
</body>
</html>
```

**브라우저 화면**     
![image](https://user-images.githubusercontent.com/84966961/133181483-eb020b69-2d6b-4ea4-8816-bc1a2a07dc8b.png)    


 <br><hr/>


## 다국어 처리
>>>>>>> fa14d91abdd3f0acef207034f6cf71f89c0a0509

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e); // 예외에 대한 정보.
		mav.setViewName("/common/nullPointerError.jsp"); // 예외시 이동할 페이지

<<<<<<< HEAD
		return mav;
	}

	// 예외 연습 2
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception e) {

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e); // 예외에 대한 정보.
		mav.setViewName("/common/error.jsp"); // 예외시 이동할 페이지
=======
### 다국어 페이지 속성 값이 저장된 messageSource_en.properties

다음 사진처럼 properties 파일을 이용하여 resource/message 폴더 내부에 `messageSource_en.properties` 파일을 만들어 저장해준다.

![image](https://user-images.githubusercontent.com/84966961/133181309-3189dcc8-fd21-49a6-94a2-f1d2f44cf048.png)    
>>>>>>> fa14d91abdd3f0acef207034f6cf71f89c0a0509

		return mav;
	}

<<<<<<< HEAD
}
```
=======
### 다국어 페이지 속성 값이 저장된 messageSource_ko.properties

한국어는 자동으로 유니코드로 등록되기 때문에 미리 txt에 작성한 다음 properties 파일로 옴겨주도록하자    

![image](https://user-images.githubusercontent.com/84966961/133183502-5974014c-4167-47c8-9937-cae839e1e53b.png)    




 <br><hr/>


### presetation-layer.xml 수정

메세지에 대해 스프링이 인지할 수 있도록 매핑해준다.

![image](https://user-images.githubusercontent.com/84966961/133188964-34a6ff28-e13d-4518-85ea-ac1849141dda.png)





 <br><hr/>


### login 뷰페이지 내용 수정

login 뷰페이지에 기존 한글로 작성했던 부분을 spring이 properties 파일의 값을 받아와서 자동으로 작성되도록 변경했다.    

이 후 한글과 영어로 변경할 수 있도록 버튼을 배치했다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.user.login.title"></spring:message></title>
</head>
<body>
	<center>
		<h1><spring:message code="message.user.login.title"></spring:message></h1>
		
		<a href="login.do?lang=en">
			<spring:message code="message.user.login.language.en" />
		</a>
		&nbsp;&nbsp;
		<a href="login.do?lang=ko">
			<spring:message code="message.user.login.language.ko" />
		</a>
		<hr>
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.id" /></td>
					<td><input type="text" name="id" value="${userVO.id }"/></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.password" /></td>
					<td><input type="password" name="password" value="${userVO.password }" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="<spring:message code="message.user.login.loginBtn" />" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
```


**브라우저 화면**     

![image](https://user-images.githubusercontent.com/84966961/133197304-054b412f-9189-48d5-93fa-702bd2b324ba.png)

>>>>>>> fa14d91abdd3f0acef207034f6cf71f89c0a0509


 <br><hr/>


<<<<<<< HEAD
### 예외처리 뷰페이지 작성

사용자에게 보여줄 페이지를 작성한다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 에러 페이지라는 표시 -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="100" % border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" bgcolor="orange">
				<b>기본 에러 화면입니다.</b>
			</td>
		</tr>
	</table>
	<br>
	<table width="100" % border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td align="center" bgcolor="orange">
				Message : ${exception.message }
			</td>
		</tr>
	</table>
</body>
</html>
```

**브라우저 화면**     
![image](https://user-images.githubusercontent.com/84966961/133181483-eb020b69-2d6b-4ea4-8816-bc1a2a07dc8b.png)    


 <br><hr/>


## 다국어 처리


### 다국어 페이지 속성 값이 저장된 messageSource_en.properties

다음 사진처럼 properties 파일을 이용하여 resource/message 폴더 내부에 `messageSource_en.properties` 파일을 만들어 저장해준다.

![image](https://user-images.githubusercontent.com/84966961/133181309-3189dcc8-fd21-49a6-94a2-f1d2f44cf048.png)    


### 다국어 페이지 속성 값이 저장된 messageSource_ko.properties

한국어는 자동으로 유니코드로 등록되기 때문에 미리 txt에 작성한 다음 properties 파일로 옴겨주도록하자    

![image](https://user-images.githubusercontent.com/84966961/133183502-5974014c-4167-47c8-9937-cae839e1e53b.png)    




 <br><hr/>


### presetation-layer.xml 수정

메세지에 대해 스프링이 인지할 수 있도록 매핑해준다.

![image](https://user-images.githubusercontent.com/84966961/133188964-34a6ff28-e13d-4518-85ea-ac1849141dda.png)





 <br><hr/>


### login 뷰페이지 내용 수정

login 뷰페이지에 기존 한글로 작성했던 부분을 spring이 properties 파일의 값을 받아와서 자동으로 작성되도록 변경했다.    

이 후 한글과 영어로 변경할 수 있도록 버튼을 배치했다.

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="message.user.login.title"></spring:message></title>
</head>
<body>
	<center>
		<h1><spring:message code="message.user.login.title"></spring:message></h1>
		
		<a href="login.do?lang=en">
			<spring:message code="message.user.login.language.en" />
		</a>
		&nbsp;&nbsp;
		<a href="login.do?lang=ko">
			<spring:message code="message.user.login.language.ko" />
		</a>
		<hr>
		<form action="login.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0">
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.id" /></td>
					<td><input type="text" name="id" value="${userVO.id }"/></td>
				</tr>
				<tr>
					<td bgcolor="orange"><spring:message code="message.user.login.password" /></td>
					<td><input type="password" name="password" value="${userVO.password }" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="<spring:message code="message.user.login.loginBtn" />" /></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>
```


**브라우저 화면**     

![image](https://user-images.githubusercontent.com/84966961/133197304-054b412f-9189-48d5-93fa-702bd2b324ba.png)



 <br><hr/>


=======
>>>>>>> fa14d91abdd3f0acef207034f6cf71f89c0a0509
### list 뷰페이지 수정

```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.springbook.biz.board.BoardVO"%>
<%@page import="com.springbook.biz.board.impl.BoardDAO"%>
<%@page import="java.util.*" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1><spring:message code="message.board.list.mainTitle"/></h1>
		<h3>
			${userName }<spring:message code="message.board.list.welcomeMsg"/><a href="logout.do">Log-out</a>
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
					<input name="searchKeyword" type="text" /> <input type="submit" value="<spring:message code="message.board.search.condition.btn"/>" /></td>
				</tr>
			</table>
		</form>
		
		<table border=1 cellpadding=0 cellspacing=0 width=700>
			<tr>
				<th bgcolor="orange" widht=100><spring:message code="message.board.list.table.head.seq"/></th>
				<th bgcolor="orange" widht=100><spring:message code="message.board.list.table.head.title"/></th>
				<th bgcolor="orange" widht=100><spring:message code="message.board.list.table.head.writer"/></th>
				<th bgcolor="orange" widht=100><spring:message code="message.board.list.table.head.regDate"/></th>
				<th bgcolor="orange" widht=100><spring:message code="message.board.list.table.head.cnt"/></th>
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
		<a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
	</center>
</body>
</html>
```




**브라우저 화면**       

<<<<<<< HEAD
![image](https://user-images.githubusercontent.com/84966961/133351232-139a553d-1a82-4185-a87f-a0d391a2ad6d.png)
=======
>>>>>>> fa14d91abdd3f0acef207034f6cf71f89c0a0509









 <br><hr/>


