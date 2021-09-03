# Lecture12  BoardWeb3.2 & BoardWeb4
Key Word : ViewResolver, BoardWeb4, context Scan, Controller Mapping, RequestMapping,     

<hr/>

 ## 기존 프로젝트에 이어서..
 
 기존 프로젝트에서 minor 버전 업을 하여 Spring의 DispatcherServlet을 활용하여 직접 붙이는 프로젝트를 만들어본다.
 
 <br>
 
 **v3.2 update**
 
 기존에는 직접 Spring의 구조를 알아보기 위해 Controller, DispatcherServlet 등을 직접 만들었지만 3.2에서는 만들었던 모든 것을 삭제하여 Spring의 기능을 사용하여 구현하도록 한다.   
    
 [BoardWeb3.2 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0902_Lecture12/BoardWeb3.2)
 

 <br><hr/>

### 3.4 모든 컨트롤러 수정 및 빈 등록 이어서..

 ModelAndView 객체를 이용하여 이동할 페이지와 보내줘야할 데이터를 저장하여 보내도록 모든 컨트롤러를 변경해주면 정상 작동하는 것을 확인할 수 있다.   
 
 다음과 같은 방식으로 컨트롤러를 모두 수정하자.


```java
	ModelAndView mav = new ModelAndView();
	mav.setViewName("getBoardList.do");
```

