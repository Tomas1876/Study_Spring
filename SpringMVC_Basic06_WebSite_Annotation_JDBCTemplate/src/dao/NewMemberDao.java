package dao;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vo.Member;

@Repository
public class NewMemberDao implements MemberDao {
	
	//NewMemberDao가 동작(DB작업)하기 위해서는 JDCB 템플릿 객체가 필요하다
	// == NewMemberDao는 JDCB 템플릿 객체에 의존한다
	
	private JdbcTemplate jdbctemplate;
	
	
	@Autowired
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}

	@Override
	public Member getMember(String uid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Member member) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
