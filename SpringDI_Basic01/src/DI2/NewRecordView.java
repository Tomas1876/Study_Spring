package DI2;

public class NewRecordView {
	
	//DI에서는 생성자를 통해 주소값을 할당했다
	//여기서는 setter를 이용할 것!
	private NewRecord record;
		
	public void setRecord(NewRecord record) { //record의 setter 함수
		this.record = record;
	}
	
	// 나는 네가 필요해! 라고 하는 것 중 두 가지 방법을 보고 있다.
	//1. 생성자를 통해서 필요한 객체를 생성 또는 주입하는 방법 - DI 패키지
	//2. 함수(setter)를 통해서 필요한 객체를 주입 - DI2 패키지 ->> 필요할 때만 주입할 수 있어 유연하다
	public void print() {
		
		System.out.println(record.total() + " / " + record.avg());
		
	}
	
	
}
