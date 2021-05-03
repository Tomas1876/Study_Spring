package DI_Annotation_01;

import org.springframework.beans.factory.annotation.Autowired;

public class MonitorViewer {
	
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	//setter를 통해서 Recorder 객체의 주소를 자동으로 주입하겠따는 뜻
	//By Typoe이 컨테이너 안에 있다는 것을 전제로
	@Autowired
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
	

}
