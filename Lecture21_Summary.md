# Lecture21 Springboot 시작 전 개념

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

## web.xml    



<br><hr>
<br><hr>
