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
			return "join.jsp";
		}
		
		//회원가입 처리(POST)
		@RequestMapping(value = "join.htm" , method = RequestMethod.POST)
		public String join(Member member) {
			System.out.println(member.toString());
			
			try {
				memberdao.insert(member);
			} catch (Exception e) {
				
				e.printStackTrace();
			} 
			return "redirect:/index.htm";
			
			//저 경로에서 /의 존재여부는 중요하다
			//만약 /없이 return redirect:index.htm 라고만 작성하면
			//	http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_JdbcTemplate/joinus/join.htm
			// 이 풀 경로에서
		    //   http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_JdbcTemplate/joinus/index.htm	
			// 이렇게 joinus 폴더 하위 경로만 바뀐다
				
		    //   /index.htm 라고 /를 추가해서 rediredct를 걸어주면 (root경로)
			//   http://localhost:8090/SpringMVC_Basic06_WebSite_Annotation_JdbcTemplate/index.htm
			// 이렇게 된다
		}
	
}
