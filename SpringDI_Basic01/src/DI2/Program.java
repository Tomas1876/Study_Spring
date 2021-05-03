package DI2;

public class Program {
	
	public static void main(String[] args) {
		
		NewRecordView view = new NewRecordView();

		//만든다고 바로 가져오는 것이 아니고 필요해지면 불러온다
		NewRecord record = new NewRecord(100, 50, 50);
		view.setRecord(record); //injection, dependency
		view.print();
		
		
	}
}
