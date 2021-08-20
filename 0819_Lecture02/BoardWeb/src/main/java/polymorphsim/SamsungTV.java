package polymorphsim;

public class SamsungTV implements TV{
	
	private Speaker speaker;
	private int price;
	
	public SamsungTV() {
		System.out.println("SamsungTV(1) 객체 생성");
	}

//	public SamsungTV(Speaker speaker) {
//		super();
//		System.out.println("SamsungTV(2) 객체 생성");
//		this.speaker = speaker;
//	}
//
//	public SamsungTV(Speaker speaker, int price) {
//		super();
//		System.out.println("SamsungTV(3) 객체 생성");
//		this.speaker = speaker;
//		this.price = price;
//	}

	public void setSpeaker(Speaker speaker) {
		System.out.println("setSpeaker() 호출됨.");
		this.speaker = speaker;
	}

	public void setPrice(int price) {
		System.out.println("setPrice() 호출됨.");
		this.price = price;
	}

	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다.(가격 : "+ price + ")");
	}
	
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}
	
	public void volumeUp() {
//		System.out.println("SamsungTV---소리 올린다.");
//		speaker = new SonySpeaker();
		speaker.volumeUp();
		// 다른 브랜드의 스피커를 사용하게 되면 Volume 관련 메소드를 모두 바꾸어 주어야함.
	}
	
	public void volumeDown() {
//		speaker = new SonySpeaker();
		speaker.volumeDown();
//		System.out.println("SamsungTV---소리 내린다.");
	}
	
}
