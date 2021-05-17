package kosta.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kosta.vo.Employee;


@Controller
public class AjaxController {

	//org.springframework.web.servlet.view.json.MappingJackson2JsonView
	//@Autowired
	//private View jsonview;
	
	@RequestMapping(value="response.kosta",method=RequestMethod.POST)
	public @ResponseBody Employee add(HttpServletRequest request, HttpServletResponse response)
	{
		System.out.println("Response ");
		
		Employee employee = new Employee();
		
		
		//request.getParameter를 사용하지 않고 
		//아래처럼 @RequestBody를 사용해서 객체로 받을 수도 있다
		employee.setFirstname(request.getParameter("firstName"));
		employee.setLastname(request.getParameter("lastName"));
		employee.setEmail(request.getParameter("email"));
		System.out.println(employee.getFirstname());
		return employee;  //{"firstname":"aa","lastname":"bb","email":"cc"}
	}
	
	@RequestMapping(value="response2.kosta",method=RequestMethod.POST)
	public @ResponseBody Employee add(@RequestBody Employee emp) //@RequestBody (비동기: 객체 형태로 받는다) 
	{
		System.out.println("response");
		System.out.println(emp.toString());
		
		return emp;
	}
	
	// 리턴타입이 Map도 가능하다
	@RequestMapping(value="response3.kosta",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> Detail() 
	{ 	
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("result", "data");
		map.put("hello", "world");
		return map;
	}
	// {"result":"data","hello":"world"}
	
}

/*
public @ResponseBody Map<String,String> add(HttpServletRequest request, HttpServletResponse response)
{
	Map<String,String> map = new HashMap<>();
	map.put("result","success");
	return map;
}
*/


