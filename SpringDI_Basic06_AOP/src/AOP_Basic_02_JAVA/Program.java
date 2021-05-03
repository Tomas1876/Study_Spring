package AOP_Basic_02_JAVA;

import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {

		//1. 실객체의 주소
		Calc calc = new NewCalc();
		
		//2. 가짜를 생성
		Calc cal = (Calc)Proxy.newProxyInstance(calc.getClass().getClassLoader(), //실객체의 메타 정보(내부)
							calc.getClass().getInterfaces(), //실객체의 행위정보(인터페이스)
							new LogPrintHandler(calc)); //보조객체의 정보
		
		int result = cal.ADD(555,555);
		System.out.println("ADD result : " + result);
		
		result = calc.MUL(465,166);
		System.out.println("MUL result : " + result);
		

	}

}
