package DI_06_Spring;

public class ArticleService {
	
	//클라이언트 요청에 따라서
	//DAO 객체 생성, 함수를 호출
	//글쓰기, 목록 보기 등등등등등등등
	
	//ArticleService는 ArticleDao에 의존한다
	private ArticleDao articledao;
	
	public ArticleService(ArticleDao articledao) {
		
		this.articledao = articledao;
		System.out.println("ArticleService 생성자 호출");
		
	}
	
	//글쓰기 서비스
	public void write(Article article) {
		
		this.articledao.insert(article);
		
	}
	
	
}
