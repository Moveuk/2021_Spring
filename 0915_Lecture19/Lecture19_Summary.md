# Lecture19 BoardWeb5

Key Word : BoardWeb5, 마이바티스, 환경 구성, 기본 세팅, CRUD 기능      

<hr/>

## BoardWeb5 생성

 마이바티스를 사용하여 DB를 사용해보는 예제를 다루어 보자.   
 
 새로운 개념을 다루기 위해 BoardWeb5 프로젝트 생성 및 세팅을 하자.    

   
 [BoardWeb5 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0915_Lecture19/BoardWeb5)
 

<br><hr>

## Mybatis 특징

 1. 한줄의 코드로 DB연동 처리
 2. **SQL 명령어를 자바 코드에서 분리하여 XML 파일로 따로 관리** - 시각적으로 가장 큰 특징
 3. SQL의 실 실행결과를 VO객체에 자동 매핑
 4. 컴파일이 필요없다

<br><hr>

## Mybatis 환경 구성

### Mybatis Dependency 추가

1. Maven repository에서 Mybatis 검색

![image](https://user-images.githubusercontent.com/84966961/133352256-8d4f60cc-fc82-4d1b-bc0c-1d66cfe7ec29.png)   

2. 3.3.1 버전 사용

![image](https://user-images.githubusercontent.com/84966961/133352273-948855cf-023a-4145-832f-4250c9be78b6.png)   

3. 추가 및 배포 확인

![image](https://user-images.githubusercontent.com/84966961/133352356-cd87eade-dd93-4665-96da-5879fdbeaa80.png)   

![image](https://user-images.githubusercontent.com/84966961/133352448-1fdd0b60-df59-4043-ab66-9baacd7cc79b.png)   



<br><hr>

### Ibatis Core Dependency 추가

![image](https://user-images.githubusercontent.com/84966961/133352702-96e5582a-ee7d-4dcf-a6d3-13dcc398077e.png)   

![image](https://user-images.githubusercontent.com/84966961/133352741-bd697b66-5475-49bc-9d16-253ea7319379.png)   

![image](https://user-images.githubusercontent.com/84966961/133352761-c8a21998-1fbb-48fd-aff0-a974714d0b73.png)   




<br><hr>

### 기존 BoardVO 복사

```java
public class BoardVO {
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	private String searchCondition;
	private String searchKeyword;
	private MultipartFile uploadFile;
```


<br><hr>

## 마이바티스 기본 세팅

### 마이바티스에 사용할 sql 문을 저장하는 xml파일 생성

 board-mapping.xml에 쿼리문을 저장하여 사용하면 된다.
 
 xml 파일 이름과 namespace에 들어가는 이름이 고정되어있는 것은 아니다. 유동적으로 원하는 이름으로 바꿔주면된다.

![image](https://user-images.githubusercontent.com/84966961/133354768-923c6e4f-456a-40cc-85ca-e91532a23d69.png)


<br><hr>

### 태그를 통한 쿼리문 작성

 - insert, update, delete, select를 태그를 통해 사용할 수 있다.
 - #{ }을 통해 값을 매칭시켜준다.
   - 어디에도 #{}을 매핑시켜주는 코드는 존재하지 않는다. 마이바티스 내부에서 해준다.
 - 단 select 태그는 결과값(ResultSet)이 return되므로 resultType을 설정해준다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- 마이바티스에 사용할 db sql 문을 저장하는데 사용하는 xml 파일이다. -->

<mapper namespace="BoardDAO">

	<insert id="insertBoard">
		insert into board1(seq,title,writer,content)
		values((select nvl(max(seq), 0)+1 from board1),#{title},#{writer},#{content})
	</insert>

	<update id="updateBoard">
		update board1 set title=#{title}, content=#{content} where seq=#{seq}
	</update>
	
	<delete id="deleteBoard">
		delete board1 where seq=#{seq}
	</delete>
	
	<select id="getBoard" resultType="board">
		select * from board where seq = #{seq}
	</select>
	
	<select id="getBoardList" resultType="board">
		select * from board1 
		where title like '%'||#{searchKeyword}||'%'
		order by seq desc
	</select>
	
</mapper>

```


<br><hr>

### db 연동을 위한 정보가 들어있는 properties 파일 작성    

![image](https://user-images.githubusercontent.com/84966961/133359714-3fa10ff0-7691-4c1e-ba7f-a804671a6c63.png)


<br><hr>

### 작성한 태그 매핑 xml 작성

resources 외부에 `sql-map-config.xml`을 작성

![image](https://user-images.githubusercontent.com/84966961/133358008-6aa720ab-d775-4d26-addf-aa3bc2ffc42e.png)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
	<!-- 루트에서 위치를 찾고 db에 대한 정보를 받아오는 태그 -->
	<properties resource="database.properties"></properties>

	<!-- 써도되고 안써도되는 태그 -->
	<typeAliases>
		<!-- select를 리턴 받기 위한 타입 -->
		<!-- board-mapping.xml의 board에 매핑함 -->
		<typeAlias alias="board" type="com.springbook.biz.board.BoardVO" />
	</typeAliases>

	<!-- db 정보 정리 -->
	<environments default="development">
		<environment id="development">
			<!-- 기존 커밋과 롤백방식으로 트랜잭션을 하겠다는 태그 -->
			<transactionManager type="JDBC"></transactionManager>
			<!-- 마이바티스는 JDBC 풀을 기본으로 제공해줌. -->
			<!-- 따라서 commons-dbcp dependency를 붙일 필요가 없음. -->
			<dataSource type="POOLED">
				<!-- name은 마이바티스에서는 driver, value는 프로퍼티에 작성한 값 -->
				<property name="driver" value="${jdbc.driverClassName}" />
				<property name="url" value="${jdbc.url}" />
				<property name="username" value="${jdbc.username}" />
				<property name="password" value="${jdbc.password}" />
			</dataSource>
		</environment>
	</environments>

	<!-- 이 db에 sql문 양식을 매핑시켜야함. -->
	<mappers>
		<mapper resource="mapping/board-mapping.xml"/>
	</mappers>
</configuration>
```



<br><hr>

## CRUD 기능 구성

### BoardDAO 작성

`com.springbook.biz.board.impl` 패키지에 BoardDAO를 생성한다.

"BoardDAO.insertBoard" 가 board-mapping.xml 에 작성된 것을 매핑한 것이다.

![image](https://user-images.githubusercontent.com/84966961/133364881-8c23bd6e-6a62-4e28-9542-c517eb006bd6.png)

![image](https://user-images.githubusercontent.com/84966961/133364984-df264444-c927-444b-ba17-6318247fde4b.png)

```java
@Repository("boardDAO")
public class BoardDAO {
	private SqlSession mybatis;

	public BoardDAO() {
		mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
	}

	public void insertBoard(BoardVO vo) {
		// board-mapping.xml에서 찾아서 알아서 매핑해준다.
		// vo에 #{}에 대한 값을 알아서 get 메소드로 자동 매핑 해준다.
		mybatis.insert("BoardDAO.insertBoard", vo);
		// 자동 커밋이므로 상관없지만 속성 설정을 이용해서 설정할 수 있다.
		mybatis.commit();
	}
	
	public void updateBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.updateBoard", vo);
		mybatis.commit();
	}
	
	public void deleteBoard(BoardVO vo) {
		mybatis.insert("BoardDAO.deleteBoard", vo);
		mybatis.commit();
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return mybatis.selectOne("BoardDAO.getBoard",vo);
	}
	
	public List<BoardVO> getboardList(BoardVO vo) {
		return mybatis.selectList("BoardDAO.getBoardList",vo);
	}
	
}
```


<br><hr>

### BoardDAO 테스트 코드 작성

```java
public class BoardServiceClient {
	public static void main(String[] args) {

		BoardDAO boardDAO = new BoardDAO();
		BoardVO vo = new BoardVO();
		
		vo.setTitle("myBatis제목2");
		vo.setWriter("홍길동2");
		vo.setContent("myBatis 내용입니다2....");
		
		boardDAO.insertBoard(vo);

		System.out.println("삽입 성공");
		
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardDAO.getboardList(vo);
		
		for(BoardVO board : boardList) {
			System.out.println(board.toString());
		}
		
		System.out.println("리스트 출력 성공");
	}
}
```

**데이터 정상 입력**     
![image](https://user-images.githubusercontent.com/84966961/133374180-9d0f14f3-67d7-4fb0-b0dc-23aa4fe729fe.png)     

**리스트 정상 출력**     
![image](https://user-images.githubusercontent.com/84966961/133374023-95f3ac6c-3cf1-415c-a629-d5f696d4dd0d.png)   



<br><hr>

### 테스트 프로그램 작성

 일전에 시험봤었던 것처럼 프로그램을 만들어 테스트를 해보았다.

```
public class BoardServiceClient {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BoardDAO boardDAO = new BoardDAO();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		BoardVO bVo = null;
		int num = 0;

		while (true) {
			List<BoardVO> boardList = null;
			System.out.println();
			System.out.println("1.글추가 2.전체글조회 3.특정글조회 4.글정보수정 5.글삭제 6.프로그램종료");
			System.out.print("메뉴 선택 >> ");
			num = Integer.parseInt(br.readLine());

			switch (num) {
			case 1:
				bVo = new BoardVO();
				System.out.println("등록할 글 정보를 입력하세요.");
				System.out.print("글제목 : ");
				bVo.setTitle(br.readLine());
				System.out.print("작성자 : ");
				bVo.setWriter(br.readLine());
				System.out.print("글내용 : ");
				bVo.setContent(br.readLine());
				boardDAO.insertBoard(bVo);
				break;

			case 2:
				bVo = new BoardVO();
				bVo.setSearchCondition("TITLE");
				bVo.setSearchKeyword("");
				boardList = boardDAO.getBoardList(bVo);

				for (BoardVO board : boardList) {
					System.out.println(board.toString());
				}
				break;

			case 3:
				bVo = new BoardVO();
				System.out.print("조회 글 번호 입력 : ");
				bVo.setSeq(Integer.parseInt(br.readLine()));
				BoardVO boardVO = boardDAO.getBoard(bVo);
				System.out.print("--->" + boardVO.toString());
				System.out.print("\n");
				break;

			case 4:
				bVo = new BoardVO();
				System.out.print("수정 글 번호 입력 : ");
				bVo.setSeq(Integer.parseInt(br.readLine()));
				System.out.print("글제목 : ");
				bVo.setTitle(br.readLine());
				System.out.print("글내용 : ");
				bVo.setContent(br.readLine());
				boardDAO.updateBoard(bVo);

				bVo.setSearchCondition("TITLE");
				bVo.setSearchKeyword("");
				boardList = boardDAO.getBoardList(bVo);
				for (BoardVO boardVO2 : boardList) {
					System.out.print("--->" + boardVO2.toString() + "\n");
				}
				break;

			case 5:
				bVo = new BoardVO();
				System.out.print("삭제 글 번호 입력 : ");
				bVo.setSeq(Integer.parseInt(br.readLine()));
				boardDAO.deleteBoard(bVo);
				
				bVo.setSearchCondition("TITLE");
				bVo.setSearchKeyword("");
				boardList = (ArrayList<BoardVO>) boardDAO.getBoardList(bVo);
				for (BoardVO boardVO3 : boardList) {
					System.out.print("--->" + boardVO3.toString() + "\n");
				}
				break;

			case 6:
				System.out.print("6번 선택\n\n");
				System.out.print("프로그램종료");
				return;

			default:
				System.out.println("숫자 1~6 사이값만 입력해주세요.");
				break;
			}
		}
	}
}
```

**정상 작동**    
![image](https://user-images.githubusercontent.com/84966961/133377636-ba6ffb9d-ed89-463a-a7d7-30b9479c9cac.png)














<br><hr>
