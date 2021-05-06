package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {
	
	/*
	WEB에서 Client가 전송한 데이터를 서버에서 받아 DB와 연동하는 것에는 몇 가지 방법이 있다
	​
	1. 전통적인 방법 (request 객체 사용)
	public void searchInteral( HttpServletRequest request ) {
		String id = request.getParameter("id")
	}
	​
	2. DTO 객체를 통한 전달 방법(게시판 데이터 , 회원가입 데이터)	​
	public void searchInteral(MemberDTO member ) {
	// input name="id"
	//선행조건 : insertmember.do?id=hong&name=김유신&age=100 
	//MemberDTO >> private id , private name , private age 가지고 있어야 한다
	​
	//MemberDTO 자동 생성
	//setter 자동 주입
	//view 까지 자동으로 forward >> view 단에서 memberDTO key 값을 사용 
	}
	​
	3. 편하게 사용하기(하지만 유효성 체크가 어렵다)
	public void serarchInternal(int page , int size){
	 	**넘어오는 parameter [이름]이 함수의 [parameter 명]과 같다면 데이터 받는다
	 	**internal.do?page=100&size=10
	}​
	​
	4. @RequestParam Annotation 사용하기
	3번 편하게 사용하기 단점 극복 (유효성 체크)
	4.1 유효성 처리
	4.2 기본값 설정
	----0506 오전에 여기까지 함 아래는 REST API수업 때 배울 것
	​
	​
	5. REST API (method : GET, POST , PUT , DELETE )
	@PathVariable /member/{memberid} >> /member/100 또는 /member/1004
	100또는 1004라는 값을 paramemter 사용할 수 있다
	*/
	
	@RequestMapping("/search/external.do")
	//public ModelAndView  searchInternal(String query , int p) {
	public ModelAndView  searchInternal(@RequestParam(value="query", defaultValue="bit") String query, 
			                            @RequestParam (value="p", defaultValue="10") int p) {	
		System.out.println("param query : " + query);
		System.out.println("param p : " +  p);
		
		return new ModelAndView("search/internal");     // public String    return 값이  view주소 
	}

}
