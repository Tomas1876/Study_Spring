package ncontroller;

import java.security.Principal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import vo.Member;
import service.MemberService;

@Controller
@RequestMapping("/joinus/")
public class MemberController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value="memberconfirm.do",method=RequestMethod.GET)
	public String memberConfirm(){
		return "joinus/memberConfirm";
	}
	
	@RequestMapping(value="memberconfirm.do",method=RequestMethod.POST)
	public String memberConfirm(
			@RequestParam("password") String rawPassword,
			Principal principal) throws ClassNotFoundException, SQLException {
		String viewpage="";
		
		//회원정보
		Member member = service.getMember(principal.getName());
		
		//DB에서 가져온 암호화된 문자열
		String encodedPassword = member.getPwd();
		
		System.out.println("rowPassword : "+rawPassword ); //입력값
		System.out.println("encodepassword : " + encodedPassword); //DB에 저장된 암호화된 값
		
		//입력값과 암호회된  값 매칭시키는 과정(matches 함수)
		boolean result = bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
		
		if(result){
			viewpage="redirect:memberupdate.do";
		}else{
			viewpage="redirect:memberconfirm.do";
		}
		
		return viewpage;
	}
	
	
	@RequestMapping(value="memberupdate.do", method=RequestMethod.GET)
	public String memberUpdate(Model model, Principal principal) throws ClassNotFoundException, SQLException {
		Member member = service.getMember(principal.getName());
		model.addAttribute("member", member);
		return "joinus/memberUpdate";
	}
	
	@RequestMapping(value="memberupdate.do", method=RequestMethod.POST)
	public String memberUpdate(Model model, Member member, Principal principal) throws ClassNotFoundException, SQLException {
		
		Member updatemember = service.getMember(principal.getName());
		
		updatemember.setName(member.getName());
		updatemember.setCphone(member.getCphone());
		updatemember.setEmail(member.getEmail());
		
		//암호화작업
		updatemember.setPwd(bCryptPasswordEncoder.encode(member.getPwd()));
		service.updateMember(updatemember);
		return "redirect:/index.do";
	}
}
