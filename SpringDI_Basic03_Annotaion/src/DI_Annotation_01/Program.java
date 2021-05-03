package DI_Annotation_01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Program {

	public static void main(String[] args) {

			/*
			MonitorViewer viewer = new MonitorViewer();
			Recorder recorder = new Recorder();
			
			viewer.setRecorder(recorder);
			System.out.println(viewer.getRecorder());
			*/
		
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:DI_Annotation_01/DI_Annotation_01.xml");
		
		MonitorViewer viewer = context.getBean("monitorViewer",MonitorViewer.class);
		System.out.println(viewer.getRecorder());
		
		//위의 코드를 실행하니
		//Caused by: java.lang.ClassNotFoundException: org.springframework.aop.TargetSource
		//이런 에러가 발생했는데, 필요한 jar파일이 없기 때문이다(aop)


					

	}

}
