# Lecture05 Test13 리뷰, BoardWeb2
Key Word : Test13 리뷰, AOP (Aspect Oriented Programming), 빌드 도구(Maven, Gradle), Log4j, joinpoint, pointcut, advice, Weaving, Aspect, Advisor   

<hr/>

 ## Test13 리뷰
    
    
 공부해야할 점은 어떤 객체를 반복해서 사용해야 한다면 사용하기 전에 초기화를 해주도록 하자.    
    
<br>
    
```java
package com.springbook.biz.board;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient_master {

	public static void main(String[] args) {
		// TODO 자동 생성된 메소드 스텁
		AbstractApplicationContext factory = 
				new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService)factory.getBean("boardService");
		
		Scanner sc = new Scanner(System.in);
		int menu = 0;
		
		String title = "";
		String writer = "";
		String content = "";
		int seq = 0;
		
		BoardVO vo = null;
		
		while(true) {
			System.out.println("1.글추가 2.전체글조회 3.특정글조회 4.글정보수정 5.글삭제 6.프로그램종료");
			
			System.out.print("메뉴선택 >> ");
			
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				System.out.println("등록할 글 정보를 입력하세요.");
				System.out.print("글제목 : ");
				title = sc.nextLine();
				
				System.out.print("작성자 : ");
				writer = sc.nextLine();
				
				System.out.print("글내용 : ");
				content = sc.nextLine();
				
				vo = new BoardVO();
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setContent(content);
				
				boardService.insertBoard(vo);
				System.out.println();
				break;
			case 2:
				List<BoardVO> boardList = boardService.getBoardList(vo);
				for(BoardVO board : boardList) {
					System.out.println("--->"+board.toString());
				}
				factory.close();
				System.out.println();
				break;
				
			case 3:
				vo = new BoardVO(); // 객체를 새로 초기화해줌.
				
				System.out.print("조회 글의 번호 입력 : ");
				seq = sc.nextInt();
				vo.setSeq(seq);
				BoardVO board = boardService.getBoard(vo);
				System.out.println("--->"+board.toString());
				System.out.println();
				
				break;
			
			case 4:
				System.out.print("수정할 글의 번호 입력 : ");
				seq = sc.nextInt();
				sc.nextLine();
				
				System.out.print("글제목 : ");
				title = sc.nextLine();
				
				System.out.print("글내용 : ");
				content = sc.nextLine();
				
				vo = new BoardVO();
				vo.setSeq(seq);
				vo.setTitle(title);
				vo.setContent(content);
				
				boardService.updateBoard(vo);
				List<BoardVO> boardListUpdate = boardService.getBoardList(vo);
				for(BoardVO boardUpdate : boardListUpdate) {
					System.out.println("--->"+boardUpdate.toString());
				}
				
				System.out.println();
				break;
				
			case 5:
				System.out.println("삭제 글의 번호 입력");
				seq = sc.nextInt();
				
				vo = new BoardVO();
				vo.setSeq(seq);
				
				boardService.deleteBoard(vo);
				List<BoardVO> boardListDelete = boardService.getBoardList(vo);
				for(BoardVO boardDelete : boardListDelete) {
					System.out.println("--->"+boardDelete.toString());
				}
				System.out.println();
				break;
			}	
			if(menu == 6) {
				System.out.println("6번 선택");
				System.out.println();
				System.out.println();
				System.out.println("프로그램종료");
				break;
			}
		}
	
	}

}
```

<br><br><hr>

 ## 1. AOP
 
 
  - OOP (Object Oriented Programming)
 
  - AOP (Aspect Oriented Programming)    
     - 공통 관심사에 대해서 함께 관리하기 위한 것. 마치 css파일을 수정하면 모든 css 파일을 사용하는 곳에서 바뀜.
 
 
 
 
<br><br><hr>

 ## 2. 공통 관심사 가지기(Aspect)
 