![image](https://user-images.githubusercontent.com/84966961/131932052-a479ec22-5829-4fc4-9bd2-0cede4240862.png)

 
 <br><hr/>



## 4. ViewResolver
   
우연히 jsp주소를 알게되면 들어올 수도 있다. 심지어는 리스트가 보이지 않음에도 글작성이 가능하다. 이런 문제는 보안적으로 문제가 있을 수 있으므로 해결해야 할 것이다.


![image](https://user-images.githubusercontent.com/84966961/131935807-b90bcdee-dd9c-40ee-beaf-8c18494420d1.png)


 <br><hr/>


### 4.1 직접 접근 불가능하도록 폴더 이동

접근을 못하기위해 web-inf 폴더에 jsp파일들을 넣어주게 되면 직접적인 주소로 접근이 불가하게 된다.    

이 때 이런 문제를 해결하기 위한 방법으로 ViewResolver이 사용 가능하다.

![image](https://user-images.githubusercontent.com/84966961/131936045-974f9b7f-6d7a-48f7-98c2-3465c84fad58.png)

 <br><hr/>


### 4.2 ViewResolver 등록

presentation-layer.xml에 ViewResolver를 사용하기 위해 등록한다.   

![image](https://user-images.githubusercontent.com/84966961/131936858-4b573511-f589-427e-b752-730947f99074.png)   


 <br><hr/>


### 4.3 ViewResolver 문제점

1. redirect: 붙이기   

모든 이동 방식에서 /WEB-INF/board에서 찾게 되고 뒤에 .jsp 확장자를 붙이기 때문에 정상적인 페이지 이동이 불가능해진다.   

이 때 다음과 같이 `redirect:`를 앞에 붙여 ViewResolver를 통하는 것이 아닌 주소에 직접적으로 접근도록 명시해주면 문제가 해결 가능하다.   

![image](https://user-images.githubusercontent.com/84966961/131936336-51e4f3b0-2159-446b-a54c-66ceea87f8ce.png)
 
 <br>
 
**변경 후**

![image](https://user-images.githubusercontent.com/84966961/131936528-2aa9c594-6ce8-41d7-b768-2c337994a5cd.png)

 <br>
 
2. 기존 .jsp 없애기   

 기존에 vetViewName에 .jsp를 넣었던 부분을 삭제해야 확장자와 jsp 파일 이름의 분리가 된다.

![image](https://user-images.githubusercontent.com/84966961/131937309-60784f07-b314-4248-a906-538295909c01.png)


3. 결과 확인   

정상작동하게 된다.

![image](https://user-images.githubusercontent.com/84966961/131937495-da2c382e-6c54-4901-b8e0-f472184a8c70.png)   
![image](https://user-images.githubusercontent.com/84966961/131937542-8e078730-5036-49d5-a970-e78ab7e470c1.png)   
![image](https://user-images.githubusercontent.com/84966961/131937556-dbe50f4c-4a89-4b46-ad46-739af54ee394.png)   


 <br><hr/>

# BoardWeb4

 새로운 버전을 만들어 실행한다.
 
 BoardWeb4에서는 Spring 자동 빈 스캔 기능을 활용하여 예제를 만들어보고 Spring을 익혀본다.


 <br><hr/>

## 1 새로운 프로젝트 BoardWeb4 생성 

 새로운 프로젝트에서는 기존의 BoardWeb3.2에서 해온 예제를 통해 Spring의 기능들을 익혀볼 예정이므로 환경을 구성하고 기존의 파일을 복사하여 활용하도록 한다.   

 <br><hr/>

### 1.2 환경설정 구성

1. 기본적인 환경 세팅을 한다.
  - pom.xml 파일 java, spring, junit 버전 설정
  - project facet 탭 환경설정 - 자바 : 1.8 / 런타임 : 톰캣 8.5v
  - 오라클 jar 파일 삽입
  - Maven 빌드할 jar파일 설정(오라클 jar파일)
  - Maven 업데이트(프로젝트 누르고 alt + F5)   
   
2. BoardWeb3.2의 MVC를 구성하는 파일과 xml파일을 모두 복사 붙여넣기한다.


 <br><hr/>

### 1.2 패스 변경 


톰캣에서 패스가 같으면 충돌날 수 있기 때문에 다음과 같은 순서대로 패스를 바꿔준다.    

1. 서버를 더블클릭한다.
2. 모듈(Modules) 탭을 클릭한다.
3. 패스를 변경할 프로젝트를 선택한다.
4. Edit 눌러 수정창으로 들어간다.
5. path를 원하는대로 바꿔준다.
6. OK 버튼을 눌러 확정한다.
7. 서버를 재시작 후 테스트한다.     
   
![image](https://user-images.githubusercontent.com/84966961/131941189-5bae6e4f-84e5-46ef-8b28-e139a5e2f549.png)


 <br><hr/>




## 2. @annotation을 활용한 Spring 기능 활용

기존 Spring을 사용했던 기능 중 하나가 클래스에 annotaion을 지정하고 패키지를 스캔하여 자동으로 빈으로서 역할을 해주도록 하는 것이었다.


 <br><hr/>

### 2.1 presentation-layer.xml에 context scanning 활용
    
presentation-layer.xml의 기존 코드를 모두 없애고 context scanning 하도록 태그를 작성한다.   

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">

	<context:component-scan base-package="com.springbook.view"></context:component-scan>

</beans>
```


 <br><hr/>

### 2.2 Controller 파트 변경

InsertBoardController 파일에서 우리는 spring의 ModelAndView와 Controller를 사용하지 않는 방식으로 할 것이므로 상속과 override를 없애주고 리턴타입을 void로 바꿔준다.   

또한, 우리는 annotation을 사용할 것이므로 @Controller를 붙여 Spring이 이해할 수 있도록 명시적으로 표시해준다.   

기존의 Controller는 MVC 패키지의 interface였으므로 import를 빼고 이 주소(`import org.springframework.stereotype.Controller;`)를 import 해주어야한다.

모두 설정해주고 나면 Spring Bean으로서 등록이 완료되어 presentation-layer의 beans graph 탭에가서 보면 확인 가능하다.   

또한, 넘어오는 URL(\*.do) 요청에 대하여 매핑해주기 위해서 `@RequestMapping(value = "/insertBoard.do")`을 해당 메소드 위에 annotation 매핑을 해준다.

<br>

이렇게 되면 매핑이 완료되고 새 글 등록 페이지에서 테스트할 경우 페이지는 뜨지 않지만 DB에는 들어가지는 것을 확인할 수 있다.

```java
package com.springbook.view.board;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class InsertBoardController {

	@RequestMapping(value = "/insertBoard.do")
	public void handleRequest(HttpServletRequest request) {
		System.out.println("글 삽입 처리");

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BoardVO bVo = new BoardVO();
		bVo.setTitle(request.getParameter("title"));
		bVo.setWriter(request.getParameter("writer"));
		bVo.setContent(request.getParameter("content"));

		BoardDAO bDao = new BoardDAO();
		bDao.insertBoard(bVo);
		
	}

}

```

**Beans Graph**
![image](https://user-images.githubusercontent.com/84966961/131943073-2c0bb9b1-e068-4177-b4a4-fb65e082ab74.png)    


**DB 삽입 성공**
![image](https://user-images.githubusercontent.com/84966961/131945143-14ee5bdd-56fa-482f-84c2-3b1d22d137d8.png)



 <br><hr/>

## 3. 객체 생성 및 초기화 자동 처리

1. VO 객체 자동생성   

Spring은 메소드에 VO객체인 커맨드 객체를 매개변수로 지정하므로써 객체를 생성하고 request.에서 받는 param들을 자동으로 받을 수 있다.     

단, **jsp에서 보내는 param의 이름과 매개변수인 커맨드 객체 내부의 Setter 메소드 이름이 같아야 한다.**   

다음의 jsp에서 설정한 name과 VO에서 설정한 이름이 같은 정보들을 자동으로 매핑하여 객체를 준비해준다.

![image](https://user-images.githubusercontent.com/84966961/131946759-b49ce363-ca47-4110-b8fb-cb5290c8521c.png)    
   
 <br>
  
**결과 화면**   
  
![image](https://user-images.githubusercontent.com/84966961/131946921-95b7f2c7-aaeb-48c7-b15e-3d9dc0513233.png)    

![image](https://user-images.githubusercontent.com/84966961/131946983-d0c96b7a-6561-4f5f-8a8f-259227cf1d7a.png)

 <br><hr/>

2. DAO 객체 자동 생성
   
 DAO 객체 또한 Command Object로서 매개변수에 넣게 되면 자동 생성되어 사용이 가능하다.
   
 <br>
  
**결과 화면**   
  
![image](https://user-images.githubusercontent.com/84966961/131947877-b8b02b79-ee69-4455-add8-9fa21396c6f2.png)    

![image](https://user-images.githubusercontent.com/84966961/131948012-78a897f3-098e-472e-bb80-fafbbee3fdc4.png)    

 <br><hr/>


**코드와 해설**   

```java

@Controller
public class InsertBoardController {

	@RequestMapping(value = "/insertBoard.do")
	public void handleRequest(BoardVO bVo, BoardDAO bDao) {
//	public void handleRequest(command object... / 생략 : HttpServletRequest request) {
		// JSP의 Bean 태그 처럼 객체를 생성하게 된다.
		System.out.println("글 삽입 처리");

//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		BoardVO bVo = new BoardVO();
//		bVo.setTitle(request.getParameter("title"));
//		bVo.setWriter(request.getParameter("writer"));
//		bVo.setContent(request.getParameter("content"));

//------여기까지 커맨드 객체로 자동 설정된다.
		
//		BoardDAO bDao = new BoardDAO();
//------두번째 매개변수로 자동 DAO 객체 생성
		
		bDao.insertBoard(bVo);
		
	}

}

```

  
 <br><hr/>

## 4. 














   









