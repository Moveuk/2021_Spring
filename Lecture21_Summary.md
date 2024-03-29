# Lecture21 Springboot 시작 전 기본 개념

Key Word : Socket, HTTP, Tomcat, Web Server, 서블릿 컨테이너, web.xml            

<hr/>

## Socket이란?  
   
### Socket
   
-> 운영체제가 가지고 있는 것!   
   
![image](https://user-images.githubusercontent.com/84966961/142856632-f839f305-5ac9-48e9-a604-df7a89dfe9ae.png)    

 포트를 잡고 계속 지속되기 때문에 Socket 사용시 cpu의 부하가 생기게 됨.
 
 Http 통신 - **Stateless** 방식을 사용 : 연결을 끊은 방식을 사용함.
    
a.txt 를 보내주고 요청을 닫음. 그런데 이 방식의 단점은 요청자가 누구인지 구분할 수 없음.   
   
-> 이 단점을 보완하면서 만들어진 것이 `web server`임.

![image](https://user-images.githubusercontent.com/84966961/142856915-c7c12d38-809f-476d-bcae-80caaa94705e.png)

<br>

### HTTP의 유래   
   
팀 버너스리가 스위스 Cern 연구소에서 박사들의 모든 연구 논문을 모으기 위한 Server를 만들고 서치하여 받기 위해서 소켓 기반의 HTTP 통신을 탄생시킴.  
   
 <br>
   
### HTTP의 목적
   
-> HTML로 된 문서를 전달하기 위한 목적

✨ **팀 버너스리 "개인이 데이터 통제하는 새로운 웹 만들 것"**   
(기사 출처 : https://www.digitaltoday.co.kr/news/articleView.html?idxno=301334)   

-> 기존의 중앙 집중화(예시 : 논문 서버)된 데이터들을 분산하여 개인이 데이터를 통제할 수 있는 **Solid** 프로젝트를 진행 중임.
-> 페이스북 Google과 같은 대기업이 데이터를 중앙집중화 되어가고 있고 개인 정보를 주어야지만 서비스를 이용할 수 있는 현 상황을 타개할 수 있는 좋은 방법인 듯 하다. 이를 통해 팀 버너스리가 "앱 자체가 서로 대화하고, 개인들의 생활과 기업들의 목표를 풍성하고 효과적으로 구현할 수 있는 방법을 모색하고 협력하는 것을 상상해보라" 라고 함.   
   
<br><hr>

## Tomcat이란?  

http : 소켓을 통하여 만들어진 것.

운영체제가 소켓이라는 기능을 시스템이 콜하여 만들어낸 것임.(시스템콜)

### 톰켓 vs 웹서버

웹 서버 : 어떤 데이터를 가지고 있는 컴퓨터가 Client로 하여금 요청을 받아 데이터를 뿌려주면 그것 자체가 **웹서버**이다.   
   
![image](https://user-images.githubusercontent.com/84966961/142860471-455f735d-20d3-409d-8b78-4e96fef45d8d.png)
   
이 때, "을"이 요청하지 않으면 응답을 할 수 없음. 그러므로 만약 "을"을 알고 싶다면 Socket을 이용한 통신을 해야함.
    
만약 A가 "갑"에게 어떤 데이터를 보냈을 때 모든 을인 A, B, C에게 알리고 싶다면 Socket 통신을 해야지 가능함.   
-> 연결이 지속되므로 상대가 누군지를 알 수 있기 때문임.
   
이 때, 주고 받는 자원들은 Static 한 자원들임. (정적인 자원들)
   
### Apache와 Tomcat 

웹서버인 Apache는 정적인 파일들만 보낼 수 있다.
   
http 통신 중에 Client 측에서 JSP에 대한 요청이 들어온다면 웹 서버인 Apache는 Java 코드를 이해하지 못하기 때문에 제대로 된 응답이 불가능 할 것이다. 

이 때, **Tomcat**을 사용하여 JSP를 분석하고 자바 코드를 컴파일하여 다시 Client가 이해할 수 있는 즉, 웹 브라우저에 띄울 수 있는 HTML파일로 응답 할 수 있도록 만들어 보내주는 것이 Tomcat의 역할이다.

![image](https://user-images.githubusercontent.com/84966961/142861729-564c346d-1877-4552-8f11-30073fa9a339.png)


<br><hr>

## 서블릿 컨테이너    

![image](https://user-images.githubusercontent.com/84966961/142862387-c5fc928f-df24-4f57-9fd8-d66e282d6d2d.png)


### 🏁 URL과 URI 의 차이

 -> 스프링에서는 URL(Location)을 이용한 .html, .css, .png 같은 파일을 요청할 수 없다.
 
 
 **URL(Location)** : 자원 접근 - ex) http://naver.com/a.png    
 **URI(Identifier)** : 식별자 접근 - ex) http://naver.com/picture/a    
    
 -> URI는 특정한 파일 요청을 할 수 없으므로 요청시에는 무조건 **자바**를 거친다. 무조건 **자바**를 거치므로 톰켓을 거친다고 보면된다.
 

<br><hr>

### request와 response

![image](https://user-images.githubusercontent.com/84966961/142950734-3ee45ab6-749b-429f-af84-33da0531646e.png)

![image](https://user-images.githubusercontent.com/84966961/142955054-085d0806-d44c-4478-8d00-72041e27236d.png)

![image](https://user-images.githubusercontent.com/84966961/142955058-f5384c91-200b-407e-8ca7-65183574c4d9.png)


<br><hr>

## web.xml 의 목적과 기능    

### ① ServletContext의 초기 파라미터

-> 초기 파라미터를 설정하여 웹서버 내부에서 어디서든지 신원 확인을 할 수 있음. 은행의 공인인증서 같은 개념?!

### ② Session의 유효시간 설정

-> 초기 파라미터와 함께 Session의 유효기간이 정해지는 데 만료시 filter에게 Session의 연장을 요청해야함.

### ③ Servlet/JSP에 대한 정의와 매핑

-> 웹서버에 도달한 요청이 어디로 가야하는지에 대해서 방향 설정을 하고 내부 로직을 호출함.

### ④ Mime Type 매핑

-> 요청이 들어왔을 때 어떤 타입의 데이터를 들고 오는지 확인하고 매핑함.


**몰랐던 점** - get방식의 요청은 파라미터에 담아서 가져오지만 데이터를 주기 위해서 요청하는 것이 아니라 받아온 파라미터를 토대로 데이터를 받아가기 위해서 사용하는 것.

```
Mime Type

 Multipurpose Internet Mail Extensions의 약자로 간단히 말하면 "파일 변환"을 뜻함.
 
 Mail 약자에 들어가 있는 이유는 최초 사용 용도가 메일에 파일을 텍스트 파일로 변환하여 보내기 위해서 만들어져서 Mime Type으로 굳어짐.
 
 지금 인터넷 상에서 데이터 파일을 변환하여 보내기 위하여 사용됨. 기존에는 ASCII 로 인코딩 했었지만 치명적인 부작용으로 인하여 텍스트 파일로 변환해서 보내야만 했고 그 새로운 인코딩 방식이 "Mime" 인코딩임.
 
 http 통신을 할 때 헤더에 Content-type 정보를 담아서 보내게 되며 만약 웹 브라우저가 지원하는 타입이라면 열어볼 수 있게 됨.
(헤더는 사용되고 있는 웹 서버의 소프트웨어의 타입, 서버의 날짜와 시간, HTTP 프로토콜, 사용중인 커넥션 타입등을 지정한다. 헤더는 또한 클라이언트가 이런 가상 패스나 도메인에 대해서 저장해야 할 쿠키를 포함함) 

JSP 에서는 다음과 같이 html의 타입을 표시했었음.
<%@ page contentType="text/html" %>

```
 
[Mime Content-type 종류](https://developer.mozilla.org/ko/docs/Web/HTTP/Basics_of_HTTP/MIME_types/Common_types)

### ⑤ Welcome File list

-> 헤더에 어디로 갈지 모르거나 데이터에 대한 정보가 없을 때 자동으로 매핑하여 알맞는 요청 처리를 하도록 함.

### ⑥ Error Pages 처리

-> 매핑되지 않은 페이지를 요청했을 경우에는 Error Pages로 보내서 처리하도록 함.

### ⑦ 리스너 / 필터 설정

-> 필터 : 들어오는 요청을 받을 수 있는지 확인하여 필터링함.   
-> 리스터 : 특정 요청이 들어왔는지 확인하여 처리할 수 있도록 함.   
   
이 둘은 동시에 작동하여 리스너가 우선권을 가지고 요청을 받아감.

### ⑧ 보안

-> 개발자가 정한 요청 규칙을 토대로 요청을 받을지 필터링하는 기술.



이 모든 과정에서 할 일이 많은 웹서버의 문지기를 FrontContoller 패턴으로 두어 FrontController 가 이 요청들을 처리하도록 함.

<br><hr>

## DispatcherServlet이란?

### FrontController 패턴

 request 요청을 받는 웹서버의 앞단이 알아서 필요한 클래스에 넘겨준다. : web.xml에 모든 경우의 수를 개발자가 정의하고 대처하기가 힘들기 때문.

 여기서 톰캣이 알아서 다음과 같은 일을 처리함.
 
 ① 웹서버로 들어오는 모든 요청은 Bufferedreader을 통해 가변길이의 문자(Data Stream?)을 받아서 이를 톰캣이 자바 객체(request)로 만들어줌.(그래서 request.메소드 사용이 가능함)
 
 ② web.xml 에 `.do`와 같은 패턴의 URI 주소가 들어오면 **FrontController**가 낚아채도록 세팅을 해놓은 상태에서 요청에 알맞는 자원으로 request를 다시 보냄.(외부에서는 자원에 접근이 불가능하지만 내부에서 다시 request를 통해서 접근하므로 접근이 가능해짐.)
 -> 이 때 내부에서 request를 할 때 메모리에 저장되있던 최초의 request는 사라지게 되는 데 이를 방지하기 위해서 requestDispatcher를 사용하게 됨.
 
 request 요청에는 보낸 사람의 정보, 가져온 데이터들 등의 다양한 정보를 포함하고 있고 이 후 이 정보들을 토대로 response 객체를 만들게 됨.
 
 
 ### requestDispatcher
 
 requestDispatcher는 페이지간 데이터를 가지고 이동할 수 있도록 도와주는 것임. (우리가 forward를 통해 데이터를 보내는 이유)
 
 
 ### DispatcherServlet
 
 DispatcherServlet = FrontController 패턴 + RequestDispatcher 
 
 ```
 Dispatch 는 "보내다"라는 뜻
 
 HTTP 프로토콜을 통해 오는 모든 요청을 가장 먼저 받아 적합한 컨트롤러에 위임해주는 프론트 컨트롤러(Front Controller)

----------
HTTP 요청의 흐름

Client -> webserver -> Tomcat(서블릿컨테이너) -> DispatcherServlet
 ```
 
 DispatcherServlet이 자동 생성 될 때 대부분 필터인 수 많은 객체(IoC : 제어의 역전)들이 생성됨. 해당 필터들은 내가 직접 등록할 수 도 있고 기본적으로 필요한 필터들은 자동 등록 되어짐. 

<br><hr>
 
## ApplicationContext

 여기부터는 스프링에 대한 이야기이다. Apache는 정적인 data를 제공하는 웹서버이지만 Spring이 관리하게 되면 정적인 자원을 요청할 수 없고 전부 자바 파일로 Servlet을 요청하여 처리하게 된다.
 
 DispatcherServlet의 역할은 주소 분배 역할을 한다고 언급했었다. 주소 분배 역할을 하기 위해서는 해당 class들이 메모리에 들어있어야 하는데(new 하여 객체 생성) 본인이 호출해야하는 class 파일을 실행시켜준다.
 
 객체에는 Static한 메소드들은 메모리에 미리 로드되어 있고 이외에는 객체 생성과 소멸의 주기를 가지고 있다.    
 Static의 특성을 사용해서 미리 사용될 class들이나 메소드들을 로드시켜놓으면 좋겠지만 보통 웹서버는 작게는 수 십명 혹은 그 이상의 수 많은 인원이 사용하게 된다.   
 이 때 Static한 파일을 사용하게 되면 효율은 좋겠지만 충돌날 위험이 있다.   
 이 때문에 대부분 이 클래스들은 Static 한 상태가 아닌 new를 하여 필요할 때 만들어 사용하고 소멸시켜주어야 한다.    
 
 하지만, Spring은 IoC(제어의 역전)를 통해서 개발자가 객체를 new하여 생성하는 것이 아닌 DispatcherServelet이 컴포넌트 스캔을 통하여 객체를 관리해준다.(src 내부의 파일-Springboot 에서 부터는 내부 전부를 스캔함 / Spring에서는 스캔 영역을 정해줄 수 있었음.)

 DispatcherServelet이 객체를 관리하는 방법은 @ annotation을 사용한다. 예를들어 @Controller, @RestController, @Configuration, @Repository, @Service, @Component 등의 annotation을 사용하여 인식하고 관리하게 된다.

 따라서 개발자는 스프링의 IoC 개념을 사용하기 위해서 공부해야하는 것은 각 annotation의 사용법이다.(직접 생성한 객체를 가리키는 레퍼런스 변수를 관리하는 것이 아닌 대리로 관리할 DispathcerServlet에게 알려주기 위해서)
 
 -> 이 후 관리되어지는 객체들은 DI(의존성 주입)에 의해서 사용할 수 있으므로 주소 자체를 몰라도 된다. ApplcationContext에 접근하여 필요한 객체를 가져오면 되기 때문이다.

### ApplicationContext의 두 가지 종류

- root-applicationContext : **ContextLoaderListener**에 의해 실행됨   

-> root : 최상단의 applicationContext를 뜻함

**-> Servlet-applicaitonContext이 관리하는 어노테이션을 제외한 Service, Repository 등을 스캔하고 DB와 관련된 객체를 생성함.(스캔: 메모리 로딩)**

- Servlet-applicaitonContext : **DispatcherServlet**에 의해 실행됨 

-> ViewResolver, Intercepter, MultipartResolver 객체를 생성하고 웹과 관련된 어노테이션 Controller, RestController를 스캔함.
-> 이 파일들은 DispatcherServlet에 의해 실행된다.

**-> 즉 web과 관련된 정보들을 가지고 있으며 web과 관련된 내용만 바라보고 메모리에 띄우며 관리한다.**


### ContextLoaderListener

 ContextLoaderListener 는 **공통 요소 관리**를 하기 위한 리스너이다. root_AppliacationContext 파일을 읽어서 DB 파일과 같이 공통적으로 쓰여야 하는 파일들을 관리하게 되고 그 이후 DispatcherServlet이 띄워지기 때문에 DB에서 이 후 객체들을 관리할 수는 없다. 하지만 DispatcherServlet이 관리중인 Class들은 맨처음 생성되어 메모리에 올라간 공통 요소들을 사용하여 작업할 수 있으므로 DB와 관련된 객체들을 사용할 수 있다.
 
 모든 쓰레드가 단 하나의 요소를 공통적으로 사용하는 것들을 ContextLoaderListener가 맨처음 띄우게 된다.

![image](https://user-images.githubusercontent.com/84966961/144286178-8069ad38-a669-41eb-9784-2b6632cdd807.png)

![image](https://user-images.githubusercontent.com/84966961/144283501-ec188928-8f16-4811-b41f-817ef09ac362.png)   
출처 : https://javannspring.tistory.com/231   


 ① Tomcat(WAS)에 의하여 web.xml를 로딩   
 ② ContextLoaderListener 로딩됨(Servlet 생명 주기 관리)   
 ③ ContextLoaderListener는 root-context.xml을 읽어와서 공통 요소들을 로드시킴   
 ④ ServiceImpl들이 실행되고 DAO, VO들이 관리됨(root는 Service, Repository 등의 어노테이션을 관리하기 때문)   
 ⑤ request 요청이 오게됨 -> 여기서부터 실제 http 통신   
 ⑥ 요청에 의해서 첫 DispatcherServlet이 생기고 두번째 스프링 컨테이너를 만듬   
 ⑦ DispatcherServlet은 Servlet-context.xml 을 로딩하여 web에 관련된 파일을 로드함   
 ⑧ request에 따라 컨트롤러가 실행됨.     
  
### Bean Factory

 이외에도 필요한 객체를 Bean Factory에 등록하여 사용할 수 있음. 초기에 등록하는 것이 아닌 `getBean()`이라는 메소드를 통하여 호출하여 메모리에 로드할 수 있다. 이것 또한 IoC이다.
 
 -> 이것이 lazy-loading(필요할 때 호출하여 로딩을 늦게하는 것)이다.
 
 ```java
@Configuration
Class A {

   @Bean
   객체 메소드() {
      ~~~~
      
      return 객체();
   {

}
```

<br><hr>

## 응답(Response)

 get 요청 -> https://localhost:8181/post/1
 
 URI에 의한 요청이 오면 적절한 컨트롤러를 찾아 실행함. 이 때 요청에 의한 답으로 **Data** 혹은 **html** 파일을 응답하게됨.
 
 html의 경우에는 ViewResolver가 관여하게 되며, Data는 MessageConverter 가 작동하고 기본 메세지 컨버팅 전략은 json이 디폴트이다.

```
ViewResolver란?

만약 .jsp 파일을 요청하게 되면

prefix : jsp파일의 경로 주소
surfix : .jsp

정보를 붙여 String(단순히 return 값으로 "jsp파일명"을 사용할 수 있게 됨) 에 대한 값을 리턴할 수 있도록 리턴해주는 기능을 한다.

만약 ViewResolver가 관여하지 않게 되면 return 값으로 만약 "hello"가 사용되었을시 그냥 "hello"라는 데이터가 전송되게 된다.
```
```
MessageConverter

 ViewResolver와는 달리 특정 메소드 앞에 @ResponseBody라는 어노테이션을 붙이게 되면 이를 단순한 Data로 보게 된다.
 
 만약 User라는 Entity가 존재한다고 하자.
 
 User {
 int id = 1;
 String name = moveuk;
 }
 
 Hello()라는 메소드의 리턴을 User로 할 때 스프링이 MessageConverter를 작동시켜 JSON을 디폴트로 컨버팅하여 DATA를 보내게 된다.
 
 MessageConverter는 현재는 Jackson이라는 라이브러리를 사용함. Jackson이라고 부르지 않은 이유는 미래의 더 나은 중간 데이터 포멧이 생기게 되면 추상화의 개념으로서 단순 교체를 하기 위함임.
 
```

<br><hr>
