# Lecture14  BoardWeb4 & Test15
Key Word : 모든 컨트롤러 형식 변경, Test15

<hr/>

## 기존 프로젝트(BoardWeb4)에 이어서..

 새로운 버전을 만들어 실행한다.
 
 BoardWeb4에서는 Spring 자동 빈 스캔 기능을 활용하여 예제를 만들어보고 Spring을 익혀본다.     

   
 [BoardWeb4 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0903_Lecture13/BoardWeb4)
 
 <br><hr/>

### 3.2 모든 컨트롤러 형식 변경

 스프링으로 MVC 모델을 실행한다면 다음만 기억하도록 하자    
 
 1. 컨트롤러에는 `@org.springframework.stereotype.Controller`을 붙인다.
 2. 해당 메소드 이름은 컨트롤러의 기능 이름과 똑같이 하며 받았으면 하는 url 패턴을 받도록 한다.
 3. 매개변수에는 생성했으면 하는 객체를 만든다. 또한, VO 같은 객체가 있다면 들어오는 데이터 값의 이름과 VO 내부의 필드명이 같다면 자동으로 주입된다.



**GetBoardController.java**
```java
@Controller
public class GetBoardController {

	@RequestMapping("/getBoard.do")
	public ModelAndView getBoard(BoardVO bVo, BoardDAO bDao, ModelAndView mav) {
		System.out.println("글 상세 처리");

//		String seq = request.getParameter("seq");
//		BoardVO bVo = new BoardVO();
//		bVo.setSeq(Integer.parseInt(seq));

//		BoardDAO bDao = new BoardDAO();
		bDao.updateBoardReadCnt(bVo);
		BoardVO board = bDao.getBoard(bVo);
		
		// 해당 정보를 mav에 넣어 전송
//		HttpSession session = request.getSession();
//		session.setAttribute("bVo", board);
		
//		ModelAndView mav = new ModelAndView();
		mav.addObject("board",board);
		mav.setViewName("getBoard.jsp");
		
		return mav;
	}

}
```
 

**UpdateBoardController.java**
```java
@Controller
public class UpdateBoardController{

	@RequestMapping("/updateBoard.do")
	public ModelAndView updateBoard(BoardVO bVo,BoardDAO bDao,ModelAndView mav) {
		System.out.println("글 수정 처리");

		bDao.updateBoard(bVo);
		
		mav.setViewName("redirect:getBoardList.do");
		
		return mav;
	}

}
```
 

**DeleteBoardController.java**
```java
@Controller
public class DeleteBoardController {

	@RequestMapping("/deleteBoard.do")
	public ModelAndView deleteBoard(BoardVO bVo,BoardDAO bDao) {
		System.out.println("글 삭제 처리");
		
		bDao.deleteBoard(bVo);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		
		return mav;
	}


}
```
 

**LoginController.java**
```java
@Controller
public class LoginController {

	@RequestMapping("/login.do")
	public String login(UserVO uVo, UserDAO userDAO, HttpSession session) {
		System.out.println("로그인 처리");

		// 1. 사용자 입력 정보 추출
//		String id = request.getParameter("id");
//		String password = request.getParameter("password");
		
		// 2. DB 연동 처리
//		UserVO uVo = new UserVO();
//		vo.setId(id);
//		vo.setPassword(password);
//		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(uVo);

		// 3. 화면 네비게이션
//		ModelAndView mav = new ModelAndView();
		// viewResolver가 prefix와 suffix를 붙여 사용하게 해준것처럼 mav도 가지고 있다.
		if(user!=null) {
			session.setAttribute("user", user);
			return "getBoardList.do";
		} else {
			return "login.jsp";
		}
		 	
		
		
		// 기존 네비게이션 삭제
//		if (user != null) {
//			System.out.println("로그인 작동");
//			return "getBoardList.do";
//		} else {
//			System.out.println("로그인 작동 실패");
//			return "login";
//		}

	}

}
```
 

**LogoutController.java**
```java
@Controller
public class LogoutController {

	@RequestMapping("/logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		System.out.println("로그아웃 처리");

//		HttpSession session = request.getSession();
		session.invalidate();
		
//		ModelAndView mav = new ModelAndView();
		mav.setViewName("login.jsp");
		
		return mav;
	}

}
```
 
 
 <br><hr/>



# Test15 


[TEST15 코드 보러가기](https://github.com/Moveuk/2021_Spring/tree/main/0906_Lecture14/Test15)

<br><br>

BoardWeb3.2 버전의 시험이다.

 
 <br><hr/>




