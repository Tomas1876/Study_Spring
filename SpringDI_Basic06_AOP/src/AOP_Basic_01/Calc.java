package AOP_Basic_01;

import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;
import org.apache.commons.logging.Log;

public class Calc {
	
	/*
		간단한 사칙연산 프로그램
		-주관심(업무) : 사칙연산(ADD, MUL) ->기능(함수) 구현
		-보조관심(공통관심) : 연산에 걸리는 시간
		-log 출력(console에 read한 글자 출력)
	 */
	
	public int Add(int x, int y) {
		//현재 동작하는 함수의 정보를 LogFactory라는 클래스가 갖게 하고 그 주소값을 log변수에 담는
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		
		sw.start();
		log.info("타이머 시작");
		
		int result = x+y;
		
		sw.stop();
		log.info("타이머 종료");
		log.info("Time log Method : Add");
		log.info("Time log Method : " + sw.getTotalTimeMillis());
		
		
		return result;
		
	}
	
	public int Mul(int x, int y) {
		
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		
		sw.start();
		log.info("타이머 시작");
		
		int result = x*y;
		
		sw.stop();
		log.info("타이머 종료");
		log.info("Time log Method : MUL");
		log.info("Time log Method : " + sw.getTotalTimeMillis());
		return result;
	}

}
