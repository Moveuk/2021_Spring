package polymorphsim;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TVUser {

	public static void main(String[] args) {
		// Stage 1 : 객체의 생성
//		SamsungTV stv = new SamsungTV();
//		stv.powerOn();
//		stv.volumeUp();
//		stv.volumeDown();
//		stv.powerOff();
//	
//
//		LgTV lgtv = new LgTV();
//		lgtv.turnOn();
//		lgtv.soundUp();
//		lgtv.soundDown();
//		lgtv.turnOff();

		
		
		// Stage 2 : 다형성을 이용한 표준화
//		TV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
//		
//		tv = new LgTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		// -> factory 패턴으로 바꾸면 Spring container가 하는 역할임
		
		
		
		// Stage 3 : Factory 패턴 도입	
//		BeanFactory factory = new BeanFactory();
//		
//		Scanner sc = new Scanner(System.in);
//		
//		System.out.println("tv이름 입력 : ");
//		String tname = sc.nextLine();
//		
//		TV tv = (TV) factory.getBean(tname);
//		tv.powerOn();
//		tv.volumeUp();
//		tv.volumeDown();
//		tv.powerOff();
		
		
		
		// Stage 4 : Spring XML에 빈 매핑 후 사용
		// 자동으로 객체를 생성하여 불러와줌.
		
		// Spring Library import 필요
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		TV tv = (SamsungTV) factory.getBean("tv");	// new 객체 생성됨
//		TV tv2 = (SamsungTV) factory.getBean("tv");	// new 객체 생성됨
//		TV tv3 = (SamsungTV) factory.getBean("tv");	// new 객체 생성됨
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
		factory.close();    // 생성되었던 객체 닫음.
	}
}
