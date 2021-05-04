package controllers.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dao.NoticeDao;
import vo.Notice;

/*
	게시판 목록 조회
	
	controller가 Model에 의존한다(DAO객체 혹은 DTO객체 필요)
	
	NoticeController는 NoticeDao에 의존한다
	필요하면 값을 받아야 한다(Spring에서는 DI, injection(생성자나 setter함수)을 통해)
 */

public class NoticeController implements Controller{
	
	public NoticeController() {
		System.out.println("NoticeController 생성자 호출");
	}
	
	private NoticeDao noticedao;

	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//DAO 객체 사용
		//public List<Notice> getNotices(int page, String field, String query)
		
		String _page = request.getParameter("pg");
		String _field = request.getParameter("f");
		String _query = request.getParameter("p");
		
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(_page != null && !_page.equals("")) {
			page = Integer.parseInt(_page);
		}
		
		if(_field != null && !_field.equals("")) {
			field = _field;
		}
		
		if(_query != null && !query.equals("")) {
			query = _query;
		}
		
		//DAO 작업
		List<Notice> list = noticedao.getNotices(page, field, query);
		
		//Spring 적용
		ModelAndView mv = new ModelAndView();
		mv.addObject("list",list);
		
		//resover가 없으면 프로젝트 폴더 내에서 알아서 찾아간다 굳이 notice.jsp앞에 상위 경로를 지정하지 않아도!
		mv.setViewName("notice.jsp");
		
		return mv;
	}

}
