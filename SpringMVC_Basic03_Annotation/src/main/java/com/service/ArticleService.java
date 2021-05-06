package com.service;

import com.model.NewArticleCommand;

//@Service 이 어노테이션을 붙이고 servlet.xml상단에 <context:component-scan base-package="com.servie"> 라고 적으면
//com.servie 폴더를 싹 스캔하면서 @Service 이 어노테이션이 붙어있는 것은 전부 객체로 만들어준다 굳이 bean 이렇게 안 해줘도 됨
//지금은 bean 객체를 직접 만드는 연습중

public class ArticleService {
	
	public ArticleService() {
		System.out.println("ArticleService 생성자 호출");
	}
	
	public void writeArticle(NewArticleCommand command) {
		
		//DAO 있다고 가정
		//insert 실행
		System.out.println("글쓰기 작업 완료 : " + command.toString());
		
	}

}
