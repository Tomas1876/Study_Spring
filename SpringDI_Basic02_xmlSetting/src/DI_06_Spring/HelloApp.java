package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {

		/*
		// 만약 java코드였다면

		// Oracle 사용해서 insert 실행
		OracleArticleDao articledao = new OracleArticleDao();
		//만약 MySQl DB를 사용할거라면
		//MySqlArticleDao articledao = new MySqlArticleDao();
				
		ArticleService articleservice = new ArticleService(articledao);
		
		Article article = new Article();
		articleservice.write(article);
	*/
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml");
		
		ArticleService articleservice = context.getBean("articleservice", ArticleService.class);
		Article article = context.getBean("article", Article.class);
		
		articleservice.write(article);
	}

}
