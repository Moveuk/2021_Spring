# Lecture10  BoardWeb3.1
Key Word : Model1, MVC 방식 Spring - 삽입, 수정, 삭제, 로그아웃.. ,       

<hr/>

 ## 기존 프로젝트에 이어서..
 
 [BoardWeb3.1 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0830_Lecture09/BoardWeb3.1)
 
 
 <br><hr/>
 
## 2. Web에서의 Spring사용 - MVC2

요청을 받아 바로 처리하는 Model1은 dao dto에 대한 코드들이 혼재되어있다.   

이번에는 MVC2 방식을 이용하여 다음과 같은 구성으로 web을 구현해보자.      

![image](https://user-images.githubusercontent.com/84966961/131281922-813c6d1e-dcf5-4ff7-9eba-f4c64463909f.png)

 <br><hr/>
 
### 2.1 URI 멀티 바인딩(*.do)

```java
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}
	
	private void process(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		System.out.println(path);
	}
}
```


**결과 화면**    
 
![image](https://user-images.githubusercontent.com/84966961/131282874-e4753f44-99fc-41de-ac6a-bab4b08bec5f.png)

요청된 주소값 : http://localhost:8181/biz/login.do


 
 <br><hr/>
 
### 2.2 로그인 기능 ActionController 사용   

DispatcherServlet의 process 내부에 if문으로 로그인에 대한 코드 작성

```java
	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		System.out.println(path);
		
		if(path.equals("/login.do")) {
			System.out.println("로그인처리");
			
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			UserDAO userDAO = new UserDAO();
			UserVO userVO = new UserVO();
			userVO.setId(id);
			userVO.setPassword(password);
			UserVO user = userDAO.getUser(userVO);
			
			if (user != null) {
				response.sendRedirect("getBoardList.jsp");
			} else {
				response.sendRedirect("login.jsp");
			}
			
		}
		
		
	}
```

 <br><hr/>
 
### 2.3 로그인 기능 ActionController 사용   

 기존 getBoardList.jsp의 List를 불러오는 것을 제거하여 `.do`형식으로 서블릿을 통해 데이터를 받아올 수 있도록 해주었다.

```java
 else if (path.equals("/getBoardList.do")) {
			System.out.println("글 목록 검색 처리");
			
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			
			response.sendRedirect("getBoardList.jsp");
		}
```
**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/131427906-dc52191e-fcce-4bc5-8327-5ab54fb01985.png)



 <br><hr/>
 
### 2.4 상세보기 기능 ActionController 사용   

상세보기 기능 또한 한곳에서 구현하였다.

```java
 else if (path.equals("/getBoard.do")) {
			System.out.println("글 상세 처리");
			
			String seq = request.getParameter("seq");
			BoardVO bVo = new BoardVO();
			bVo.setSeq(Integer.parseInt(seq));

			BoardDAO bDao = new BoardDAO();
      BoardVO board = bDao.getBoard(bVo);

      HttpSession session = request.getSession();
      session.setAttribute("bVo", board);
      response.sendRedirect("getBoard.jsp");
		}
```

```jsp
<%
BoardVO bVo = (BoardVO)session.getAttribute("bVo");
%>
```



 <br><hr/>
 
### 2.5 삽입 기능 ActionController 사용   


```java
 else if (path.equals("/insertBoard.do")) {
			System.out.println("글 삽입 처리");

			request.setCharacterEncoding("UTF-8");
			BoardVO bVo = new BoardVO();
			bVo.setTitle(request.getParameter("title"));
			bVo.setWriter(request.getParameter("writer"));
			bVo.setContent(request.getParameter("content"));

			BoardDAO bDao = new BoardDAO();
			bDao.insertBoard(bVo);
			response.sendRedirect("getBoardList.do");
		}
```

 <br><hr/>
 
### 2.6 수정 기능 ActionController 사용   



```java
 else if (path.equals("/updateBoard.do")) {
			System.out.println("글 수정 처리");

			request.setCharacterEncoding("UTF-8");
			BoardVO bVo = new BoardVO();
			bVo.setSeq(Integer.parseInt(request.getParameter("seq")));
			bVo.setTitle(request.getParameter("title"));
			bVo.setContent(request.getParameter("content"));

			BoardDAO bDao = new BoardDAO();
			bDao.updateBoard(bVo);
			response.sendRedirect("getBoardList.do");
		}
```

 <br><hr/>
 
### 2.7 삭제 기능 ActionController 사용   



```java
 else if (path.equals("/deleteBoard.do")) {
			System.out.println("글 삭제 처리");
			String seq = request.getParameter("seq");
			BoardVO bVo = new BoardVO();
			bVo.setSeq(Integer.parseInt(seq));

			BoardDAO bDao = new BoardDAO();
			bDao.deleteBoard(bVo);
			response.sendRedirect("getBoardList.do");
			
		}
```



 <br><hr/>
 
### 2.8 로그아웃 기능 ActionController 사용   



```java
 else if (path.equals("/logout.do")) {
			System.out.println("로그아웃 처리");

			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
```


 
 
 <br><hr/>
 
## 3. MVC2 방식처럼 분할

 Model과 View가 ActionController에 합쳐져 있으므로 분리해야 한다.

 <br><hr/>

















