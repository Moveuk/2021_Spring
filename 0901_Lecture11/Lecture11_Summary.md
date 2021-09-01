# Lecture11  BoardWeb3.1
Key Word : HandlerMapping, ViewResolver, Controller 파트 분리     

<hr/>

 ## 기존 프로젝트에 이어서..
 
 [BoardWeb3.1 코드 페이지 이동](https://github.com/Moveuk/2021_Spring/tree/main/0830_Lecture09/BoardWeb3.1)
 
 
 <br><hr/>
 
## 3. MVC2 방식처럼 분할

 Model과 View가 ActionController에 합쳐져 있으므로 분리해야 한다.    
    
 ![image](https://user-images.githubusercontent.com/84966961/131693944-fe1cd76f-fe8f-4073-a23e-512a46203bf6.png)


 <br><hr/>

### 3.1 Controller을 Mapping해주는 HandlerMapping 

 HandlerMapping 클래스는 들어오는 URI Path 값을 받아 컨트롤러 객체를 내주기 위한 용도로 사용된다.


```java
public class HandlerMapping {
	private Map<String, Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String, Controller>();
		mappings.put("/login.do", new LoginController());
		mappings.put("/getBoardList.do", new GetBoardListController());
		mappings.put("/getBoard.do", new GetBoardController());
		mappings.put("/insertBoard.do", new InsertBoardController());
		mappings.put("/updateBoard.do", new UpdateBoardController());
		mappings.put("/deleteBoard.do", new DeleteBoardController());
		mappings.put("/logout.do", new LogoutController());
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}

```



 <br><hr/>


### 3.2 접두사와 접미사를 붙여주는 ViewResolver

ViewResolver는 DispatcherServlet에서 서블릿 생명주기중 init 단계에서 생성되며 기본 접미사와 접두사를 지정하여 들어오는 uri값을 변환시켜주기 위하여 사용한다.



```java
public class ViewResolver {
	public String prefix;
	public String suffix;
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}	
	
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}	
	
	public String getView(String viewName) {
		return prefix + viewName + suffix;
	}
}
```


 <br><hr/>


### 3.3 Controller 파트

 기존의 DispatcherServlet에 작성되었던 컨트롤러 파트를 각 클래스로 분리하여 하나의 Controller interface를 사용해 다형성이 충족되도록 만들었다.

 <br><hr/>


 **login & logout Controller**


```java
public class LoginController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그인 처리");

		String id = request.getParameter("id");
		String password = request.getParameter("password");

		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);

		if (user != null) {
			System.out.println("로그인 작동");
			return "getBoardList.do";
		} else {
			System.out.println("로그인 작동 실패");
			return "login";
		}

	}

}
```
```java
public class LogoutController implements Controller {

	@Override
	public String handlerRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃 처리");

		HttpSession session = request.getSession();
		session.invalidate();
		
		return "login";
	}

}
```

<br><br>

이하 나머지 컨트롤러 생략.


 <br><br><br><hr/>











