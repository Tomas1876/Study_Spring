package DI;

// vo, dto, domain의 의미를 갖는 클래스(데이터를 담을 수 있는 클래스)
public class NewRecord {
	
	private int kor;
	private int eng;
	private int math;
	
	public NewRecord() {
		
	}

	public NewRecord(int kor, int eng, int math) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}
	
	
	//필요에 따라서는 함수를 추가 구현할 수도 있다 위에 있는 건 그냥 기본요소일 뿐
	public int total(){
		return this.kor+this.eng+this.math;
	}
	public float avg() {
		return total() / 3.0f;
	}
	
	

}
