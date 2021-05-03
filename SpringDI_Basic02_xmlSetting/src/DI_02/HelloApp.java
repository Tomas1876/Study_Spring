package DI_02;

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
		
		MessageBean messagebeean = new MessageBean_kr();		
		messagebeean.sayHello("hong");

	}

}


