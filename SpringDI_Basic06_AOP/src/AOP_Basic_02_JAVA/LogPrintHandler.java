package AOP_Basic_02_JAVA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

//보조 업무(공통관심)를 가지고 있는 클래스
//실제 함수를 대신해서 처리할 수 있는 기능을 가지는 함수(대리함수, invoke)

//invoke란 여러 개의 함수를 대리한다는 뜻 implements InvocationHandler 필수

public class LogPrintHandler implements InvocationHandler{
	
	private Object target; //실객체의 주소값
	
	public LogPrintHandler(Object target) {
		System.out.println("logHandler 생성자 호출");
		this.target = target;
		//이렇게 LogPrintHandler이 가진 target이라는 멤버필드에
		//주업무를 가진 객체의 주소값을 주입할 수 있다
		
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		System.out.println("invoke 함수 호출");
		System.out.println("method 함수명 : " + method);
		System.out.println("method parameter : " +Arrays.toString(args));
		
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		
		sw.start();
		log.info("타이머 시작");
		
		//주업무 실행(실객체의 진짜함수를 호출해야 한다 - 주객체의 주함수인 ADD, MUL, SUB를 method라는 파라미터로 받아 호출)
		int result = (int)method.invoke(this.target, args);
		
		sw.stop();
		log.info("타이머 종료");
		log.info("Time log Method : "+method);
		log.info("Time log Method : " + sw.getTotalTimeMillis());

		return result;
	}

}
