package DI_04_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class HelloApp {

	public static void main(String[] args) {

		//java코드
		//MessageBeanImpl messagebean = new MessageBeanImpl("hong");
		
		//인터페이스로 사용하면 인터페이스가 가진 자원밖에 사용할 수 없어서 setGreeting 은 사용할 수 없다.
		//그래서 여기서 인터페이스를 사용하지 않은 것
		//messagebean.setGreeting("hello");
		//messagebean.sayHello();
		
		//스프링을 사용하면 일단 new로 객체 생성은 안 한다고 생각하자
		
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_04_Spring/DI_04.xml");
	      
	    MessageBean messagebean= context.getBean("m1", MessageBean.class);
	    messagebean.sayHello();

		
		
	}

}
