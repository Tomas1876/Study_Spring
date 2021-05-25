package kr.or.bit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.bit.dto.Emp;

@RestController
@RequestMapping("/sample")
public class EmpRestController {

	@RequestMapping(value="/hello")
	public String gsayHello() {
		
		return "Hello World";
	}
	
	@RequestMapping("/empvo")
	public Emp empvo() {
		Emp emp = new Emp();
		emp.setEmpno(2000);
		emp.setEname("홍길동");
		return emp;
	}
	
	@RequestMapping("/emplist")
	public ResponseEntity<List<Emp>> sendList(){
		
		List<Emp> list = new ArrayList<Emp>();
		for(int i = 0; i < 3; i ++) {
			Emp emp = new Emp();
			emp.setEmpno(i);
			emp.setEname("아무개");
			
			list.add(emp);
		}
		
		return new ResponseEntity<List<Emp>>(list, HttpStatus.NOT_FOUND); //실제로는 정상건인데 의도적으로 NOT_FOUND 사용해 에러 발생시키는 것
	}
	
	
	@RequestMapping("/emplist2")
	public List<Emp> sendList2(){
		
		List<Emp> list = new ArrayList<Emp>();
		for(int i = 0; i < 3; i ++) {
			Emp emp = new Emp();
			emp.setEmpno(i);
			emp.setEname("아무개");
			
			list.add(emp);
		}
		
		return list; //정상적으로 보내는 건
	}
	
}
