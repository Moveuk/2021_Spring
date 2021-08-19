# Lecture02 STS
Key Word : 환경 설정

<hr/>

 ## 1. STS 환경 설정
    
 <br>
    
 ### 1.1 STS 환경 설정
   
 본인이 사용하는 기본 환경 설정을 맞춰주면 된다.     
     
 예를 들어 encoding(utf-8 변경), font, html 양식, auto(자동 채움 기능) 등등이 있다.
   
![image](https://user-images.githubusercontent.com/84966961/129990265-fbd26a4f-1581-4522-8fc2-4ada113b603f.png)



 <br><br><hr>
    
 ### 1.2 Maven 내장 Library 구경
 
  - 프로젝트에 필요한 다양한 라이브러리가 내장되어있다.
    - jstl : jsp 태그 라이브러리
    - log4j : 로그를 위한 라이브러리
    - spring : spring 라이브러리
    - junit 등등.. 정확히 용도를 알지 못하여 알게 되면 추후 수정할 예정.
 
 ![image](https://user-images.githubusercontent.com/84966961/129990153-35e5ad8d-c167-46d9-9cec-f605f471f0c1.png)

       
 <br><br><hr>
    
 ### 1.3 프로젝트 환경 설정
   
1. 프로젝트 alt+enter 혹은 오른쪽 클릭 후 property 클릭.

![image](https://user-images.githubusercontent.com/84966961/129989989-4d25f1fd-99d3-4074-a261-390780e4dfb2.png)

2. Project Facets 자바 1.8 버전, runtime 탭에 프로젝트에 사용할 서버(본인은 톰캣 8.5) 설정

![image](https://user-images.githubusercontent.com/84966961/129990074-851dd6a8-5d11-485c-845d-02b06d8e92d8.png)

 <br><br><hr>

    
 ### 1.4 pom.xml 수정 : 사용할 라이브러리 버전 변경
  
 1. 자바 버전 스프링 버전 변경
  
![image](https://user-images.githubusercontent.com/84966961/129991852-0d490402-e940-4492-8344-2e4b54b2ccdd.png)

 2. junit 버전 변경

![image](https://user-images.githubusercontent.com/84966961/129991952-b6c4b90a-ff88-4800-a8c7-d409a05085f7.png)

 3. 메이븐 컴파일러 버전 변경

![image](https://user-images.githubusercontent.com/84966961/129991982-a7ffb057-bfb4-434e-8a08-beeea6a70f5a.png)

 4. 수정 후 재배포후 라이브러리 버전 변경 확인

![image](https://user-images.githubusercontent.com/84966961/129992060-cd1102c5-f33d-4bfd-8dce-5364c5de8e31.png)


 <br><br><hr>

    
 ## 2. Spring 기본
  
|**스프링의 기술**|
|---|
| - Presentation - View( jsp )|
| - Business - Spring( IoC(inversion of control), AOP, Inject )|
| - Persistance - Model ( jdbc, Mybatis, Jpa)|







