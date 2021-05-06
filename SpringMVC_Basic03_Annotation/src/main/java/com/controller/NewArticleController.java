package com.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
	클라이언트 요청
	
	1. 화면 보여달라고 요청(글쓰기, 로그인하기 등) : write.do
	
	2. 처리해달라고 요청(글쓰기 입력 처리, 로그인 완료 처리 등) : writeok.do
	
	그래서 요청주소가 write.do로 들어오면 화면출력
	writeok.do면 로직을 처리해야겠구나 하고 판단
	
	그런데 이렇게 주소를 나누지 않고 하나의 주소로 처리할 수는 없을까?
	
	클라이언트 요청 주소 하나를 가지고 나누어서, 처리하고 판단한다 - 판단근거 : GET or POST
	전송방식이
	1. GET : 화면 출력 - view 제공
	2. POST : 서비스 처리 - insert, update 처리
 */

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	
	//NewArticleController가 service를 필요로 한다
	//->NewArticleController가 service를 주입받겠다
	private ArticleService articleService;
	
	//주소값을 자동으로 주입받는 Annotation Autowired 사용
	@Autowired
	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	@RequestMapping(method=RequestMethod.GET) //화면제공
	public String form() { //함수의 return 타입이 String이면 view의 주소를 리턴하는 것(약속)
		
		//resolver가 있기 때문에 아래처럼만 써도 알아서 앞 뒤에 /WEB-INF/views/, jsp가 붙는다
		return "article/newArticleForm";
		
	}
	
	//이 방식이 데이터를 받는 가장 전통적인 방법이나 코드량이 많기 때문에 Spring에서는 선호하지 않는다
	//다른 방법을 적용할 것
	/*
	@RequestMapping(method=RequestMethod.POST) //insert 처리
	public ModelAndView submit(HttpServletRequest request) {
		
		NewArticleCommand article = new NewArticleCommand();
		article.setParentId(Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
				
		//NewArticleController가 service를 필요로 한다
		//->NewArticleController가 service를 주입받겠다
		this.articleService.writeArticle(article);
		
		//처리하고 나서 다시 제어권이 넘어온다
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand",article);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}
	*/
	
	
}
