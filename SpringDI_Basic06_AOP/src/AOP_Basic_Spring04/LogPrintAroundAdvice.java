package AOP_Basic_Spring04;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

//중간에 행위를 가로채야 하기 때문에 그럴 수 있도록 해주는 MethodInterceptor를 구현해야 한다
public class LogPrintAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {

		System.out.println("Around Advice invoke Start");
		System.out.println("method : " + method);
		
		//보조업무
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		
		sw.start();
		log.info("타이머 시작");
		
		//주업무
		Object result = method.proceed(); //여기 들어온 실객체의 함수를 실행하는 함수 proceed()
										  //실객체가 무슨 타입으로 들어올지 모르니 Object 타입으로 받아준다
		
		//보조업무
		sw.stop();
		log.info("타이머 종료");
		log.info("Time log Method : "+method);
		log.info("Time log Method : " + sw.getTotalTimeMillis());

		
		return result;
	}

}
