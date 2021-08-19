# Lecture02 STS
Key Word : 환경 설정, Polymorphism, Injection 주입, Constructor Injection, lazy-init, CollectionBean 활용, Properties 활용

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
| - Business - Spring( IoC(inversion of control), AOP, Injection )|
| - Persistance - Model ( jdbc, Mybatis, Jpa)|

 <br><br><hr>


### 구조

 - SamsungTV
 - LgTV
 - TVUser : was의 역할
 - TV(interface) : 표준화를 위한 용도
 - BeanFactory : Spring container의 역할
 - SonySpeaker

 <br><br><hr>

### 2.1 Polymorphism

#### Stage 1 : 객체의 생성

![image](https://user-images.githubusercontent.com/84966961/129997587-1f092457-ad70-492b-80fd-ac2a6d1de79b.png)   
![image](https://user-images.githubusercontent.com/84966961/129998239-ccd254fc-b157-421f-a252-18521f00fcfd.png)   

<br><br><hr>

#### Stage 2 : 다형성을 이용한 표준화

결국 같은 기능이므로 TV 인터페이스를 만들어 통일시킴.    

![image](https://user-images.githubusercontent.com/84966961/129997689-1b9585d6-9208-4306-b9f7-7850f18f6eb0.png)    
<br><br>

다형성을 이용한 표준화를 하여 다른 브랜드의 TV지만 같은 기능을 사용할 수 있게끔 함.    
    
```java
		// 다형성을 이용한 표준화
		TV tv = new SamsungTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		tv = new LgTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
```
 <br><br><hr>
    
#### Stage 3 : Factory 패턴 도입
    
 -> 이것을 factory 패턴으로 바꾸면 Spring container가 하는 역할임.   
   
Spring 에서는 모든 객체를 Bean으로 부름.    

**TVUser.java**

```java
public class BeanFactory {
	// 요청에 따른 객체 생성
	public Object getBean(String beanName) {
		if(beanName.equals("samsung")) {
			return new SamsungTV();
		} else if(beanName.equals("lg")) {
			return new LgTV();
		}
		return null;
	}
}
```

**TVUser.java**

```java
		// Stage 3 : Factory 패턴 도입	
		BeanFactory factory = new BeanFactory();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("tv이름 입력 : ");
		String tname = sc.nextLine();
		
		TV tv = (TV) factory.getBean(tname);
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
```

 -> 기존 자바에서 작성하여 사용하던 방식
 -> 하지만 스프링에서는 이 모든 과정이 생략됨 : Spring Container - Inversion of Control

 <br><br><hr>

### 2.2 Spring Container - Inversion of Control

 Spring에서는 이 과정을 자동으로 객체를 생성하고 삭제하며 사용자가 원활한 개발을 할 수 있도록 도와주는 것이다.

 Spring에 작성한 클래스, 빈을 알려주기 위해서는 **2가지 방식**이 있다.
 
 - xml 파일에 저장하여 알려주는 방식
 - @annotation을 이용하여 자바 코드에 넣어놓는 방식


 <br><br><hr>


#### Stage 4 : Spring XML에 빈 매핑 후 사용

1. xml 생성   

![image](https://user-images.githubusercontent.com/84966961/130001628-d48c555a-7606-4023-bcf1-7ce14a3e9398.png)   

![image](https://user-images.githubusercontent.com/84966961/130001649-72e8f2de-896e-4b02-83d5-445f573ca32a.png)   

![image](https://user-images.githubusercontent.com/84966961/130001746-0609e7db-bc8d-4b01-9837-eba80f1dc69d.png)   

![image](https://user-images.githubusercontent.com/84966961/130001780-eed1beea-dd9e-49f9-a62b-dbbf0b5bb503.png)   

 <br><br><hr>

2. bean 매핑   

![image](https://user-images.githubusercontent.com/84966961/130002414-e62f890f-1e57-4e6f-b2b6-418a7912a199.png)   

 <br><br><hr>

3. bean 사용   
```java
		// Stage 4 : Spring XML에 빈 매핑 후 사용
		// 자동으로 객체를 생성하여 불러와줌.
		
		// Spring Library import 필요
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		TV tv = (SamsungTV) factory.getBean("tv");	// new 객체 생성됨
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		factory.close();    // 생성되었던 객체 닫음.
```

<br>

factory에 초기화한 객체가 자동으로 applicationContext.xml 내부에 설정해 놓은 빈 값들을 읽고 이후 메소드 활용으로 객체를 생성, 삭제를 해준다.

![image](https://user-images.githubusercontent.com/84966961/130003306-d69c0c58-156a-4844-b262-be6e83a81b04.png)

<br><br>

기본적으로는 싱글턴 객체로 생성됨.  

![image](https://user-images.githubusercontent.com/84966961/130003855-d17a8f6e-74f8-43bd-8b89-6a8c42e482ed.png)    

하지만 다음과 같이 xml파일에 scope를 prototype으로 바꿔주면 싱글턴 패턴이 아닌 객체를 여러 개 생성할 수 있다.   

![image](https://user-images.githubusercontent.com/84966961/130004034-70a9ab0f-dd67-4caf-905f-47b2d4844fe6.png)   
   
![image](https://user-images.githubusercontent.com/84966961/130004092-60ccce7a-097f-4806-bfcb-414727e001b5.png)   
   
 <br><br><hr>

4. lazy-init : xml 로딩시 객체 생성을 미리 해둘지에 대한 여부    
   
 -> 다음 사진처럼 bean 객체에 lazy-init 속성을 true로 걸어두면 객체가 사용되기 전까지 미리 load되어 있지 않게 된다.   
   
![image](https://user-images.githubusercontent.com/84966961/130032735-fbe2f483-796b-48a0-8aba-eac112c1f658.png)   
   


 <br><br><hr>

### 2.2 Injection

#### 의존성 주입을 위한 캡슐화
 SamsungTV에 SonySpeaker 의존 주입 -> 기본 스피커 사용이 아닌 소니의 스피커를 사용.   
 
 기존의 기능에서 객체를 생성하여 사용.

```java
public class SamsungTV implements TV{
	
	private SonySpeaker speaker;
	
	public SamsungTV() {
		System.out.println("SamsungTV 객체 생성");
	}

	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다.");
	}
	
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}
	
	public void volumeUp() {
		speaker = new SonySpeaker();
		speaker.volumeUp();
//		System.out.println("SamsungTV---소리 올린다.");
	}
	
	public void volumeDown() {
		speaker = new SonySpeaker();
		speaker.volumeDown();
//		System.out.println("SamsungTV---소리 내린다.");
	}
	
}
```

**결과 화면**    
![image](https://user-images.githubusercontent.com/84966961/130010528-9edfa7c2-dc8b-46bd-9e65-a629f4b24bfe.png)



 <br><br><hr>

#### 객체의 생성자 의존 주입

 다음 SamsungTV의 코드를 보자.   
 
 만약 다른 브랜드로 스피커를 바꾸게 되면 volume에 관련된 모든 정보들을 바꾸어 줘야 하므로 생성자를 생성하여 speaker를 한번에 변경가능하도록 만들어 준다.   
    
 하지만 우리는 Spring Container를 활용하여 Bean의 생성과 삭제에 대한 관여를 하지 않을 수 있다.

```java
	public void volumeUp() {
//		System.out.println("SamsungTV---소리 올린다.");
		speaker = new SonySpeaker();
		speaker.volumeUp();
		// 다른 브랜드의 스피커를 사용하게 되면 Volume 관련 메소드를 모두 바꾸어 주어야함.
	}
```
    
Bean 매핑 내부에 sony를 constructor의 매개변수(arg)에 대해 넣어주면 생성자 의존 주입이 된다.    
   
매개 변수는 여러개 넣을 수 있으며 기본형인 경우에는 value 속성에 값을 넣어주면 된다.   

```xml
	<bean id="tv" class="polymorphsim.SamsungTV">   
		<constructor-arg ref="sony"></constructor-arg>
		<constructor-arg value="2700000"></constructor-arg>
	</bean>
	
	<bean id="sony" class="polymorphsim.SonySpeaker">
```




 <br><br><hr>

#### Speaker의 표준화

 기존 SonySpeaker 클래스를 이용한 Speaker interface 생성    

![image](https://user-images.githubusercontent.com/84966961/130013337-248d3195-a91d-4459-b30d-4cbd2553d510.png)   
   
![image](https://user-images.githubusercontent.com/84966961/130013390-25604a74-881b-4255-a5b1-67aa6989608e.png)   

![image](https://user-images.githubusercontent.com/84966961/130013826-5f01f881-3691-4ccc-94a4-a01b689af21f.png)
   
<br><br><hr>   
   
#### applespeaker 추가   

![image](https://user-images.githubusercontent.com/84966961/130013947-7319ed29-8467-44e1-939e-a138f28d8de5.png)   

 -> 이제는 만약 AppleSpeaker를 사용하고 싶다면 다음코드처럼 sony를 apple로 바꿔주면 된다.

```xml
	<bean id="tv" class="polymorphsim.SamsungTV">   
		<constructor-arg ref="apple"></constructor-arg>
		<constructor-arg value="2700000"></constructor-arg>
	</bean>
	
	<bean id="sony" class="polymorphsim.SonySpeaker">
```


 <br><br><hr>

   
#### setter를 이용한 매개변수 지정 : 의존성 주입     

기존의 다중 매개변수를 가진 생성자들을 모두 없애면 다음과 같이 xml파일에 빨간줄이 가게 된다.    

![image](https://user-images.githubusercontent.com/84966961/130017083-f004d15b-dbed-4a40-b26d-0a0de209946e.png)      
   
<br><br>
Speaker와 Price를 위한 Setter를 만들고 다음과 같이 property 속성을 만든다.     
   
![image](https://user-images.githubusercontent.com/84966961/130017359-1b0c36d2-18f4-4089-abab-25e3e1f803fe.png)   

<br><br>

-> **이런 과정을 거치게 되면 단순히 매개변수가 들어가는 생성자를 만들지 않고도 Setter를 이용하여 의존성 주입을 할 수 있다.**

 <br><br><hr>

### 2.3 CollectionBean Injection

CollectionBean을 이용한 주입에 대해서 알아보자.   

 <br><br><hr>

#### 컬렉션빈을 위한 코드 작성

 1. 먼저 컬렉션을 담는 빈을 만들어준다.

```java
import java.util.List;

public class CollectionBean {

	private List<String> addressList;

	public List<String> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
}
```
<br><br>   

2. applicationContext.xml 에 list의 값을 정의해준다.

```xml
	<bean id="collectionBean" class="com.springbook.iod.injection.CollectionBean">
		<property name="addressList">
			<list>
				<value>서울시 영등포구 여의도동</value>
				<value>서울시 영등포구 영등포본동</value>
				<value>서울시 영등포구 신길동</value>
			</list>
		</property>
</bean>
```


 <br><br><hr>

#### 컬렉션빈 사용(List, Set, Map)

```xml
	<!-- CollectionBean 사용 -->
	
	<bean id="collectionBeanList" class="com.springbook.iod.injection.CollectionBean">
		<property name="addressList">
			<list>
				<value>서울시 영등포구 여의도동</value>
				<value>서울시 영등포구 영등포본동</value>
				<value>서울시 영등포구 신길동</value>
			</list>
		</property>
	</bean>

	<bean id="collectionBeanSet" class="com.springbook.iod.injection.CollectionBean">
		<property name="addressSet">
			<set value-type="java.lang.String">
				<value>서울시 영등포구 여의도동</value>
				<value>서울시 영등포구 영등포본동</value>
				<value>서울시 영등포구 신길동</value>
			</set>
		</property>
	</bean>
	
	<bean id="collectionBeanMap" class="com.springbook.iod.injection.CollectionBean">
		<property name="addressMap">
			<map>
				<entry>
					<key>
						<value>고길동</value>
					</key>
					<value>서울시 영등포구 여의도동</value>
				</entry>
				<entry>
					<key>
						<value>마이클</value>
					</key>
					<value>서울시 영등포구 영등포본동</value>
				</entry>
			</map>
		</property>
	</bean>
```

 <br><br>
 
**CollectionBeanClient.java**
```java
	public static void main(String[] args) {
		// Factory
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		// List
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBeanList");

		List<String> addressList = bean.getAddressList();

		for (String address : addressList) {
			System.out.println(address);
		}

		// Set
		bean = (CollectionBean) factory.getBean("collectionBeanSet");

		Set<String> addressSet = bean.getAddressSet();

		for (String address : addressSet) {
			System.out.println(address);
		}

		// Map
		bean = (CollectionBean) factory.getBean("collectionBeanMap");

		Map<String, String> addressMap = bean.getAddressMap();
		Set<String> keySet = null;

		if (addressMap != null) {
			keySet = addressMap.keySet();
		}

		if (keySet != null) {
			// KeySet 사용
			int num = 1;
			for (String key : keySet) {
				System.out.println(num + "번째 ");
				System.out.print("key : " + key);
				System.out.print("value : " + addressMap.get(key));
				System.out.println();
				num++;
			}
			
			// EntrySet 사용
			for (Entry<String, String> entrySet : addressMap.entrySet()) {
				System.out.println(entrySet.getKey()+" : "+entrySet.getValue());
			}
		}
	}
```

 <br><br>
 
**결과 화면**   

![image](https://user-images.githubusercontent.com/84966961/130028478-60313475-f25d-4c06-a507-051fc5119c2d.png)



 <br><br><hr>

#### Properties 사용

```xml
	<!-- Properties 사용 -->

	<bean id="collectionBeanProperties" class="com.springbook.iod.injection.CollectionBean">
		<property name="addressProperties">
			<props>
				<prop key="고길동">서울시 영등포구 여의도동</prop>
				<prop key="마이클">서울시 영등포구 영등포본동</prop>
			</props>
		</property>
	</bean>
```

 <br><br>
 
**CollectionBeanClient.java**
```java
	public static void main(String[] args) {
		// Factory
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");

		// Properties
		bean = (CollectionBean) factory.getBean("collectionBeanProperties");
		
		Properties addressProperties = bean.getAddressProperties();
		
		for(String key : addressProperties.stringPropertyNames()) {
			System.out.println(String.format("키 : %s, 값 : %s", key, addressProperties.get(key)));
		}
	}
```

 <br><br>
 
**결과 화면**   

![image](https://user-images.githubusercontent.com/84966961/130032061-8289678b-5556-48a0-8462-6b81254b496b.png)



 <br><br><hr>


