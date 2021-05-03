package DI_01;

public class HelloApp {

	public static void main(String[] args) {
		MessageBean messagebeean = new MessageBean();
		
		messagebeean.sayHello("hong");

	}

}

/*
	요구사항이 하나 들어왔다
	한글 버전과 영문 버전 두 개를 만들어달라!
	hong이라고 들어왔을 때
	1. 한글 버전 : 안녕 hong!
	2. 영문 버전 : hello hong!
	MessageBean_kr > 안녕 hong!
	MessageBean_en > hello hong!
	-근데 이렇게 하면 매번 다른 객체를 만들어야 한다
	그럼 MessageBean을 인터페이스로 만들어볼까?	
	
 
*/
