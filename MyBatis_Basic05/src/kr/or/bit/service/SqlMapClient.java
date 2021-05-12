package kr.or.bit.service;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/*
나쁜 냄새가 나는 코드  
public class SqlMapClient {
	private static SqlSession session; //SqlSession의 가장 좋은 스코프는 메서드이다 그러므로 static으로 만들면 안된다
	
	static {
		String resource = "SqlMapConfig.xml";
		try {
			  Reader reader =  Resources.getResourceAsReader(resource);
			  SqlSessionFactory factory=  new  SqlSessionFactoryBuilder().build(reader);
			  session = factory.openSession();
		}catch (Exception e) {
			
		}
	}
	public static SqlSession getSqlSession() {
		return session;
	}
	
}

아래가 좋은 예제

*/

public class SqlMapClient {
	private static SqlSessionFactory sqlsessionfactory;
	static {
		String resource = "SqlMapConfig.xml";
		try {
			 Reader reader = Resources.getResourceAsReader(resource);
			 sqlsessionfactory = new SqlSessionFactoryBuilder().build(reader);
			 
		}catch(Exception e) {
		}
	}
	 public static SqlSessionFactory getSqlSession(){
		  return sqlsessionfactory;
	  }	
}
