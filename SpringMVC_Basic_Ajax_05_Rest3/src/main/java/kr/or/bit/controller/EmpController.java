package kr.or.bit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sun.org.slf4j.internal.Logger;

import kr.or.bit.dto.Emp;

@RestController
@RequestMapping("/empdata")
public class EmpController {
	
	/*
	  
	 예시) Http  method 종류
	GET        ( /board/100 )       >> 100번 게시물 조회
	POST     ( /board  + 데이터)      >> 게시물 등록
	DELETE ( /board/100)            >> 100게시물 삭제
	PUT       ( /board/100 + 데이터   >> 100번 게시물 수정
	PATCH   ( /board/100 + 데이터     >> 100번 게시물 수정
		-->> uri 값이 같아도 메서드 종류에 따라 의미가 달라짐 
	 
	 */
	
	//사원등록(POST) /empdata + json 데이터로 uri가 들어왔을 때 데이터는 객체로 받을 수 있다 
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody Emp emp){
		
		try {	
			//등록
			System.out.println("등록 성공");
			return new ResponseEntity<String>("Emp data register success", HttpStatus.OK); //200번대
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Emp data register fail", HttpStatus.BAD_REQUEST); //400번대
		}

	}
	
	//PUT  ( /board/100 + 데이터   >> 100번 게시물 수정 - 이렇게 들어올 경우 board/뒤의 100은 value로 받는고 @@PathVariable을 사용한다
	@RequestMapping(value="/{no}", method=RequestMethod.PUT) //혹은 PATCH
	public ResponseEntity<String> register(@PathVariable("no") int no, @RequestBody Emp emp ){
		
		//ResponseEntity는 필수가 아니라 선택사항
		
		try {
		      //등록
		      System.out.println("수정요청 사번 : " + no);
		      System.out.println(emp.toString());
		      System.out.println("변경성공");
		      return new ResponseEntity<String>("emp data register update success", HttpStatus.OK);  //200
		}catch (Exception e) {
		     return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);  //400
		}
	}
	
	
	//GET        ( /empdata/100 )                //select empno , ename from emp where empno=100
	//만약 empdata/뒤에 여러 데이터가 들어온다면? 이런 식으로 http://localhost:8090/bit/empdata/100/1/2
	//그럴 땐 RequestMapping을 아래와 같이 사용할 수 있다
	@RequestMapping(value="/{no}/{page}/{index}" , method = RequestMethod.GET)
	public Map<String ,Object> getEmp(@PathVariable("no") int no , @PathVariable("page") int page , @PathVariable("index") String index) {
		return  new HashMap<String, Object>();
	} 
	


}