<br>

 ### 2.1 CRUD 기능 사용시 공통으로 LogAdvice라는 공통 관심사 가지기(Aspect)
 
  BoardServiceImpl.java에서 만약 CRUD 기능을 사용하면 다음 코드를 실행시킨다. Service에서 생성자 생성시 하나의 객체를 만들어 두고 log.print()라는 메소드를 호출하여 다다음 코드처럼 CRUD 기능에 넣어주도록 한다.
 
 ```java
 public class LogAdvice {
	public void printLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	}
}
```
```java
@Service("boardService")
public class BoardServiceImpl implements BoardService {

	// DAO를 사용하기 위한 객체 생성
	@Autowired
	private BoardDAO boardDAO;
	private LogAdvice log;
	
	public BoardServiceImpl() {
		// 현재는 우리가 직접 객체 생성 but @autowired 넣어주면 자동주입도 가능함.
		// CRUD 기능이 발생할 때마다 공통으로 사용하고 싶음.
		log = new LogAdvice();
	}

	@Override
	public void insertBoard(BoardVO bVo) {
		log.printLog();         // 모든 기능이 실행될때 로그가 실행됨.
		boardDAO.insertBoard(bVo);
	}

	@Override
	public void updateBoard(BoardVO bVo) {
		log.printLog();
		boardDAO.updateBoard(bVo);
		
	}

	@Override
	public void deleteBoard(BoardVO bVo) {
		log.printLog();
		boardDAO.deleteBoard(bVo);
		
	}

	@Override
	public BoardVO getBoard(BoardVO bVo) {
		log.printLog();
		return boardDAO.getBoard(bVo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO bVo) {
		log.printLog();
		return boardDAO.getBoardList(bVo);
	}

}
```
 
 **결과 화면**    
 ![image](https://user-images.githubusercontent.com/84966961/130552478-4dc54e6b-1c8e-4d65-8d70-3947e940d2ce.png)    

 
  
 
<br><br><hr>

 ### 2.2 Log 기능 확장 - 단순 기능 변경
 
  Log를 위한 새로운 라이브러리를 사용한다. common 패키지에 Log4jAdvice 클래스를 만들어보자.    
 
 ```java
 public class Log4jAdvice {
	public void printLogging() {
		System.out.println("[공통 로그-Log4j] 비즈니스 로직 수행 전 동작");
	}
}
```

 <br><br>

 새로운 기능을 추가할 때 만약 이름과 메소드가 모두 다르다면 지금의 시스템에서는 모든 메소드의 내부 내용을 바꿔주어야만 할 것이다. 다음은 log.printLog를 log.pringLogging으로 모두 다시 바꿔준 코드이다.   

```java
package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.Log4jAdvice;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	// DAO를 사용하기 위한 객체 생성
	@Autowired
	private BoardDAO boardDAO;
	
	//private LogAdvice log; 새로운 로그로 교체
	private Log4jAdvice log;
	
	public BoardServiceImpl() {
		// 현재는 우리가 직접 객체 생성 but @autowired 넣어주면 자동주입도 가능함.
		// CRUD 기능이 발생할 때마다 공통으로 사용하고 싶음.
//		log = new LogAdvice();
		
		// 새로운 로그시스템을 사용하고 싶어서 교체함
		log = new Log4jAdvice();
		// 이럴 경우 아래 메소드를 모두 바꿔야함
	}

	@Override
	public void insertBoard(BoardVO bVo) {
		// 새로운 로그시스템으로 전부 바꿔줘야함.
//		log.printLog();
		log.printLogging();
		
		boardDAO.insertBoard(bVo);
	}

	@Override
	public void updateBoard(BoardVO bVo) {
//		log.printLog();
		log.printLogging();
		
		boardDAO.updateBoard(bVo);
		
	}

	@Override
	public void deleteBoard(BoardVO bVo) {
//		log.printLog();
		log.printLogging();
		
		boardDAO.deleteBoard(bVo);
		
	}

	@Override
	public BoardVO getBoard(BoardVO bVo) {
//		log.printLog();
		log.printLogging();
		
		return boardDAO.getBoard(bVo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO bVo) {
//		log.printLog();
		log.printLogging();
		
		return boardDAO.getBoardList(bVo);
	}

}
```

<br><br>

 이렇게 기능이 바뀔 때마다 모든 작업들을 변경해주어야 한다. 이런 애로사항을 없애기 위해 우리는 공통 관심사를 두고 Aspect로서 관리하는 것이다.
  
 
<br><br><hr>

 ### 2.3 Log 기능 확장 - 공통 관심사 적용
 
 1. Maven과 Gradle - 빌드 도구 : 중앙 서버에서 필요한 파일을 배포받기 위한 용도.   
   - pom.xml : jar 파일을 받기 위하여 필요한 정보들을 모아놓은 곳.   
   - org.aspectj : AOP를 구현하기 위한 라이브러리   

```xml   
		<dependency>
			<groupId>org.aspectj</groupId>
			<artifactId>aspectjrt</artifactId>
			<version>${org.aspectj-version}</version>
		</dependency>	
```

  - org.aspectjweaver : 추가   
![image](https://user-images.githubusercontent.com/84966961/130560106-0337f912-aecd-42c8-a3fb-b1dfa521441f.png)    
    

<br><br>

2. Maven Repository 사용
  
  - 홈페이지 진입 :  https://mvnrepository.com/    
      
  - 원하는 라이브러리 검색   
     
![image](https://user-images.githubusercontent.com/84966961/130560566-73674bd8-7b5e-4d99-aebd-ba8fd8b82d10.png)   

<br>

  - 원하는 버전 클릭    
     
![image](https://user-images.githubusercontent.com/84966961/130560628-3459460c-9f19-4eb7-aa63-5dd35857faae.png)   

<br>

  - pom.xml 양식 복사 후 추가.   
     
![image](https://user-images.githubusercontent.com/84966961/130560673-dcb85cb3-c7f8-4ec9-adda-9f379932c679.png)    

<br>

  - 추가된 weaver    
     
![image](https://user-images.githubusercontent.com/84966961/130560762-785a29e7-e3c5-4817-a53a-33f38e5d0cbc.png)    
  


<br><br>

3. Spring에서의 공통 관심사 구성   
     
  - Spring에서 공통 관심사를 적용할 수 있는 대상은 **메소드만 가능**하다.
  


 
<br><br><hr>

 ### 2.3 Log 기능 확장 - 공통 관심사 적용
 
 1. applicationContext.xml 에 aop 기능 추가    
  ![image](https://user-images.githubusercontent.com/84966961/130561333-229f5087-40f5-4cb9-9d2f-0ffb43895de7.png)    
![image](https://user-images.githubusercontent.com/84966961/130561371-1a0722a9-2c74-45d2-b403-15b552566691.png)    


 2. 사용하고 싶은 bean 추가  및 aop 등록  
```xml
	<!-- aop 등록을 위해 필요한 빈을 만들어 준다. -->
	<bean id="Log" class="com.springbook.biz.common.Log4jAdvice">	
    
	<!-- aop 등록 -->
	<aop:config>
		<!-- 모든 패키지 biz 내부에 들어있는 Impl로 끝나는 모든 파일들에 대해서 적용 -->
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.*(..))" id="allPointcut"/>
		
		<!-- aspect log 레퍼런스(Log4jAdvice)에게 공통관심사 등록 -->
		<aop:aspect ref="log">
			<!-- pointcut에 정해준 영역 안에서 printLogging이 시작되기 전(before)에 발생 -->
			<aop:before method="printLogging" pointcut-ref="allPointcut"/>
		</aop:aspect>
	</aop:config>
```

 
<br><br><hr>

 ### 2.4 AOP를 위한 용어 정리
     
 **조인포인트** : 공통 관심사를 적용할 수 있는 후보들(대상이 될 수 있는 메소드들)   
   
 **포인트컷** : 실질적으로 공통 관심사를 적용 받는 대상 메소드들    
   
 **어드바이스** : 공통 관심사에 해당하는 기능의 코드를 의미(printLogging)    
  - 어드바이스의 동작 시점
    - before : pointcut의 동작 전
    - after : pointcut의 동작 후(예외 발생 여부에 상관없이 무조건 수행하는 어드바이스)
    - after-returning : pointcut이 성공적으로 동작하고 리턴되면 실행
    - after-throwing : pointcut이 예외가 발생하면 실행
    - around : pointcut의 동작 전/후
   
 **위빙(Weaving)** : 포인트컷의 대상 메소드에 어드바이스의 메소드가 삽입되는 것.    
  - Weaver (직공) - 실로 면을 짜듯 메소드를 모아서 내보냄.
   
 **애스팩트(Aspect) or 어드바이저(Advisor)**     
  - Pointcut + Advice    
   ![image](https://user-images.githubusercontent.com/84966961/130575376-0e5105fd-7775-45b4-ac0f-a2ec0f35cb4d.png)     


<br><br><hr>

#### 2.4.1 포인트컷의 활용

 allPoingcut 에서 getPointcut으로 바꾸면 get메소드에 한정적으로 관심사를 조정할 수 있다.    
     
 조인포인트 중에서 포인트컷을 줄여서 사용할 수 있다.

```
	<!-- aop 등록 -->
	<aop:config>

		<!-- 모든 패키지 biz 내부에 들어있는 Impl로 끝나는 모든 파일들에 대해서 적용 -->
		<!-- 반환타입(*) 패키지주소.*로끝나는.모든 메소드 -->
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.*(..))" id="allPointcut" />
		<!-- 반환타입(*) 패키지주소.*로끝나는.get으로 끝나는 모든 메소드 -->
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.get*(..))" id="getPointcut"/>

		<!-- aspect log 레퍼런스(Log4jAdvice)에게 공통관심사 등록 / 구현 객체 지정 -->
		<aop:aspect ref="log">
			<!-- pointcut에 정해준 영역 안에서 printLogging이 시작되기 전(before)에 발생 -->
			<!-- all에서 get으로 변경해주니 get 메소드만 사전 로깅이 됨.-->
			<aop:before method="printLogging" pointcut-ref="getPointcut" />
		</aop:aspect>

	</aop:config>
```

<br><br>

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/130567596-964b1c30-de6a-4833-ad57-4e735a421140.png)    
   

<br><br><hr>

#### 2.4.2 aop:pointcut expression 표현식

```
execution(* com.springbook.biz..*Impl.get*(..))
```

 위의 표현식에 대한 정리를 해보면 다음과 같다.    
   
**포인트컷의 표현식**   
|execution(|*|com.springbook.biz..|*Impl.|get*|(..)|
|-|-|-|-|-|-|
|  | 리턴타입 | 패키지명 | 클래스명 | 메소드명 | 매개변수 |

<br><br>

**리턴타입**
<br> - \* : 모든 리턴타입<br> - void<br> - !void

**패키지명**
<br> - com.springbook.biz : 해당패키지만 대상<br> - com.springbook.biz.. : 해당 패키지로 시작하는 모든 패키지<br> - com.springbook.biz..Impl : 해당 패키지로 시작하면서 마지막 패키지 이름이 Impl로 끝나는 패키지

**클래스명**
<br>- BoardServiceImpl : 해당클래스만 대상<br> - \*Impl : Impl로 끝나는 클래스<br> - BoardService+ : 해당 클래스로부터 파생되는 자식 클래스 대상(상속 표현)

**메소드명**
<br>- \*(..) : 모든 메소드<br> - get*(..) : get으로 시작하는 모든 메소드

**매개변수**
<br>- (..) : 개수, 타입 제약없음<br> - (\*) : 반드시 1개의 매개변수를 가지는 메소드<br> - (com.springbook.user.UserVO) : UserVO 타입의 매개변수<br> - (!com.springbook.user.UserVO) : UserVO 타입의 매개변수가 아닌 것<br> - (Integer, ..) : 한 개 이상의 매개변수를 가지고 첫번째 매개변수만 지정(Integer)<br> - (Integer, \*) : 반드시 두 개의 매개변수를 가진 메소드




<br><br><hr>

#### 2.4.3 어드바이스의 동작 시점

  - 어드바이스의 동작 시점
    - before : pointcut의 동작 전
    - after : pointcut의 동작 후(예외 발생 여부에 상관없이 무조건 수행하는 어드바이스)
    - after-returning : pointcut이 성공적으로 동작하고 리턴되면 실행
    - after-throwing : pointcut이 예외가 발생하면 실행
    - around : pointcut의 동작 전/후
   
<br>
    
 동작시점을 알아보기 위해 전(before)에는 allPointcut을 후(after)에는 getPointcut을 적용하였다.

```XML
	<!-- aop 등록 -->
	<aop:config>

		<!-- 모든 패키지 biz 내부에 들어있는 Impl로 끝나는 모든 파일들에 대해서 적용 -->
		<!-- 반환타입(*) 패키지주소.*로끝나는.모든 메소드 -->
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.*(..))" id="allPointcut" />
		<!-- 반환타입(*) 패키지주소.*로끝나는.get으로 끝나는 모든 메소드 -->
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.get*(..))" id="getPointcut"/>

		<!-- aspect log 레퍼런스(Log4jAdvice)에게 공통관심사 등록 / 구현 객체 지정 -->
		<aop:aspect ref="log">
			<aop:before method="printLogging" pointcut-ref="allPointcut" />
			<!-- pointcut에 정해준 영역 안에서 printLogging이 시작되기 전(before)에 발생 -->
			<!-- all에서 get으로 변경해주니 get 메소드만 사후 로깅이 됨.-->
			<aop:after method="printLoggingAfter" pointcut-ref="getPointcut" />
		</aop:aspect>

	</aop:config>
```

**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/130570822-44b870c1-f03c-4761-b57e-8d2c17744222.png)     



<br><br><hr>

#### 2.4.4 BeforeAdvice 설정

 새로운 bean 등록.

```xml
	<bean id="before" class="com.springbook.biz.common.BeforeAdvice"></bean>
	
	<!-- aop 등록 -->
	<aop:config>
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.*(..))" id="allPointcut" />

		<aop:aspect ref="before">
			<aop:before method="beforeLog" pointcut-ref="allPointcut" />
		</aop:aspect>

	</aop:config>
```




<br><br><hr>



