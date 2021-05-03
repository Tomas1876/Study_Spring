package DI;

public class NewRecordView {
	
	//NewRecordView는 NewRecord를 필요로 한다
	//NewRecordView는 NewRecord를 출력하는 클래스
	
	//NewRecord는 NewRecordView를 필요로 하고,
	//NewRecordView가 생겨야 NewRecord가 생길 수 있으므로 둘은 복합 연관이다
	private NewRecord record;

	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}
	
	public void print() {
		
		System.out.println(record.total() + " / " + record.avg());
		
	}
	
	
}
