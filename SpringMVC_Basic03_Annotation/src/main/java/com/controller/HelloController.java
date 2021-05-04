package com.controller;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
/*
  
	implements Controller하고 HandleRequest를 쓰는 방식은 
	서비스의 요청 갯수만큼 controller가 필요하다는 단점이 있다
	대안은 바로 Annotation이다
	
public class HelloController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
*/

@Controller
public class HelloController {
	public HelloController() {
		System.out.println("HelloController 생성자 호출");
	}
	
	@RequestMapping("/hello.do")   //<a href="hello.do"></a>
    public ModelAndView hello() {
    		System.out.println("[hello.do method call]");
    		ModelAndView  mv = new ModelAndView();
    		mv.addObject("greeting",getGreeting());
    		mv.setViewName("Hello");
    	
    	return mv;
    }
	
	private String getGreeting() {
		
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		
		String data ="";
		if(hour >= 6 && hour <= 10) {
			data="학습 시간";
		} else if(hour >= 11 && hour <=13) {
			data="배고픈 시간";
		} else if(hour >= 14 && hour <=18) {
			data="졸린 시간";
		} else {
			data="이제는 우리가 헤어져야할 시간";
		}
		return data;
	}
	
}