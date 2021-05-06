package ncontroller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;


@Controller
@RequestMapping("/customer/") //들어오는 모든 주소 앞에 공통적으로 붙는 부분 잡아주기
public class CustomerController {
	
	NoticeDao noticedao = new NoticeDao();
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}
	
	
	/*
		
		1. method 안에서 return type이 String : return값이 view의 주소
		
		2. public ModelAndView notices ... >> ModelAndView 객체 생성(데이터, 뷰단 설정해서 return)
		
		3. public String notices (Model model){ 함수가 실행시 내부적으로 Model 객체의 주소가 들어옴 }
									ㄴ 이 Model은 인터페이스
		
		
	 */
	
	
	
	@RequestMapping("notice.htm")
	public String notices(String pg, String f, String q, Model model) {

				//default 값 설정
				int page = 1;
				String field="TITLE";
				String query = "%%";
				
				if(pg != null && !pg.equals("")) {
					page = Integer.parseInt(pg);
				}
				
				if(f != null && !f.equals("")) {
					field = f;
				}
				
				if(q != null && !q.equals("")) {
					query = q;
				}
				
				//DAO 작업
				List<Notice> list = null;
				try {
					list = noticedao.getNotices(page, field, query);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//자동으로 notice.jsp까지 forward 해주는 코드
				model.addAttribute("list", list);
				
				/*
				
				 */
				
				return "notice.jsp";
		
	}
	
	@RequestMapping("noticeDetail.htm")
	public String noticedetail(String seq, Model model) {
		//public Notice getNotice(String seq)
		

		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("notice",notice);
		
		return "noticeDetail.jsp";
		
	}
	
	//글쓰기 화면(GET방식)
	@RequestMapping(value="noticeReg.htm", method=RequestMethod.GET)
	public String noticeReg() {
		return "noticeReg.jsp";
	}
	
	//글쓰기 로직 처리(POST방식)
	//DTO(이 프로젝트에서는 vo하위)로 받는다
	@RequestMapping(value="noticeReg.htm", method=RequestMethod.POST)
	public String noticeReg(Notice n) {
		
		System.out.println(n.toString());
		
		//return "noticeReg.jsp";
		return null;
	}
	
	//글 수정하기(화면) GET
	//글 수정하기(처리) POST
	

}
