package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {

	
	@RequestMapping("/cookie/make.do")
	public String make(HttpServletResponse  response) {
		  response.addCookie(new Cookie("auth", "1004"));  //jsp & servlet 동일
		  return "cookie/CookieMake";
	}
	
	@RequestMapping("/cookie/view.do")
	public String view(@CookieValue(value="auth" , defaultValue = "1007")  String auth) {
		//public String view(HttpServletRequest request) 이것도 가능하지만 여기서는 어노테이션 사용
			System.out.println("클라이언트에서 read 한 쿠키값 : " + auth);
			return "cookie/CookieView";
	}
	
	
	
}
