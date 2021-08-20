package polymorphsim;

//import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV{
	
	@Autowired
//	@Qualifier("apple")
//	@Resource(name="apple")
	private Speaker speaker;
	
	@Override
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
		
	}

	@Override
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
		
	}

	@Override
	public void volumeUp() {
//		System.out.println("LgTV---소리 올린다.");
		speaker.volumeUp();
		
	}

	@Override
	public void volumeDown() {
//		System.out.println("LgTV---소리 내린다.");
		speaker.volumeUp();
		
	}
}
