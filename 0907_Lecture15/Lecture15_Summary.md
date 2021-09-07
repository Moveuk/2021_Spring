# Lecture15  BoardWeb4.1

Key Word : BoardWeb4.1 - Controller 합치기, 검색 기능, @ModelAttribute, @SessionAttribute    

<hr/>

## BoardWeb4.1 

 새로운 버전을 만들어 실행한다.
 
 BoardWeb4.1 에서는 Spring에서 컨트롤러를 모두 합쳐 사용하는 방식을 연습해본다.        
 
 기존의 모든 환경은 그대로 복사했으며 컨트롤러 패키지 내부의 ViewResolver 같은 클래스들은 모두 삭제되었다.


   
 [BoardWeb4.1 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0907_Lecture15/BoardWeb4.1)
 
 <br><hr/>


### Controller 합치기

컨트롤러를 합쳐 사용할 수가 있다.    


```JAVA
@Controller
public class BoardController {

	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO bVo,BoardDAO boardDAO){ //view에 대한 처리만 필요.
		System.out.println("updateBoardController 실행");
		boardDAO.updateBoard(bVo);
		
		return"getBoardList.do";
	}
	
	@RequestMapping(value="/insertBoard.do")
	public String insertBoard(BoardVO bVo, BoardDAO boardDAO){
		
		System.out.println("run InsertBoardControoler");
		
		boardDAO.insertBoard(bVo);
		
		return "redirect:getBoardList.do";
		
	}
	
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardVO bVo,BoardDAO boardDAO,ModelAndView mav){
		System.out.println("run getBoardListController");
		
		List<BoardVO> boardList = boardDAO.getBoardList(bVo);
		
		mav.addObject("boardList",boardList);
		
		mav.setViewName("getBoardList.jsp");
		
		return mav;
	}
	
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO bVo, BoardDAO boardDAO,ModelAndView mav) {
		System.out.println("run GetBoardController");
		
		BoardVO board = boardDAO.getBoard(bVo);
		
		mav.addObject("board",board);
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}

	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO bVo , BoardDAO boardDAO){
		System.out.println("run DeleteBoardController");
		
		boardDAO.deleteBoard(bVo);
		
		
		return "redirect:getBoardList.do";
	}
}

```

**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/132268942-83151fac-1dd7-46d9-8cbd-fedbcee24220.png)


<br><hr>

### 필요없는 컨트롤러 삭제

Controller를 합쳐 사용하게 되면 다음의 모든 컨트롤러는 필요없게 된다.   

