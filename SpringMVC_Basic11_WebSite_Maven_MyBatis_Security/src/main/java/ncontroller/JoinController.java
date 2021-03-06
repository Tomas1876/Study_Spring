package ncontroller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.MemberDao;
import vo.Member;

@Controller
@RequestMapping("/joinus/")
public class JoinController {
	
		private MemberDao  memberdao;

		@Autowired
		public void setMemberdao(MemberDao memberdao) {
			this.memberdao = memberdao;
		}
		
		//회원가입 페이지(GET)
		@RequestMapping(value = "join.htm" , method = RequestMethod.GET)
		public String join() {
			//return "join.jsp";
			return "joinus/join";
		}
		
		//회원가입 처리(POST)
		@RequestMapping(value = "join.htm" , method = RequestMethod.POST)
		public String join(Member member) {
			System.out.println(member.toString());
			//Member [userid=null, pwd=2, name=3, gender=남성, birth=, isLunar=Solar, cphone=7, email=8, habit=on, regDate=null]
			try {
			     	memberdao.insert(member);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			return "redirect:/index.htm";
			
		}
		
		//로그인요청
		@RequestMapping(value="login.htm", method=RequestMethod.GET)
		public String login() {
		
			return "joinus/login";
		}
		
		//로그인 처리(Spring 자동화 처리)
}
