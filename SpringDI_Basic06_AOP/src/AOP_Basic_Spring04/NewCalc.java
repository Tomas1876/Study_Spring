package AOP_Basic_Spring04;

public class NewCalc implements Calc {

	@Override
	public int ADD(int x, int y) {
		//보조(공통)업무 cross-cutting-concern
		
		int sum = x+y; //주업무(core-concern)
		
		//보조(공통)업무 cross-cutting-concern
		//이렇게 보조업무가 주업무를 감싸고 있는 것을 arround-advice라고 한다
		
		return sum;
	}

	@Override
	public int MUL(int x, int y) {
		//보조(공통)업무 cross-cutting-concern
		
		int mul = x*y;
		
		//보조(공통)업무 cross-cutting-concern
		
		return mul;
	}

	@Override
	public int SUB(int x, int y) {
		//보조(공통)업무 cross-cutting-concern
		
		int sub = x-y;
		
		//보조(공통)업무 cross-cutting-concern
		
		return sub;
	}

}