![image](https://user-images.githubusercontent.com/84966961/132268886-d37cd571-15c1-4c1d-8a66-0c6edd916a9f.png)


<br><hr>


### 다른 메소드 방식을 하나의 RequestMapping으로 묶기

기존에는 jsp 뷰페이지로 이동하기 위해서는 jsp로 이동하거나 LoginFormController와 같은 컨트롤러를 따로 만들어 이동하게 만들었었다. 하지만 다음 코드와 같은 구성을 하게 된다면 스프링에서는 단순 뷰페이지 이동과 기능 컨트롤러를 get과 post 방식ㄷ에 따라.합쳐 놓을 수 있다. 

```java
@Controller
public class LoginController {

	// 단순 페이지 이동을 위한 매핑 기능을 할 수도 있음.
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String loginView(UserVO uVo, UserDAO userDAO) {
		System.out.println("get method를 이용한 로그인 처리");

		uVo.setId("admin");
		uVo.setPassword("1234");
		
		
		return "login.jsp";

	}
	
	
	// 로그인을 위한 post 처리 가능.
	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public String login(UserVO uVo, UserDAO userDAO, HttpSession session) {
		System.out.println("로그인 처리");

		UserVO user = userDAO.getUser(uVo);

		if(user!=null) {
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}

	}

}
```


<br><hr>

#### 다양한 속성 값들.

1. Spring에서 어노테이션에 매개변수를 사용하게 된다면 다음과 같이 속성명을 함께 입력해주어야 한다.

```java
@RequestMapping(value = "/login.do", method=RequestMethod.POST)
```
   
<br><br>
   
2. jsp 뷰페이지로 보내고자 하는 매개변수의 이름을(EL식에서 사용할 이름) 정하고 싶다면 다음과 같이 속성을 붙여주면된다.

```java
	@RequestMapping(value = "/login.do", method=RequestMethod.GET)
	public String loginView(@RequestMapping("user")UserVO uVo, UserDAO userDAO) {
 ```

<br><br>

3. 세션 기능 넣기

```java
	// 로그인을 위한 post 처리 가능.
	@RequestMapping(value = "/login.do", method=RequestMethod.POST)
	public String login(UserVO uVo, UserDAO userDAO, HttpSession session) {
		System.out.println("로그인 처리");

		UserVO user = userDAO.getUser(uVo);

		if(userDAO.getUser(uVo)!=null) {
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}

	}
```



<br><hr>

### 모델 객체를 사용하여 뷰 분리하기

 기존에는 ModelAndVIew를 사용하여 모델과 뷰를 함께 사용하였지만 이번에는 Model이라는 SpringFramework의 interface를 이용하여 모델과 뷰를 분리해보았다. 기존의 코드 보다 간단해진 코드를 확인할 수 있다.

   
**뷰 분리 전**
```java
	// 모델 객체를 사용하여 뷰 분리하기
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO bVo, BoardDAO boardDAO,ModelAndView mav) {
		System.out.println("GetBoardController 실행");
		
		mav.addObject("board",boardDAO.getBoard(bVo));
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}
	
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardList(BoardVO bVo,BoardDAO boardDAO,ModelAndView mav){
		System.out.println("GetBoardListController 실행");
		
		List<BoardVO> boardList = boardDAO.getBoardList(bVo);
		mav.addObject("boardList",boardList);
		mav.setViewName("getBoardList.jsp");
		
		return mav;
	}
```

**뷰 분리 후**

```java
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO bVo, BoardDAO boardDAO, Model model) {
		System.out.println("GetBoardController 실행");

		model.addAttribute("board", boardDAO.getBoard(bVo));

		return "getBoard.jsp";
	}

	@RequestMapping("/getBoardList.do")
	public String getBoardList(BoardVO bVo, BoardDAO boardDAO, Model model) {
		System.out.println("GetBoardListController 실행");

		model.addAttribute("boardList", boardDAO.getBoardList(bVo));
		
		return "getBoardList.jsp";
	}
```


<br><hr>

### 검색 기능 구현

주요 spring @annontation
 1. @ModelAttribute
    - command 객체 이름 지정
    - View(jsp)에서 사용할 데이터를 설정
 2. @SessionAttributes
    - HttpSession에 값을 저장
    - null 업데이트 방지


<br>

#### 검색 타입 설정하기
   
![image](https://user-images.githubusercontent.com/84966961/132275671-0c561172-aaf9-40e0-a4ba-cda9a56ec0b8.png)


 위의 사진처럼 jsp로 부터 검색할 때 제목으로 검색할지 내용으로 검색할지에 대하여 설정할 수 있어야 한다. 그러기 위한 select 값을 컨트롤러 메소드의 매개변수로 다음과 같이 속성을 정의하여 받을 수 있다.


```
@RequestParam(value = "searchCondition",defaultValue = "TITLE", required = false)String condition
@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String Keyword
```

-> 해석 : searchCondition(jsp의 name 값)이라는 Param을 받아서 만약 값이 없다면 TITLE을 디폴트로 설정하며 필수는 아니도록(required = false) 설정한 값을 `condition`이라는 매개변수로 사용한다.    
-> 해석 : searchKeyword(jsp의 name 값)이라는 Param을 받아서 만약 값이 없다면 TITLE을 디폴트로 설정하며 필수는 아니도록(required = false) 설정한 값을 `Keyword`라는 매개변수로 사용한다.    
<br><br>

**전체 코드**

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

<br><hr>


#### 검색 타입 설정 테스트
    
![image](https://user-images.githubusercontent.com/84966961/132276199-2d867585-ba38-4044-97e6-3e765f131c7d.png)  

   
![image](https://user-images.githubusercontent.com/84966961/132276242-050c168f-0128-489e-93c4-e1a405eafdbd.png)    



<br><hr>


#### @ModelAttribute
    
스프링에서 컨트롤러가 불러질 때(생명주기 내에서) 사전에 자동적으로 ModelAttribute 어노테이션 구성을 해놓은 메소드들이 실행되어 준비되어진다. 그렇게 되면 다음 코드처럼 conditionMap이 생성되게 된다. 이렇게 되게 되면 자동적으로 scope가 생성되고 우리는 Map을 가져다 사용할 수 있다.
   
또한, `conditionMap`은 생성된 상태에서 Model 객체가 생성될 당시 함께 추가되어 뷰페이지로 이동하여 사용할 수 있게 된다.

```java
	@ModelAttribute("conditionMap")	// 이름은 원하는대로 작성 가능하다. 이름은 뷰(jsp)에서 사용한다.
	public Map<String,String> searchConditionMap() {
		Map<String, String> conditionMap = new HashMap<String, String>();
		
		conditionMap.put("제목", "TITLE");
		conditionMap.put("내용", "CONTENT");
		
		return conditionMap;
	}
```


#### 검색 목록을 자동 작성되게 하기

기존 jsp에서 select 태그로 구성했던 정보를 conditionMap을 가져와서 자동적으로 option값을 작성해주도록 만든다.   
  


**변경 전 jsp**
```jsp
		<form action="getBoardList.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="TITLE">제목
							<option value="CONTENT">내용
						</select>
						<input name="searchKeyword" type="text" />
						<input value="검색" type="submit" />
					</td>
				</tr>
			</table>
		</form>
```


**변경 후 jsp**
```jsp
		<form action="getBoardList.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width="700">
				<tr>
					<td align="right">
						<select name="searchCondition">
							<option value="TITLE">제목
							<option value="CONTENT">내용
						</select>
						<input name="searchKeyword" type="text" />
						<input value="검색" type="submit" />
					</td>
				</tr>
			</table>
		</form>
```


<br><hr>


#### 같은 컨트롤러 내부에서 모두 사용한 객체

다음 그림과 같이 같은 컨트롤러 내부에 있는 getBoard(Model model 포함)에 사용시에도 똑같이 적용되는 것을 확인할 수 있다.    

단, Model 내부에 저장되어 이동되어 지는 것이므로 Model이 매개변수로 정해져 객체가 생성되어야 한다.   
   
![image](https://user-images.githubusercontent.com/84966961/132280409-ac05cf57-6be2-4dff-831b-d44b5b40dd08.png)   

**Model**

|객체|값|
|-|-|
|condition|Map|
|..|..|
.
.
.
<br><hr>


#### @SessionAttributes("board")
    
 만약 뷰페이지로 보낼 때 세션에 저장했지만 다시 한번 뷰페이지에서 보내지 않은 값을 받고 싶다면 @SessionAttributes("board") 어노테이션을 통해 과거에 보냈었지만 현재는 보내지 않은 값도 받아낼 수 있다.   

 사용할 컨테이너에 어노테이션을 붙이게 되면 `board`라는 이름으로 저장되어 세션에 존재하는 값들을 받아올 수 있다.




<br><hr>



