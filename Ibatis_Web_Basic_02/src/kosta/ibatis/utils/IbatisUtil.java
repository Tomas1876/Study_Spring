package kosta.ibatis.utils;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


public class IbatisUtil {
	
	private static SqlMapClient sqlMapper; 
	//ibatis SqlMapClient (static으로 만들었으므로 다른 클래스에서 new 없이 바로 사용 가능)
	
	static{
		try {
			
		    Reader reader = Resources.getResourceAsReader("kosta/ibatis/sqlmap/sqlMapConfig.xml");
		    sqlMapper = SqlMapClientBuilder.buildSqlMapClient(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SqlMapClient getSqlMapClient(){
		return sqlMapper;
	}
	
}
