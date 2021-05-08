package ncontroller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/") // 얘를 써줌으로서 다른 매핑들 주소 간추릴수 있음
public class CustomerController {

	private NoticeDao noticedao;

	@Autowired // -> 타입에 의한 자동 주입!!! 주소값을 자동으로 주입받을수 있다 -- 얘 없으면 null값나옴
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}

	/*
	 * 1. method안에서 return type [String] 리턴값이 뷰의 주소 !
	 * 
	 * 2. public ModelAndView notices >> ModelAndView 객체 생성 >> 데이터 값, 뷰 설정하고 >>
	 * return함 -> ModelAndView 얘를 리턴
	 * 
	 * 3. public String notices (Model model){ 함수가 실행시 내부적으로 Model객체의 주소가 들어온다
	 * (Model에 있는 인터페이스를 통해서..) }
	 */

	// public List<Notice> getNotices(int page, String field, String query)
	@RequestMapping("notice.htm")
	public String notices(String pg, String f, String q, Model model) {

		// default 값 설정
		int page = 1;
		String field = "TITLE";
		String query = "%%";

		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		if (f != null && !f.equals("")) {
			field = f;
		}

		if (q != null && !q.equals("")) {
			query = q;
		}

		// DAO 작업
		List<Notice> list = null;
		try {
			list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Spring 적용
//		ModelAndView   mv = new ModelAndView();
//		mv.addObject("list", list);
//		mv.setViewName("notice.jsp");
//		return mv;
		model.addAttribute("list", list); // 자동으로 notice.jsp까지 forward가 된다.
		/*
		 * <c:forEach items="${list}" var="n">
		 */
		return "notice.jsp";
	}

	// public Notice getNotice(String seq)
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq, Model model) {

		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

//		ModelAndView  mv = new ModelAndView();
//		
//		mv.addObject("notice", notice);
//		mv.setViewName("noticeDetail.jsp");

		model.addAttribute("notice", notice);

		return "noticeDetail.jsp";
	}

	// 글쓰기 화면 (GET)
	// http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer/notice.htm
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {

		return "noticeReg.jsp";
	}

	// 글쓰기 처리 (POST)
	@RequestMapping(value = "noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice n, HttpServletRequest request) {
		System.out.println(n.toString());

		// cos.jar 자동 파일 업로드
		// 실제 파일 업로드 구현 (upload 업로드)
		String filename = n.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("/customer/upload"); // 배포된 서버 경로
		//String fpath = path + "/" + filename;
		String fpath = path + File.separator + filename;
		System.out.println(fpath);

		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(fpath);
			fs.write(n.getFile().getBytes());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 파일명 추출 (DTO구성 완료)
		n.setFileSrc(filename); // 실제 파일명

		try {
			noticedao.insert(n); // DB insert

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// insert나 update 하고나면 -> (f5 누르면 계속 글이 써진다)
		// 리스트(location.href or redirect)로 넘겨주기
		// ㄴ 서버에게 새로윤 요청을 하도록 만들어야됨 (목록보기)
		// String : redirect -> notice.htm
		// ㄴ = Servlet 또는 jsp : loacation.href or response.sendRedirect

		return "redirect:notice.htm"; // 목록을 재요청으로 바꾸기
	}

	//글수정하기 (화면) GET
		@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.GET)
		public String noticeEdit(String seq , Model model) {
			Notice notice=null;
			try {
			   notice =  	noticedao.getNotice(seq);
			} catch( Exception e) {
					e.printStackTrace();
			} 
			
			model.addAttribute("notice", notice);
			
			return "noticeEdit.jsp";
		}
		//글수정 (처리) POST
		@RequestMapping(value="noticeEdit.htm"  , method = RequestMethod.POST)
		public String noticeEdit(Notice n , HttpServletRequest request) {
			 
			    //파일 업로드 가능
			    String filename =n.getFile().getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로 
				//String fpath = path + "\\" + filename;
				String fpath = path + File.separator + filename;
				System.out.println(fpath);
				
				FileOutputStream fs =null;
				try {
					     fs = new FileOutputStream(fpath);
					     System.out.println("fpath : " + fpath);
					     fs.write(n.getFile().getBytes());
					     
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					 try {
						fs.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				//파일명 (DTO)
				n.setFileSrc(filename);
			
				try {
						noticedao.update(n);  //DB insert
					} catch (Exception e) {
						e.printStackTrace();
					} 
			//처리가 끝나면 상세 페이지로 : redirect  글번호를 가지고 ....
			return "redirect:noticeDetail.htm?seq="+n.getSeq();    //서버에게 새 요청 ....
		}
		
		
		
	}