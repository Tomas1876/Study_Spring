package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import service.JoinService;

@RestController
public class AjaxController {
	
	@Autowired
	private JoinService service;
	
	//비동기 아이디 중복체크
	@RequestMapping(value = "/joinus/idcheck.do", method = RequestMethod.POST)
	public String idCheck(@RequestParam("userid") String userid) {
		System.out.println("THis is idcheck.do");
		int result = service.idCheck(userid);
		String msg ="true";
		
		System.out.println("result : " + result);
		if(result > 0) {
			msg="false";
		}

		//System.out.println("model"+model);	
		System.out.println("meg : " + msg);
	   return msg;
	}

}
