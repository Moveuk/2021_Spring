# Lecture17  BoardWeb4.3

Key Word : BoardWeb4.3, JDBC Templete, UserDAO, 스프링방식 변환, 검색기능, like 와일드 카드, 파일 업로드 기능,   

<hr/>

## BoardWeb4.3

 User 파트를 스프링의 JDBCTemplate를 활용한 방식으로 변환하여 작동하도록 한다.

   
 [BoardWeb4.3 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0909_Lecture17/BoardWeb4.3)
 
 <br><hr/>

##  User 파트 Spring으로 전환하기

### BoardWeb4.1 vs BoardWeb 4.3 차이점 설명

1. JDBC 와 Weaver dependency 추가    
   
![image](https://user-images.githubusercontent.com/84966961/132604467-6fd5554a-d171-47ba-bd77-bcd852cfb801.png)   
   
**pom.xml**
   
![image](https://user-images.githubusercontent.com/84966961/132604677-328c66c4-ebd7-49ac-93e3-2254b9c9c23f.png)   


   
2. web에서 applicationContext.xml 붙여줌.

![image](https://user-images.githubusercontent.com/84966961/132604756-b631caeb-58d8-48ce-b89a-268d78c0e536.png)


   
3. 서비스에서 다른 DAO를 사용하기 위해서 자동 주입되는 객체 변경

![image](https://user-images.githubusercontent.com/84966961/132606501-2ebad405-8d80-49c0-a6ee-1fd17a606da0.png)


4. 자동 주입되는 객체(UserDAOSpring) 생성

```java
@Repository
public class UserDAOSpring {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String USER_GET = "select * from users where id =? and password= ?";
	
	//READ
	
	public UserVO getUser(UserVO vo) {
		System.out.println("===> Spring JDBC로 getUser() 기능 처리");
		Object[] args = { vo.getId(), vo.getPassword() };
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}

}
```

5. loginController에서 서비스를 사용하려면 필요하므로 자동주입     
   
```java
@Controller
public class LoginController {

	@Autowired
	private UserService userService;   // UserServiceImpl
	
	@RequestMapping(value= "/login.do", method=RequestMethod.GET)
	public String loginView(/* @ModelAttribute("user") */UserVO vo) {
		
		vo.setId("admin");
		vo.setPassword("1234");
		return "login.jsp";
	}

	@RequestMapping(value= "/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO,HttpSession session) {
		
		UserVO user = userService.getUser(vo);
		if (user != null) {
			
			session.setAttribute("userName", user.getName());
			
			return "getBoardList.do";
		}
		else
			return "login.jsp";
	}

}
```
 
![image](https://user-images.githubusercontent.com/84966961/132605503-18c6ed33-7865-4e74-a3b5-d1503b2a28bb.png)   

 <br><hr/>


### 검색기능 추가

기존 검색 기능을 구현하기 위해서 boardController에서  @ModelAttribute를 사용하여 만들었었다.      

```java
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
					<input name="searchKeyword" type="text" /> <input type="submit" value="검색" /></td>
				</tr>
			</table>
		</form>
```

<br>

그런데 우리는 지금 Spring으로 바꾸게 되면서 getBoardList를 같이 공유하고 있는데 문제가 발생하게 된다.    

```java
	// 검색 기능 구현 추가 버전
	@RequestMapping("/getBoardList.do")
	public String getBoardList(@RequestParam(value = "searchCondition",defaultValue = "TITLE", required = false)String condition,@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String Keyword, BoardVO bVo, BoardDAO boardDAO, Model model) {
		System.out.println("GetBoardListController 실행");
		System.out.println("검색 조건 : " + condition);
		System.out.println("검색 단어 : " + Keyword);
		
		model.addAttribute("boardList", boardDAO.getBoardList(bVo));
		
		return "getBoardList.jsp";
	}
```



 <br><hr/>


#### dto에 검색 컬럼값 추가

Spring에서는 dto 내부의 값을 jsp로부터 자동으로 읽어들여 Model에 저장하여 사용하므로 추가하여 검색기능 구현에 원활하도록 돕는다.

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
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
```


 <br><hr/>


#### getBoardList.do 에서 검색어 null 체크

```java
	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO vo, Model model) {
		// 검색조건이 없을시 기본값은 제목
		if(vo.getSearchCondition() == null) vo.setSearchCondition("TITLE");
		// 검색내용이 없을시 기본값은 빈값
		if(vo.getSearchKeyword() == null) vo.setSearchKeyword("");
		
		
		model.addAttribute("boardList", boardService.getBoardList(vo)); // Model 정보 저장
		return "getBoardList.jsp";  //View 이름 리턴
	}
```

 <br><hr/>


#### like 와일드 카드를 사용한 쿼리

![image](https://user-images.githubusercontent.com/84966961/132608528-35fb278e-d158-453d-869b-77f614b1ee5c.png)




 <br><hr/>


#### BoardDAOSpring.java

 Controller에서 빈값으로 content를 검색하게 되면 전체 데이터가 조회됨.

```java
@Repository
public class BoardDAOSpring {

	@Autowired
	private JdbcTemplate jdbcTemplate;

중략...

	// 안써도 되도록 할 예정
//	private final String BOARD_LIST = "select * from board1 order by seq desc";
	// "" 공백으로 검색하면 모든 값 다나옴
	private final String BOARD_LIST_T = "select * from board1 WHERE title like '%'||?||'%' order by seq desc";
	private final String BOARD_LIST_C = "select * from board1 WHERE content like '%'||?||'%' order by seq desc";
  
중략...

	public List<BoardVO> getBoardList(BoardVO vo) {
		System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		if (vo.getSearchCondition().equals("TITLE"))
			// 공백이면 모든 값 검색
			list = jdbcTemplate.query(BOARD_LIST_T, new BoardRowMapper(), vo.getSearchKeyword());
		else if (vo.getSearchCondition().equals("CONTENT"))
			list = jdbcTemplate.query(BOARD_LIST_C, new BoardRowMapper(), vo.getSearchKeyword());
		return list;
	}

...
```



 <br><hr/>


#### String의 null과 ""의 차이점

 null과 ""의 차이점은 쉽게 말해 메모리에 넣었는지 안넣었는지이다.   
 
 즉, null 은 메모리에 올리지 않은 상태, 초기화를 하지 않은 상태를 말한다. 하지만 ""으로 String을 초기화하게 되면 그 객체는 메모리에 올라가게 되며 실제 객체가 존재하게 되는 것이므로 빈값으로 사용되게 된다. 


 <br><hr/>


### 파일등록 추가

#### VO 객체 MultipartFile uploadFile 필드 추가

 VO가 생성될 때 MultipartFile 객체가 presentation-layer.xml에 등록한 resolver에 의해 자동으로 생성되어 초기화된다.  
 
 즉, insertBoard() 메소드를 사용할 때 자동으로 MultipartFile가 만들어져 있는 VO를 사용하여 파일 업로드를 처리한다.    
   
![image](https://user-images.githubusercontent.com/84966961/132613157-72ffbb7f-4f79-4152-b9b7-5eee76f3aed9.png)


#### 라이브러리 의존성 추가

1. Maven repository site    
![image](https://user-images.githubusercontent.com/84966961/132613414-38122618-2579-4622-b9c5-0f8d7677632e.png)   

<br>

2. fileupload 검색 > 아파치 커먼즈 파일 업로드 선택
![image](https://user-images.githubusercontent.com/84966961/132613466-b53c4fb0-c1a5-45b9-972a-3fe7259454ed.png)   

<br>

3. 1.3.1 버전 사용
![image](https://user-images.githubusercontent.com/84966961/132613488-2fd039ce-55f8-4b6d-9076-0b948b58b5df.png)   

<br>

4. 의존성 주입
![image](https://user-images.githubusercontent.com/84966961/132613575-d9e7b526-1300-4e41-a965-07710ff0d4d6.png)   


 <br><hr/>

5. 외부라이브러리 이므로 presentation-layer.xml 에 bean 등록

```java
<!-- 스프링 컨테이너에서 BoardVO의 MultipartFile를 생성하여 사용가능하도록 해주는 태그 -->
<!-- resolver와 같이 특정 클래스는 스프링에서 지정한 id를 사용하여야지만 자동 주입이 가능하다. -->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
	
  </bean>
```

 <br><hr/>


6. BoardInsert() 메소드에 파일 추가 코드 작성

```java
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO vo) throws IllegalStateException, IOException {
		System.out.println("글 등록 처리");
		
		MultipartFile uploadFile = vo.getUploadFile();
		
		// 넣어야 할 파일이 존재하면 실행.
		if(!uploadFile.isEmpty()) {
			// 실제 업로드한 파일의 이름
			String fileName = uploadFile.getOriginalFilename();
			// 원하는 폴더로 전송.
			uploadFile.transferTo(new File("c:/lib/workspace/2021_Spring/0909_Lecture17/BoardWeb4.3/uploadFileLocation" + fileName));
		}
		
		boardService.insertBoard(vo);
		
		return "redirect:getBoardList.do";
	}
```

 <br><hr/>


7. 최대 업로드 파일 사이즈 수정가능

 업로드 가능한 최대 파일 크기를 설정해놓으면 값이 작은 파일들만 받을 수 있다. 

```java
<!-- 스프링 컨테이너에서 BoardVO의 MultipartFile를 생성하여 사용가능하도록 해주는 태그 -->
<!-- resolver와 같이 특정 클래스는 스프링에서 지정한 id를 사용하여야지만 자동 주입이 가능하다. -->
	<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		<property name="maxUploadSize" value="10"></property>
</bean>
```

![image](https://user-images.githubusercontent.com/84966961/132619311-7ca048d9-ce21-4a3e-b6d4-e964dfdd0ccf.png)

 <br><hr/>


### 사용자 배려 예외처리 





















 <br><hr/>



