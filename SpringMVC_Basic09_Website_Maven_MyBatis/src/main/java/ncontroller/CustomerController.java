package ncontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import service.CustomerService;
import vo.Notice;

@Controller
@RequestMapping("/customer/") // 얘를 써줌으로서 다른 매핑들 주소 간추릴수 있음
public class CustomerController {
	
	/*
	//Service에서 DB작업을 하면 이걸 의존할 필요가 없다
	private NoticeDao noticedao;

	@Autowired // -> 타입에 의한 자동 주입!!! 주소값을 자동으로 주입받을수 있다 -- 얘 없으면 null값나옴
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}
	*/
	
	
	//CustomerController는 CustomerService에 의존합니다
	//==Service 객체의 주소가 필요하다는 의미
	private CustomerService customerservice;
	
	@Autowired
	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}
	
	//글 목록 조회
	@RequestMapping("notice.htm")   //   /customer/notice.htm
	public String notices(String pg , String f , String q , Model model) {
		
		List<Notice>  list = customerservice.notices(pg, f, q);
		model.addAttribute("list", list);  //자동으로 notice.jsp forward 
		return "customer/notice";
		
	}

	// 글 상세 조회
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq, Model model) {

		Notice notice =  customerservice.noticeDetail(seq);
		model.addAttribute("notice", notice);
		return "customer/noticeDetail";

		
	}

	// 글쓰기 화면 (GET)
	// http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer/notice.htm
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {

		return "customer/noticeReg";
	}

	// 글쓰기 처리 (POST)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice n, HttpServletRequest request) {
		
		String url = null;
		
		try {
			customerservice.noticeReg(n, request);
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return "redirect:notice.htm"; // 목록을 재요청으로 바꾸기
	}

	//글수정하기 (화면) GET
		@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.GET)
		public String noticeEdit(String seq , Model model) {
			
			Notice notice = null;
			
			try {
				notice = customerservice.noticeEdit(seq);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			model.addAttribute("notice", notice);
			
			return "customer/noticeEdit";
		}
		
		//글수정 (처리) POST
		@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.POST)
		public String noticeEdit(Notice n , HttpServletRequest request) throws ClassNotFoundException, IOException, SQLException {
			 
			return customerservice.noticeEdit(n, request);
		}
		
		//게시글 삭제
		@RequestMapping("noticeDel.htm")
		public String noticeDel(String seq) throws ClassNotFoundException, SQLException {
			
			return customerservice.noticeDel(seq);
			
		}
		
		//파일 다운로드
		@RequestMapping("download.htm")
		public void download(String p , String f , HttpServletRequest request , HttpServletResponse response) throws IOException {
			   customerservice.download(p, f, request, response);
		}
		
		
		
	}