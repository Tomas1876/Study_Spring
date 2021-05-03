package DI3;

import java.util.Scanner;

public class NewRecordView implements RecordView {
	
	//DI에서는 생성자를 통해 주소값을 할당했다
	private NewRecord record;//인터페이스 타입을 사용
		
	public void setRecord(Record record) { //파라미터가 인터페이스타입
		this.record = (NewRecord)record;
	}
	
	// 나는 네가 필요해! 라고 하는 것 중 두 가지 방법을 보고 있다.
	//1. 생성자를 통해서 필요한 객체를 생성 또는 주입하는 방법 - DI 패키지
	//2. 함수(setter)를 통해서 필요한 객체를 주입 - DI2 패키지 ->> 필요할 때만 주입할 수 있어 유연하다
	public void print() {
		
		System.out.println(record.total() + " / " + record.avg());
		
	}

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("kor : " );
		record.setKor(sc.nextInt());
		
		System.out.println("eng : " );
		record.setEng(sc.nextInt());
		
		System.out.println("math : " );
		record.setMath(sc.nextInt());
	}
	
	
}
