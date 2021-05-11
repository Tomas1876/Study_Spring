package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import vo.Member;


//Spring JDBC를 사용하면서 memberDao를 인터페이스화 시켰다
@Repository
public interface MemberDao {
	public Member getMember(String uid) throws ClassNotFoundException, SQLException;
	
	public int insert(Member member) throws ClassNotFoundException, SQLException;
	
}
