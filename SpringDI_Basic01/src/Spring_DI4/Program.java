package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {

	public static void main(String[] args) {

		/*
		 * 
		 * 1. SpringFramework가 제공하는 컨테이너 안에 객체를 생성(메모리 공안에 IOC컨테이너가 있다) 2. 개발자는 이런 컨데이터를
		 * 만들고 그 메모리에 필요한 객체를 생성하고 조립(주입 == DI), 소멸시킨다
		 * 
		 * NewRecordView view = new NewRecordView(); NewRecord record = new NewRecord();
		 * 
		 * view.setRecord(record);
		 * 
		 * Spring으로 xml에서 설정해줬으므로 이 코드는 이제 필요가 없다
		 */

		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		// 저장공간이 컨테이너를 만들고 그 다음 xml파일을 read하기 시작한다
		// 그 다음에 컨테이너 안에 객체가 생성되고 주입하는 과정을 실행한다 - 그게 저 윗줄의 코드

		
		// 컨테이너 안에서 필요한 객체만 골라 이용하면 된다
		// 레고박스 안에 만들어진 블럭들이 있고 원하는 블럭을 가지고 와서 이용하는 것이 바로 아래의 코드
		RecordView view = (RecordView) context.getBean("view"); // id 값으로 뽑아낸다
		// view라는 id 값을 가진 객체를 가져와서 사용하겠다는 뜻
		// 그런데 bean은 온갖 타입의 객체를 담아야하기 때문에 특정타입으로 지정되지 않았다 그래서 뽑아낸 다음
		// 자기 타입으로 다운캐스팅 해주어야 한다		
		
		 view.input();
		 view.print();
		
		// 그런데 RecordView view = (RecordView) context.getBean("view");
		// 이렇게 한 줄 쓰고 실행하면 다음과 같은 예외가 발생한다
		// Caused by: java.lang.ClassNotFoundException:org.apache.commons.logging.LogFactory
		// 위의 작업을 하려면 LogFactory 라이브러리가 필요하고 그게 spring-framework-3.0.2.RELEASE-dependencies 버전에 있다
		// properties의 buildPath에서 추가해줄것

	}
}
