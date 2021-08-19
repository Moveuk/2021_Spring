# Lecture01 STS
Key Word : STS 설치, Test Legacy 프로젝트 생성, Could not initialize class  PropertiesConverter & java.lang.ExceptioninitializerError 오류 처리

<hr/>

 ## 1. STS 설치
    
 1. Help > Eclipse MarektPlace 클릭   <br><br>
![image](https://user-images.githubusercontent.com/84966961/129849148-55c4c93b-28c2-4167-ba17-674970d9276f.png)

<br><br><hr>
 2. sts 검색   <br><br>
 ![image](https://user-images.githubusercontent.com/84966961/129849186-b1461ce9-b07b-43bd-ad3f-0122ac34bd67.png)

<br><br><hr>
 3. Spring Tool 3 (Standardalone Edition) install click     <br><br>
 ![image](https://user-images.githubusercontent.com/84966961/129849377-8884cde4-a2a3-4404-b239-dc14a5500d61.png)

<br><br><hr>
 4. 설치 목록 Confirm 클릭   <br><br>
 ![image](https://user-images.githubusercontent.com/84966961/129849410-3132c67b-c603-46fa-8d91-3df364cb9e7a.png)

<br><br><hr>
 5. 설치 파일 설치 중 화면   <br><br>
![image](https://user-images.githubusercontent.com/84966961/129849500-32c076cf-5c55-4cfc-aaeb-4923dc4b4731.png)

<br><br><hr>
 6. 약관 동의 후    <br><br>
![image](https://user-images.githubusercontent.com/84966961/129849625-0e0878cd-fa3c-4b0d-98e3-3e74604e919b.png)

<br><br><hr>
 7. 우측 하단 설치 중...   <br><br>
![image](https://user-images.githubusercontent.com/84966961/129849730-e020928d-e8ac-4dc0-8ab9-c5a78c331b31.png)

<br><br><hr>
 8. 외부 프로그램 확인 Certificate 후 다시 설치   <br><br>
![image](https://user-images.githubusercontent.com/84966961/129850075-de9f4ebd-0041-4d3f-9cf8-16c645163457.png)

<br><br><hr>
 9. 설치 완료 후 Restart Now 버튼 활성화    <br><br>
 ![image](https://user-images.githubusercontent.com/84966961/129850195-ab6922f6-19b2-4416-958e-3183b0f89ae7.png)

<br><br><hr>

 ## 2. Spring 프로젝트 생성 및 테스트
    
 1. 새로운 프로젝트 만들기 : file > new > Others... > spring 검색 후 legacy Project 클릭  
![image](https://user-images.githubusercontent.com/84966961/129851734-65be5070-df4a-46b0-90a5-6c0fd4e7fb13.png)
<br><br><hr>
 2. 프로젝트명 입력, Spring MVC Templates 선택 : 나중에 pom 설정파일 내부에서의 프로젝트 이름    
 ![image](https://user-images.githubusercontent.com/84966961/129851999-4d96384b-6336-402a-b5c7-39c4e50646e1.png)
<br><br><hr>
 3. 새로운 템플릿 설치를 위하여 yes 클릭(최초 1회)   
![image](https://user-images.githubusercontent.com/84966961/129852228-5ca85c94-1d22-4aa0-93d0-bdc5a24cee57.png)
<br><br><hr>
 4. 예시처럼 최소 3개의 폴더(레벨?) 구성으로 작성해야함.    
![image](https://user-images.githubusercontent.com/84966961/129900865-8a97dd56-2709-4693-868c-71725ffd7951.png)
<br><br><hr>
 5. 이후 다시 프로젝트를 구성하기 위하여 오른쪽 아래 초록색 바가 생기며 일정 시간 후 프로젝트 완성됨.    
 - home.jsp에 빨간 에러가 표시될 수 있는데 톰캣에서 인지 못할 수 있으므로 태그를 지웠다가 다시 넣어서 저장하면 됨.    
<br><br><hr>
 6. 서버 실행 : 프로젝트 오른쪽 버튼 클릭 후 run as > run on server   
![image](https://user-images.githubusercontent.com/84966961/129901747-b4aedaf1-d7a8-459f-9146-c3a4e9893fa7.png)    

![image](https://user-images.githubusercontent.com/84966961/129901709-2c31338d-3c87-49e4-b59e-aa69bf81b81b.png)   


<br><br><br><hr/>

## 오류들

### 오류 증상
   - legacy project 생성 도중 Spring MVC Project template을 이용하여 프로젝트를 만들려하는데 다음과 같은 오류가 등장했음.   
   - 업데이트, compile 버전 변경 등을 해보았지만 의미없었음
   - 정확한 이유를 알 수가 없어서 에러에 대한 정확한 진단을 내릴 수 없었고, eclipse 폴더 위치의 변경 등 사용도중 다양한 변수가 많아 원인을 찾을 수 없었음
   - 비슷한 에러를 겪으신 분에게 물어 해결함
   - 오류코드 예시(상황에 따라 다음 두가지 오류가 발생함.)
     - Could not initialize class  com.thoughtworks.xstream.converters.collections.PropertiesConverter
     - java.lang.ExceptioninitializerError
  
![image](https://user-images.githubusercontent.com/84966961/129859527-f40171d6-4471-4ea6-b1dd-3c627803880f.png)
![image](https://user-images.githubusercontent.com/84966961/129901017-92b443ed-c601-42c8-84a2-73a22ccec776.png)

![image](https://user-images.githubusercontent.com/84966961/129858730-74b8fac1-5dd0-4dc7-a0f8-d5d7ec0df50f.png)

![image](https://user-images.githubusercontent.com/84966961/129858823-7c7bc277-2615-456a-86f1-38e238a26701.png)

![image](https://user-images.githubusercontent.com/84966961/129859671-0c352172-f267-472b-b2a4-7f0ed8eb721c.png)

<br><br><hr/>

### 해결 방법
  1. Dynamic Web Project 생성 후 Configure -> Convert to Maven Project 연결 방법을 이용해 비슷하게나마 사용하는것    <br><br>

-> 내가 원한 방법이 아니라 패스함.    <br>

  2.  워크스페이스 .metadata 폴더 삭제 및 초기화, eclipse 2021-03 버전으로 이클립스 완전 삭제 후 다시 설치    
   <br>
  -> 본인의 경우엔 두번째 방법이 잘 작동했음.









<br/>
<hr/>



