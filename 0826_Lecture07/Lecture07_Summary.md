# Lecture07 BoardWeb2 Spring JDBC
Key Word : Spring JDBC 환경 구성    

<hr/>

 ## 기존 프로젝트에 이어서..
 
 [BoardWeb2 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0824_Lecture05/BoardWeb2)
 
 
 <br><hr/>
 
## 1. Spring JDBC 환경 구성

### 1.1 Maven Repository 에서 필요한 라이브러리 찾아 pom.xml에 의존성 명시.
  - Spring-jdbc    
  - common-dbcp : Spring의 DI에 의해서 자동으로 pool까지 받아진다.    
![image](https://user-images.githubusercontent.com/84966961/130881860-0de051a1-1afe-48c7-a5ce-d183b3e6db81.png)    
 

<br><br>

### 1.2 외부 라이브러리는 @annotation으로 **빈 등록이 불가**하므로 applicationContext.xml에 빈을 직접 등록해주어야 한다.

```xml
	<!-- dbcp 라이브러리 : destroy-method(사용 후 자동으로 close메소드) -->
	<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
		<!-- Setter method 등록 : 오라클 드라이버 등록 / JSP Context.xml 파일에 저장한 드라이버 세팅과 똑같다.-->
		<property name="driverClassName" value="oracle.jdbc.driver.OracleDriver"></property>
		<property name="url" value="jdbc:oracle:thin:@localhost:1521:XE"></property>
		<property name="username" value="scott"></property>
		<property name="password" value="TIGER"></property>
		<property name="maxActive" value="8"></property>
		<property name="maxIdle" value="8"></property>
	</bean>
```
 

<br><br>

### 1.3 Spring jdbc 정보 분리 및 framework화
  -database.properties 작성 : JDBC에 필요한 주요 속성들을 정리해놓기 위함.
    - 작성 위치    
![image](https://user-images.githubusercontent.com/84966961/130883878-839fa219-8d2a-4981-875f-61d8ea036979.png)     
    - applicationContext.xml 파일 기입
![image](https://user-images.githubusercontent.com/84966961/130884617-134917d6-e036-4e5b-ae3c-6a032a198456.png)


<br><br>

### 1.4 JdbcTemplate 라이브러리 사용 : 기존 JDBCUtil(db 연결) CRUD 기능
    - JdbcTemplate에는 JDBC를 위한 편의성 메소드가 있음.
    - update(...) : insert, update, delete
    - queryForInt(...) : select
    - queryForObject(...) : select - 하나의 객체(Object) 결과 값이 나올 때 사용하는 메소드이다.(객체 그대로 반환)
    - query(...) : select - 


<br><br>

### 1.5 라이브러리 추가

```xml
<!-- Pstmt & DAO 기능 : jdbcTemplate 라이브러리 - Connection을 이용해서 CRUD 실행 -->
	<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
		<property name="dataSource" ref="dataSource"></property>
	</bean>
```

 <br><br>
<hr>
 
 
## 2. Spring JDBC 구현

<br><br>

### 2.1 사용을 위한 BoardDAOSpring 클래스 생성
  - 사용할 sql문을 상수 변수로 정의함.
![image](https://user-images.githubusercontent.com/84966961/130890206-1b815a10-f044-48ad-a0a4-3797219d99e0.png)   

<br>

  - insertBoard() 메소드 구현
    - jdbcTemplate.update는 매개변수를 **..args 가변인자로서 받아 sql문의 와일드카드(?)를 처리**한다.
```java
	public void insertBoard(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, bVo.getTitle(), bVo.getWriter(), bVo.getContent());
	}
```

<br>

  - getBoardList() 메소드 구현
      - 쿼리를 통해 받은 rs 객체를 BoardRowMapper라는 클래스를 만들어 Spring이 사용할 수 있도록 프레임화 해준다.
      - BoardRowMapper객체를 직접 만들지 않고 AutoWired를 사용해 Spring이 생성하도록 해주었다.
```java
	public List<BoardVO> getBoardList(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
	
		// BOARD_LIST가 되고난 데이터를 BoardRowMapper로 보내 정리하여 리턴함
		return jdbcTemplate.query(BOARD_LIST, BoardRowMapper);
		// 현재 List를 만들고 add하는 객체가 없으나 
		// query메소드가 내부적으로 List형태로 받아 리턴해주고 있음.
	}
```


<br>

  - updateBoard() 메소드 구현
    - 2번 게시물을 수정해주었다.
![image](https://user-images.githubusercontent.com/84966961/130896124-7bc9d662-2d66-4aec-a4f4-04dc083d769f.png)


<br>

  - deleteBoard() 메소드 구현
    - 28번 게시물을 삭제해주었다.
![image](https://user-images.githubusercontent.com/84966961/130896272-501fd3e9-f7fd-41c3-b59b-15091e244d3f.png)
![image](https://user-images.githubusercontent.com/84966961/130896300-c373e06d-04d2-4e02-851e-fd128a4065f5.png)



<br>

  - getBoard() 메소드 구현
    - Object 배열을 만들어 sql문 와일드 카드를 넣어주도록 한다.
```java
	public BoardVO getBoard(BoardVO bVo) {
		System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
		System.out.println("==>> Spring JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
	}
```

   
<br>
 

<br><br> <hr>

