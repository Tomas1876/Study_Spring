
//활용 : awt , swing , android (event 처리) :버튼 클릭 , 마우스 오버 , 

//익명 클래스, Anonymous Class

//클래스를 정의하지 않고 객체를 정의하는 방법 > 1회용 클래스 사용 > 재사용이 불가능한 클래스(객체를 1번 만드는 용도)

//이벤트 객체, 스레드 개체, 람다식, 스트림 등에서 사용.

//inner class - 클래스안에 클래스가 있는 것(java에서)
//코딩이 조금 불편하지만 안쪽에 있는 클래스가 바깥쪽 클래스의 자원을 사용할 수 있다는 장점이 있다
//단 사용하려는 바깥쪽 클래스의 자원의 접근자가 private이면 안된다
class OuterClass {
	public int pdata = 10;
	private int data = 30;

	class InnerClass {
		void msg() {
			System.out.println("outerclass data : " + data);
			System.out.println("outerclass data : " + pdata);
		}
	}
}

/////////////////////////////////////////////
abstract class Person2 { // 강제적 구현
	abstract void eat();
}

class Man extends Person2 {

	@Override
	void eat() {
		System.out.println("person2 의 eat 함수 재정의");
	}

}

//////////////////////////////////////////////
interface Eatable {
	void eat();
}

class Test {
	void method(Eatable e) {
		e.eat();
	}
}
/*
 * class TTT implements Eatable{
 * 
 * @Override public void eat() { System.out.println("aaaaaa");
 * 
 * }
 * 
 * } Test t = new Test(); t.method(new TTT());
 * 
 */

public class Ex10_InnerClass {
	public static void main(String[] args) {
		OuterClass outobj = new OuterClass();
		System.out.println("public :" + outobj.pdata);

		OuterClass.InnerClass innerobj = outobj.new InnerClass();
		innerobj.msg(); // outer 클래스에 대한 자원 접근 용이

		Man m = new Man();
		m.eat();
		Person2 p2 = m;
		p2.eat();

		Person2 p3 = new Man();
		p3.eat();
		
		
		
		// abstract class Person2 어차피 강제 구현을 목적으로 설계됐고
		// 추상클래스는 스스로 객체 생성이 불가능하다
		// Person2 상속하는 클래스 없이도 1회성으로 사용가능한 클래스는 없을까??
		// == 익명클래스(재사용 못하니까 이름도 필요없다)
		
		
		Person2 p4 = new Person2() { //한 번만 쓸거니까 굳이 상속 같은 걸 할 필요도 없이 바로 생성
			
			@Override
			void eat() {
				// TODO Auto-generated method stub
				System.out.println("익명 타입으로 구현");
			}
		};
		
		p4.eat();
		
		////////////////////////////////////////////////////////////////
		// Today POINT  주로 사용하는 방법
		Test t = new Test();
		//원래는 class Test implements Eatable{ eat() } 이런 식으로 해야 한다

		t.method(new Eatable() { //파라미터로 인터페이스 타입을 받아서 이 메서드를 사용할 때만 인터페이스를 구현한다
								 //한 번만 쓸 것이니 굳이 implements까지 해서 사용할 필요가 없다
			@Override
			public void eat() {
				
				System.out.println("일회성 자원으로 인터페이스를 직접 구현");
			}
		});
		
		////////////////////////////////////////////////////////////////
		
		/*
		 * t.method(new Eatable() {
		 * 
		 * @Override public void eat() { System.out.println("일회성 자원으로 인터페이스를 직접 구현");
		 * 
		 * } });
		 */

	}

}
