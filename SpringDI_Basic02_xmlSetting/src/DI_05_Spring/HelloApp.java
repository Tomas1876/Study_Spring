package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		/*
		//java 코드
		MyBean mybean = new MyBean();
		MyBean mybean2 = new MyBean("hong");
		MyBean mybean3 = new MyBean();
		
		System.out.println("mybean : " + mybean);
		System.out.println("mybean2 : " + mybean2);
		System.out.println("mybean3 : " + mybean3);
		//new 할 때마다 객체가 새로 생성되므로 위의 셋은 주소가 다르다
		
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		System.out.println("single : " + single);
		System.out.println("single2 : " + single2);
		//하지만 싱글톤은 하나의 객체를 공유하기 때문에 주소값이 같다!
		*/
		
		//Spring 컨테이너 생성하고 xml파일 read한 것
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml");
		
		//그리고 xml파일을 마싱해서 객체를 생성하고 조립, 소멸시킨다
		//컨테이너 안에서 필요한 객체를 얻어서 사용하면 된다
		
		MyBean mybean = context.getBean("mybean",MyBean.class);
		MyBean mybean2 = context.getBean("mybean",MyBean.class);
		MyBean mybean3 = context.getBean("mybean",MyBean.class);
		
		System.out.println("주소값 : " + mybean);
		System.out.println("주소값 : " + mybean2);
		System.out.println("주소값 : " + mybean3);
		
		
		//getBean 함수!
		//1. return type Object(타입에 맞는 casting)
		//2. 호출시 새로운 객체를 만들지 않는다(new 하지 않는다)
		//	 Spring 컨테이너 안에 들어있는 객체들의 타입은 default로 singleton이다
		//	 즉 어플리케이션이 끝나지 않는 한 메모리에 남아있는다
		//	 단 예외적으로 getBean()이 객체를 생성하게 할 수 있는 경우가 있긴 하다(거의 쓰지 않는다)
		MyBean mybean4 = context.getBean("mybean2", MyBean.class);
		System.out.println("생성자 사용 : " + mybean4);
		
		//Single
	}

}
