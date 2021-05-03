package DI_Annotation_02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonitorViewer {
	
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	//setter를 통해서 Recorder 객체의 주소를 자동으로 주입하겠다는 뜻
	//By Typoe이 컨테이너 안에 있다는 것을 전제로
	
	//@Autowired(required = true) default값 - 무조건 injection하겠다는 뜻
	//@Autowired(required = false) - 컨테이너 안에 원하는 타입이 없으면 안하겠다는 뜻
	@Autowired
	@Qualifier("recorder_1")
	//xml에서 설정한 <qualifier value="recorder_1"></qualifier>
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
	@Autowired
	@Qualifier("recorder_2")
	public void Gmethod(Recorder rec) {
		 System.out.println("Gmethod (rec) : " + rec);
	}


}
