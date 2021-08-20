# Lecture03 Spring 기본
Key Word : @annotation, @Autowired, @qualifier, @Resource, @Service, @Repository, @Controller, Maven 업데이트, 배포할 jar파일 추가, 의존성 주입

<hr/>

 ## 2. 스프링의 기본 - 이어서..
    
 <br>
    
 ### @annotation을 이용하여 클래스 매핑

 먼저, applicationContext.xml

![image](https://user-images.githubusercontent.com/84966961/130163193-6eb9d7fa-2d79-4d3f-966e-a425428e2416.png)



![image](https://user-images.githubusercontent.com/84966961/130162867-6df08012-09ab-48fe-9d68-620eb0102397.png)


![image](https://user-images.githubusercontent.com/84966961/130162911-7d69958e-c38b-44a4-a10d-29861524a1c3.png)

클래스에 @Component로 스캔을 위한 지시

![image](https://user-images.githubusercontent.com/84966961/130162961-c4bd47c5-de35-4bf2-8442-e8c3c12e3774.png)

@Component를 사용하기 위해서는 import가 필요하며 서블릿 uri 패턴처럼 `( "id" )`를 사용하여 이 클래스의 id값을 설정해줄 수 있다.

다만, 이 상태에서는 Setter를 이용한 주입같은 것처럼 계층적인 관계를 설명할 수 없다.   
   
이 문제를 해결하기 위해 `자동 의존 주입` 대해서 알아보자.


<br><br><hr>

### 자동 의존 주입 : @Autowired

LgTV의 private한 필드인 speaker에 @AutoWired를 걸어주면 Spring이 알아서 관련 타입의 클래스들을 찾아서 매핑해준다.   
   
![image](https://user-images.githubusercontent.com/84966961/130163394-c89ae9c4-609c-4b63-aff3-4768f7959004.png)   
   
 하지만 이 떄 스피커가 apple, sony 두개일 때(물론 두 스피커 모두에 @Component()를 넣어주었다.)는 어떤 스피커인지 선택할 수 없어 오류가 생기게 된다. 다음과 같이 사용 가능한 스피커가 2개라는 오류가 뜨게 된다.

<br>

![image](https://user-images.githubusercontent.com/84966961/130163583-fae37c90-7780-4f8e-9e80-f506c1f0a28c.png)
   
<br>

그렇기 때문에 주입에 사용할 클래스를 선택하여 한정짓기 위해서 Autowired를 사용할 때는 다음과 같이 Qualifier를 함께 사용하여야 한다. import를 할 때는 org.springframework.beans.factory.annotation.Qualifier를 하도록 한다.
<br>

![image](https://user-images.githubusercontent.com/84966961/130163762-cf0ae959-6168-4028-a872-279730a6281e.png)    
     
<br>
    
 정상 작동하는 LgTV를 볼 수 있다.
<br>
  
![image](https://user-images.githubusercontent.com/84966961/130163925-2d6b92c2-eda6-4032-9a9f-ebced64acdd7.png)
   
<br>

이를 대체할 방식으로 @Resource(name="apple")로 대체하여 사용할 수 있다.   
<br>

![image](https://user-images.githubusercontent.com/84966961/130164135-c6ba7105-3b55-4618-a9df-882d682328b4.png)

<br><br><hr>

### @Autowired와 직접 주입 방식의 혼용

이번에는 Sony와 Apple의 @Component를 제거하여 Spring에 등록하지 못하도록 만들고 LgTV의 Speaker 필드에는 @Autowired만 남겨보도록 하자.   
   
![image](https://user-images.githubusercontent.com/84966961/130164448-4eed0116-5499-42cd-948f-ae24bfd653e2.png)   
   
그리고 applicationContext.xml 파일에 하나의 Speaker bean만 작성하여 실행시킬 수도 있다.   

![image](https://user-images.githubusercontent.com/84966961/130164657-2d4e7d80-61bf-4c1a-afe2-bb5a05d966a3.png)   
   
 단, 이 방법의 경우 sony 혹은 apple만 사용 가능하므로 단일 빈을 사용할 때만 사용해야 한다.

<br><br>

**실행 결과**    

![image](https://user-images.githubusercontent.com/84966961/130164749-d897bfb4-500f-4865-be39-601731bb39af.png)   
   

<br><br><hr>

### @Component를 대체할 @annotation 방식 : @Service, @Repository, @Controller
   
 - @Service
 - @Repository
 - @Controller

이 세가지가 붙어있는 클래스들은 Spring이 자동으로 스캔하여 등록을 해준다. 주로 DB 관련 클래스들을 다룰 때 사용한다.   
   

<br><br><hr>

## 3. 비즈니스 컴포넌트

 - VO
 - DAO
 - Service
 - ServiceImpl

위의 4가지는 접미사로 비즈니스 로직을 사용하는 클래스를 작성할 때 사용하게 된다. 예를들어 'xxxxVO, xxxxDAO, xxxxService, xxxxServiceImpl' 등으로 사용되게 된다.   

<br><br><hr>

### 3.1 새로운 프로젝트 생성 및 환경 설정 : BoardWeb1_6

 1. Project Facet 설정 변경, pom.xml 버전 변경   

 2. 오라클 라이브러리 배포

![image](https://user-images.githubusercontent.com/84966961/130168802-6628b849-24c4-48c5-a1c3-79bda5083e3f.png)

 3. 빌드 후 배포시 jar 파일 포함하여 배포하도록 Deployment Assembly 설정
   
![image](https://user-images.githubusercontent.com/84966961/130168829-bcd0833c-8237-4614-8f5b-68f8608e978d.png)   
   
![image](https://user-images.githubusercontent.com/84966961/130168889-0b818a5a-efca-4928-a282-3b7594f9da88.png)   
   
![image](https://user-images.githubusercontent.com/84966961/130168926-e45b9d33-96f7-419c-a38f-05df8b4b00a9.png)   
   
![image](https://user-images.githubusercontent.com/84966961/130168960-7bd90d01-2ca4-42ee-8a2f-5d0a4916be51.png)   
   
![image](https://user-images.githubusercontent.com/84966961/130168978-3ebcc7ca-a783-414b-91ed-73fcd5a8c0ed.png)   


<br><br><hr>

### 3.2 sql developer : db 생성

```sql
create table board2(
    seq number(5) primary key,
    title varchar2(200),
    writer varchar2(20),
    content varchar2(2000),
    regdate date default sysdate,
    cnt number(5) default 0
);
```

<br><br><hr>

### 3.3 VO 클래스 생성

db와 동일하게 생성해준 후 Setter, Getter와 ToString을 Override 해주도록 한다.   
   
![image](https://user-images.githubusercontent.com/84966961/130170557-2d856ee3-0584-49ee-8a92-423f27885fc6.png)


<br><br><hr>

### 3.4 JDBCUtil 클래스 생성
   
db연결에 필요한 connection 생성, close 메소드를 만들어 준다.    
   
```java
public class JDBCUtil {

	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "scott", "TIGER");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(PreparedStatement pstmt, Connection conn) {
		try {
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

}
```



<br><br><hr>

### 3.5 BoardDAO 클래스 생성
   
 Spring을 이용하여 비즈니스 컴포넌트를 구성할 것이므로 다음과 같이 `@Repository` annotation을 붙여준다.   
   
![image](https://user-images.githubusercontent.com/84966961/130184289-d641a3b2-6932-43b4-bd6c-63b4a325f529.png)


<br><br><hr>

### 3.6 BoardDAO 메소드 사용을 위한 Service 인터페이스
   
![image](https://user-images.githubusercontent.com/84966961/130173941-9621be4d-e729-43b7-b9d3-77b4b81d1682.png)


<br><br><hr>

### 3.7 Service를 구체화한 DAO 사용 클래스 : BoardServiceImpl
   
DAO를 사용하려면 객체를 생성해야 하는데 이 과정은 Spring이 해줄 것이므로 객체를 생성하도록 `@Autowired`로 명시해준다.    
   
![image](https://user-images.githubusercontent.com/84966961/130174547-07c72cde-0f71-4efb-a05e-612f3268a585.png)


<br><br><hr>

### 3.8 자동 의존 주입 : 빈들을 등록하기 위한 ApplicationContext.xml 파일 생성
   
![image](https://user-images.githubusercontent.com/84966961/130175104-9146ff82-b177-454d-a8fa-eb8c31835b18.png)


<br><br><hr>

### 3.9 insertBoard 메소드 구성
   
 - insertBoard 처리시 컬럽 6개 중 2개는 디폴트 값으로 들어간다.
 - seq 값은 기존의 글 갯수 + 1의 값이 들어간다.

```java
	// 글등록
	public void insertBoard(BoardVO bVo) {
		System.out.println("insertBoard() 기능 처리");
		String sql = "INSERT INTO board2(seq,title,writer,content)"
				+ " VALUES ((SELECT COUNT(*) FROM board2)+1,?,?,?)";
		JDBCUtil ut = new JDBCUtil();
		
		try {
			conn = ut.getConnection();
			pstmt = conn.prepareStatement(sql);
			// 테스트용 작동함.
//			pstmt.setString(1, "제목");
//			pstmt.setString(2, "글쓴이");
//			pstmt.setString(3, "내용");
			pstmt.setString(1, bVo.getTitle());
			pstmt.setString(2, bVo.getWriter());
			pstmt.setString(3, bVo.getContent());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ut.close(pstmt, conn);
		}
	}
```

<br><br><hr>

#### Sql문의 오류

아래 sql문을 비교해보면 count 함수를 사용할 때 문제점이 보인다.    
중간에 데이터가 없어지면 max값과 count값이 달라져서 중복되어 PK에 대한 오류가 발생될 수 있다.   
그렇기 때문에 nvl 함수로 null을 체크하고 max(seq)으로 최대값을 찾아내며 만약에 null일 경우 0+1로 넣는다.   
   
```java
		String sql = "INSERT INTO board2(seq,title,writer,content) VALUES ((SELECT COUNT(*) FROM board2)+1,?,?,?)";

		String sql = "INSERT INTO board2(seq,title,writer,content) VALUES ((SELECT nvl(max(seq), 0)+1 FROM board2),?,?,?)";
```


<br><br><hr>

### 3.10 테스트를 위한 Maven Update

1. Maven을 업데이트 하여 Test 폴더를 활용한다.   
![image](https://user-images.githubusercontent.com/84966961/130182760-b90b0fca-541c-4825-a7eb-282170ae2421.png)   
   
2. 테스트용 Main 클래스를 만든 후 AbstractApplicationContext factory와 BoardVO를 초기화하고 insertBoard(bVo)를 테스트한다.   
   
![image](https://user-images.githubusercontent.com/84966961/130184378-277ca120-6c45-4c9f-b982-b17d7770a247.png)   
   
```java
// 테스트용 클래스
public class BoardServiceTest {

	public static void main(String[] args) {
		System.out.println("Test 작동");
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService) factory.getBean("boardService");
		
		BoardVO bVo = new BoardVO();
		bVo.setTitle("첫번째글");
		bVo.setWriter("고길동");
		bVo.setContent("첫번째 글 작성 입니다.");
		
		boardService.insertBoard(bVo);
		factory.close();
	}

}
```

**결과 화면과 SQL DB 확인**   
![image](https://user-images.githubusercontent.com/84966961/130183859-1f84066e-b4d2-4603-9650-0e9b65897139.png)    
    
![image](https://user-images.githubusercontent.com/84966961/130183885-edf81633-17bf-45d9-bf5e-6fe9378309d7.png)    
    

<br><br><hr>

#### 입력한 값으로 넣을 수 있도록 업그레이드하기.

BufferReadered로 값을 받아 넣어주었다.

```java
package com.springbook.mybiz.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

// 테스트용 클래스
public class BoardServiceTest {

	public static void main(String[] args) throws IOException {
		System.out.println("Test 작동");
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		BoardService boardService = (BoardService) factory.getBean("boardService");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		BoardVO bVo = new BoardVO();
		System.out.println("제목을 입력해 주세요.");
		bVo.setTitle(br.readLine());
		System.out.println("글쓴이를 입력해 주세요.");
		bVo.setWriter(br.readLine());
		System.out.println("내용을 입력해 주세요.");
		bVo.setContent(br.readLine());
		
		if (bVo.getTitle().isBlank()) bVo.setTitle("빈값이라 새 제목");
		if (bVo.getWriter().isBlank()) bVo.setWriter("미상");
		if (bVo.getContent().isBlank()) bVo.setContent("빈 내용");
		
		boardService.insertBoard(bVo);
		factory.close();
	}

}
```
   
**결과 화면**   
![image](https://user-images.githubusercontent.com/84966961/130187816-336bfd4a-327f-4b66-a868-c0e6532f7422.png)   
   

<br><br><hr>

### 3.11 총정리 복습 : UserWeb 만들기 



[UserWeb](https://github.com/Moveuk/2021_Spring/blob/main/0819_Lecture03/UserWeb)



<br><br><hr>
