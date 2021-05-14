package ncontroller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.multi.MultiFileChooserUI;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/customer/")
public class CustomerController {

	//CustomerController 는  CustomerService 에 의존 합니다
	
	private CustomerService customerservice;
	
	@Autowired
	public void setCustomerservice(CustomerService customerservice) {
		this.customerservice = customerservice;
	}
	
	//글목록 조회
	@RequestMapping("notice.htm")   //   /customer/notice.htm
	public String notices(String pg , String f , String q , Model model) {
		
		List<Notice>  list = customerservice.notices(pg, f, q);
		model.addAttribute("list", list);  //자동으로 notice.jsp forward 
		return "customer/notice";
		
	}
	//글 상세 조회
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq  , Model model) {

		Notice notice =  customerservice.noticeDetail(seq);
		model.addAttribute("notice", notice);
		return "customer/noticeDetail";

	}
	//글쓰기 화면 (GET)
	@RequestMapping(value="noticeReg.htm",  method = RequestMethod.GET)
	public String noticeReg() {
			//return  "noticeReg.jsp";
		   return "customer/noticeReg";
	}
	//글쓰기 처리(POST)
	@RequestMapping(value="noticeReg.htm",  method = RequestMethod.POST)
	public String noticeReg(Notice n , HttpServletRequest request) {
		
		String url="redirect:notice.htm";
		//String url=null;
		
		
		try {
			      url = customerservice.noticeReg(n, request);
		} catch (Exception e) {
			
			//트랜잭션이 실패해서 글쓰기가 rollback되는 경우 여길 타게 된다
			//문제는 예외 발생해서 서비스에서 컨트롤러로 예외를 던졌을 때
			//url이 넘어오지 않는다는 것이다
			e.printStackTrace();
		}		
		
		//그래서 아래 코드가 필요
		//String url="redirect:notice.htm";
		//원래는 null로 초기화 해줬다
		//하지만 트랜잭션 관련한 예외가 발생할 것을 대비해
		//예외가 발생하든 하지 않든 목록 페이지가 새로고침 되도록 이렇게 할당해주고
		//함수는 url을 리턴하도록 한다
		//String url="redirect:notice.htm"를 catch블럭에서 줘도 된다
		
		  return url;
	}
	//글수정하기 (화면) GET
	@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.GET)
	public String noticeEdit(String seq , Model model) {
		
		Notice notice =null;
		try {
			notice = customerservice.noticeEdit(seq);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		model.addAttribute("notice", notice);
		return "customer/noticeEdit";
	}
	//글수정 (처리) POST
	@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.POST)
	public String noticeEdit(Notice n , HttpServletRequest request) throws ClassNotFoundException, IOException, SQLException {

		return customerservice.noticeEdit(n, request);
		
	}
	//글 삭제하기
	@RequestMapping("noticeDel.htm") // /customer/noticeDel.htm
	public String noticeDel(String seq) throws ClassNotFoundException, SQLException{
			return customerservice.noticeDel(seq);
	}
	//파일 다운로드
	@RequestMapping("download.htm")
	public void download(String p , String f , HttpServletRequest request , HttpServletResponse response) throws IOException {
		   customerservice.download(p, f, request, response);
	}
	
}





