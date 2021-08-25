# Lecture05 Test13 리뷰, BoardWeb2
Key Word : AOP (Aspect Oriented Programming), Bean Braph, XML AOP, @annotation AOP,     

<hr/>

 ## 2. 공통 관심사 가지기(Aspect) _ 이어서..
 
 
### xml 파일 Bean Braph 켜는 법
   
![image](https://user-images.githubusercontent.com/84966961/130715049-62223f9d-46eb-4413-ab38-ae4b23df77f3.png)   

![image](https://user-images.githubusercontent.com/84966961/130715058-14551648-e09f-469e-a794-92260e793a56.png)   


<br><br>

#### 2.4.4 동작 시점 예제

1. 새로운 bean 등록.

```xml
	<bean id="before" class="com.springbook.biz.common.BeforeAdvice"></bean>
	<bean id="afterReturning" class="com.springbook.biz.common.AfterReturningAdvice"></bean>
	<bean id="afterThrowing" class="com.springbook.biz.common.AfterThrowingAdvice"></bean>
	<bean id="after" class="com.springbook.biz.common.AfterAdvice"></bean>
	<bean id="aroundAdvice" class="com.springbook.biz.common.AroundAdvice"></bean>
	
	<!-- aop 등록 -->
	<aop:config>
		<aop:pointcut expression="execution(* com.springbook.biz..*Impl.*(..))" id="allPointcut" />

		<aop:aspect ref="aroundAdvice">
			<!-- before | after-returning | after-throwing | after | around  사용 -->
			<aop:around method="aroundLog" pointcut-ref="allPointcut" />
		</aop:aspect>
		
	</aop:config>
```

<br>

2. exception 테스트를 줄 때는 다음과 같이 코드를 변경해 주어야 한다.

```java
	@Override
	public void insertBoard(BoardVO bVo) {
		
		// exception log를 위한 throw : 강제 예외 int는 0이 들어갈 수 없음.
		if (bVo.getSeq() == 0) {
			throw new IllegalArgumentException("0번 글 등록할 수 없습니다.");
		}
		
		boardDAO.insertBoard(bVo);
	}
```

**before**    
![image](https://user-images.githubusercontent.com/84966961/130710652-23945a02-ef2a-4c03-9be4-6fb27a43ccb8.png)    

<br><br>

**after-returning**    
![image](https://user-images.githubusercontent.com/84966961/130710588-bf9847b8-00b2-4dc9-87a6-675f301b3ea9.png)   

<br><br>

**after-throwing**    
![image](https://user-images.githubusercontent.com/84966961/130710154-b4a9616a-6d43-44d1-b0c2-9f5e87a73dd2.png)   

<br><br>

**after**     
![image](https://user-images.githubusercontent.com/84966961/130710454-653c31c0-f726-443f-8861-809161c7f237.png)   

<br><br>

**around**     
![image](https://user-images.githubusercontent.com/84966961/130711092-ad345a1b-6941-4eae-a9b4-8045bc3c5775.png)



<br><br><hr>



#### 2.4.5 @annotaion 방식 advice 적용
    
<br>
    
1. applicationContext.xml 파일에 aop autoproxy 추가    

```xml
	<!-- 자동 aop 등록 -->
	<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
```


<br><br>

2. 원하는 빈을 @annotaion으로 등록해주고 pointcut + advice로서 즉 Aspect로서 작동하게 하기 위해서 @Aspect 추가해줌.    
   
```java
import org.springframework.stereotype.Service;

// 비즈니스 로직과 관련되어 사용되기 때문에 서비스로 빈을 등록함.
@Service
// aop Aspect화 xml에 사용한 코드와 동일하다.
@Aspect
public class LogAdvice {
//	public void printLog() {
//		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
//	}
}
```

<br><br>

3. Pointcut으로 사용할 메소드 정의 및 등록    

```java
@Service
@Aspect
public class LogAdvice {
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Pointcut("execution(* com.springbook.biz..*Impl.*get(..))")
	public void getPointcut() {
		
	}
}
```

<br><br>

4. 공통 관심사 메소드 추가   

```java
	// 공통 관심사 메소드
	@Before("allPointcut()")
	public void printLog() {
		System.out.println("[공통 로그] 비즈니스 로직 수행 전 동작");
	{
```

<br><br>

**결과 화면**    
<br>

이 전에 했던 xml을 통해 코드로 등록하는 것과 똑같은 결과를 내보인다.

<br>
![image](https://user-images.githubusercontent.com/84966961/130716155-02b6ab7f-d1a6-46ff-9702-4c67c9d69581.png)   




<br><br>

5. 같은 방식으로 beforeAdvice 준비


```
@Service
@Aspect
public class BeforeAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Before("allPointcut()")
	public void beforeLog() {
		System.out.println("[사전 처리] 비즈니스 로직 수행 전 동작");
	}
}
```
<br><br>

**@annotation 방식 설정 **    


**before**    
![image](https://user-images.githubusercontent.com/84966961/130717160-c3d83bcf-6669-4294-9c6f-ffb942aef647.png)

<br><br>

**after-returning + returnObj 활용**    
![image](https://user-images.githubusercontent.com/84966961/130718535-868a7f3e-d127-4892-90ac-6e61c823b0d3.png)

<br><br>

**after-throwing**    
![image](https://user-images.githubusercontent.com/84966961/130720715-76ca6d6a-dfb3-432e-8989-55b94e254647.png)
![image](https://user-images.githubusercontent.com/84966961/130720696-acab087d-fdcb-4ed0-9966-f8fd0e3d2cdb.png)

<br><br>

**after**     
![image](https://user-images.githubusercontent.com/84966961/130721574-11f5c1f2-6ea7-40cc-901d-e04a12b0c185.png)

<br><br>

**around**     
![image](https://user-images.githubusercontent.com/84966961/130711092-ad345a1b-6941-4eae-a9b4-8045bc3c5775.png)




<br><br><hr>


#### 2.4.6 stopwatch 기능 : 비즈니스 로직 실행 시간 측정
    
**AroundAdvice**   
```java
@Service
@Aspect
public class AroundAdvice {
	@Pointcut("execution(* com.springbook.biz..*Impl.*(..))")
	public void allPointcut() {
		
	}
	
	@Around("allPointcut()")
	public Object aroundLog(ProceedingJoinPoint pjp) throws Throwable {
		// 비즈니스 로직 실행 시간 측정
		StopWatch stopWatch = new StopWatch();
		// 비지니스 로직 전 시작
		stopWatch.start();
		// 비지니스 로직 실행
		Object obj = pjp.proceed();
		// 스탑워치 종료
		stopWatch.stop();
		System.out.println("() 메소드 수행에 걸린 시간 : " + stopWatch.getTotalTimeMillis() + "(ms)밀리초");
		
		return obj;
	}
}
```
<br>

**결과 화면**    
<br>
![image](https://user-images.githubusercontent.com/84966961/130722766-aa2e65dd-16d6-42f4-a336-fa5bdd18e6c9.png)




<br><br><hr>








































<br><br><hr>

