package DI_Annotation_03;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

public class MonitorViewer {
	
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	// @Autowired는 type을 기준으로 주입한다
	@Resource(name="zz") //는 name이 기준 같은 타입의 객체가 여러 개 있더라도 name값으로 찾을 수 있다
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	


}
