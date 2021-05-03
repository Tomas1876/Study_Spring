package DI_03_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		/*
		 * //영문 버전 MessageBean_en messagebeean_en = new MessageBean_en();
		 * messagebeean_en.sayHello("hong");
		 * 
		 * //한글 버전 MessageBean_kr messagebeean_kr = new MessageBean_kr();
		 * messagebeean_kr.sayHello("hong");
		 * 
		 * 이렇게 해도 되지만 효율적이진 않다 이왕 인터페이스도 만들었는데 활용하자
		 */
		
		/* 스프링을 이용하면 이렇게 직접 객체를 생성할 필요 없다
		 * MessageBean messagebeean = new MessageBean_kr();
		 * messagebeean.sayHello("hong");
		 */
		
		// spring 컨테이너를 생성하고 생성된 컨테이너 객체를 조립한다
		// 컨테이너를 생성하는 방법은 아주 다양핟
		
															//여기서는 폴더 하위를 .이 아니라 /로 표기한다
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_03_Spring/DI_03.xml");
		
		//Generic을 사용하면 형변환을 할 때 이점이 있다
		//원래 ApplicationContext는 최상위 객체고 getBean은 무조건 오브젝트 타입을 리턴하기 때문에 다운캐스팅을 해야하는ㄷ
		//GenericXmlApplicationContext라는 클래스로 받아주면, getBean함수에 파라미터를 MessageBean.class라고 줘서 자동으로 형변환해준다
		MessageBean message = context.getBean("messagebean", MessageBean.class); //getBean함수는 무조건 오브젝트 타입으로 리턴한다
		
		message.sayHello("hong");

	}

}


