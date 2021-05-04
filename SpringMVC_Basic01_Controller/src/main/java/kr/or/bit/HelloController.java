package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
public class HelloController  implements  Controller {
	public HelloController() {
		System.out.println("HelloController 객체 생성");
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("HelloController요청 실행 :  handleRequest  함수 실행");
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("name","bituser"); //reauest.setAttrubute("name","bituser");
		mav.setViewName("Hello");
		
		//ModelAndView 객체의 주소값을 리턴하는데
		//이건 Resolver가 가져간다
		//InternalResourceViewResolver에 의해 view단의 주소가 조합된다
		// /WEB-INF/views/ + Hello + .jsp = view의 주소
		//mav.setViewName("Hello"); 는 마치 spring을 사용하지 않은 기존 MVC2 패턴에서
		//forwars.setPath("/WEB-INF/views/Hello.jsp"); 와 같은데
		//spring을 사용하면 xml에서 설정하고, 여기선 이름만 적을 수 있는 것
		return mav;
	}

}
