# Lecture20 BoardWeb5.1

Key Word : BoardWeb5.1, BoardWeb5.2, 마이바티스, JDBC 이용 방법 2가지         

<hr/>

## BoardWeb5.1 생성

 마이바티스를 사용하여 DB를 사용해보는 예제를 다루어 보자.   
 
 새로운 개념을 다루기 위해 BoardWeb5 프로젝트 생성 및 세팅을 하자.    

   
 [BoardWeb5.1 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0923_Lecture20/BoardWeb5.1)
 

<br><hr>

## Mybatis 특징

 1. 한줄의 코드로 DB연동 처리
 2. **SQL 명령어를 자바 코드에서 분리하여 XML 파일로 따로 관리** - 시각적으로 가장 큰 특징
 3. SQL의 실 실행결과를 VO객체에 자동 매핑
 4. 컴파일이 필요없다

<br><hr>

## Mybatis with spring 복습 이후 이어서..

2가지 방법
1. SQL DAO SQLSESSION 을 구현(IMPLEMENT)하여 사용.
2. 마이바티스 SESSION TEMPLATE를 활용하여 직접 생성자를 생성하도록 하여 JDBC 사용.

보통 두 번째 방법을 사용하여 JDBC를 사용한다.






























