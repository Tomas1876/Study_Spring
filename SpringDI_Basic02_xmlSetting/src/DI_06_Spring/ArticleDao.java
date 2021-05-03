package DI_06_Spring;

//MySql, Oracle이 상요하는 동일한(공통적인) 추상함수가 필요하다
//구현을 강제한다
public interface ArticleDao {
	
	void insert(Article article);
	

}
