package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import vo.Notice;


/*

Spring > JdbcTemplate 강제 

​

NewNoticeDao 가 동작하기 위해서는 JdbcTemplate 객체가 필요하다
 == NewNoticeDao는 JdbcTemplate에 의존한다

1. queryForObject : 

	- [레코드 하나(row 1개)]의 값만을 들고 올때 사용 
	- 주의점 : 0~2개 이상이면
	--IncorrectResultSizeDataAccessException을 발생시킴. 
	--select sum(sal) from emp
	--select id, num , title from emp where id=100;

​
2. query : [레코드 여러개]의 값[객체목록]을 가져올때 사용 (게시판)

	--리턴타입 List<T>


​3. queryForList :

	--쿼리 실행 결과로 읽어온 컬럼개수가 한개인 경우(데이터 여러건)
	--select name from emp

​
3. JdbcTemplete 객체 사용시 *************************** (머리 아파요)

	-컬럼명과 VO의 변수명이 같아야한다.

​
4. ParameterizedBeanPropertyRowMapper와 RowMapper 차이점

	-select 함수 인자 
	- ParameterizedBeanPropertyRowMapper 컬럼명이 같은 경우
	- RowMapper 컬럼명이 다른경우에 set 사용
	- BeanPropertyRowMapper경우는 list와 같은 여러개의 레코드를 받을 때 사용
​
5. 삽입 / 수정 / 삭제 를 위한 메서드 update()

5. template.update(sql,new PreparedStatementSetter() {

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
	
		ps.setString(1, n.getTitle());	
		ps.setString(2, n.getContent());		
		ps.setString(3, n.getFileSrc());
	
	}
});


6. template.update(

		new PreparedStatementCreator() {
	
		@Override
		public PreparedStatement createPreparedStatement(Connection con)
		
		throws SQLException {
		
			String sql = "INSERT INTO NOTICES(SEQ,			
			TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM		
			NOTICES), ?, ?, 'kglim', SYSDATE, 0, ?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1, n.getTitle());		
			ps.setString(2, n.getContent());			
			ps.setString(3, n.getFileSrc());
			
			return ps;
			
			}
		
		} 
	
	);

​

*/

/*

	@Controller Presentation Layer에서 Contoller를 명시하기 위해서 사용
	
	@Service Business Layer에서 Service를 명시하기 위해서 사용
	
	@Repository Persistence Layer에서 DAO를 명시하기 위해서 사용
	
	@Component 그 외에 자동으로 스캔해서 등록하고 싶은 것들을 위해 사용
	
	@Repository
	
	NewNoticeDao 에 Annotation 붙이고 싶다면 ....

*/



@Repository //필수사항
public class NewNoticeDao implements NoticeDao {
	
	//NewMemberDao가 동작(DB작업)하기 위해서는 JDCB 템플릿 객체가 필요하다
	// == NewMemberDao는 JDCB 템플릿 객체에 의존한다
	
	private JdbcTemplate jdbctemplate;
	
	
	@Autowired
	public void setJdbctemplate(JdbcTemplate jdbctemplate) {
		this.jdbctemplate = jdbctemplate;
	}
	
	
	//Notices 테이블에서 count한 것
	@Override
	public int getCount(String field, String query) throws ClassNotFoundException, SQLException {
		//쿼리의 결과가 단일값 (sum() , max() , min(), count())
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";
		
		
		//이 한 줄은 원래
		return this.jdbctemplate.queryForObject(sql, Integer.class, "%"+query+"%");
		// sql은 쿼리문, Integer.class은 return값의 타입
		// "%"+query+"%"은 
		
		/* 아래의 코드였다 템플릿을 사용해 이걸 한 줄로 줄인 것
		String sql = "SELECT COUNT(*) CNT FROM NOTICES WHERE "+field+" LIKE ?";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 1. 접속
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
				"springuser", "1004");
		// 2. 실행
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		
		// 3. 결과
		ResultSet rs = st.executeQuery();
		rs.next();
		
		int cnt = rs.getInt("cnt");
		*/
	}

	@Override
	public List<Notice> getNotices(int page, String field, String query) throws ClassNotFoundException, SQLException {
		
		int srow = 1 + (page-1)*5;
		int erow = 5 + (page-1)*5;

		String sql = "SELECT * FROM"; // select에서 *를 하는 건 별로 좋은 습관이 아니다 뭘 읽었는지 불명확해지기 때문
		sql += "(SELECT ROWNUM NUM, N.* FROM (SELECT * FROM NOTICES WHERE "+field+" LIKE ? ORDER BY REGDATE DESC) N)";
		sql += "WHERE NUM BETWEEN ? AND ?";
		
		//Notice DTO 객체 데이터 담아서
		//List 형태로 리턴
		//Object[] arr = {"%"+query+"%" , srow , erow}
		//결과를 notice 객체를 만들어서 데이터 건수만큼 .. List 추가 .....
		return this.jdbctemplate.query(sql, new Object[]{"%"+query+"%",srow,erow}, new BeanPropertyRowMapper<Notice>(Notice.class));
		//BeanPropertyRowMapper
		// 객체 속성    DB에서 셀렉트한 결과 연결 - 이름의 뜻
		//DB에서 select한 결과와 객체의 속성을 자동 매핑해주는 객체
	}
	
	@Override
	public Notice getNotice(String seq) throws ClassNotFoundException, SQLException {
	String sql = "SELECT seq,title,writer,content,regdate,hit,filesrc,filesrc2 FROM NOTICES WHERE SEQ="+seq;

	return this.jdbctemplate.queryForObject(sql,new RowMapper<Notice>(){
		
		//자동 매핑이 되지 않는 경우
		//자동 매핑 (select 컬럼명 == dto member field 일치)
		//아래화 같은 작업을 해야 하는데 그렇다면 Template을 사용할 필요가 없다
		//자동화 준비를 꼭 하자 == ui의 input name과 dto(memberfield)와 DB컬럼명을 모두 같게 맞추자​
		@Override
		public Notice mapRow(ResultSet rs, int rownum) throws SQLException {
			Notice n = new Notice();
			//Notice 객체의 생성자 구현 new Notice(rs.getString("seq") ,...
			//하나씩 mapping
			n.setSeq(rs.getString("seq"));
			n.setTitle(rs.getString("title"));
			n.setWriter(rs.getString("writer"));
			n.setRegdate(rs.getDate("regdate"));
			n.setHit(rs.getInt("hit"));
			n.setContent(rs.getString("content"));
			n.setFileSrc(rs.getString("fileSrc"));
			n.setFileSrc2(rs.getString("fileSrc2"));
			return n;
			}
		});
	
		/*
		일반적인 자동 매핑 (권장)
		String sql="SELECT * FROM NOTICES WHERE SEQ="+seq;
		try{}catch(Exception e) {} 처리 권장
		return this.jdbctemplate.queryForObject(sql,
		new BeanPropertyRowMapper<Notice>(Notice.class));
		*/
	}

	@Override
	public int delete(String seq) throws ClassNotFoundException, SQLException {
		String sql = "DELETE NOTICES WHERE SEQ=?";
		return this.jdbctemplate.update(sql, seq);
	}
	@Override
	public int update(Notice notice) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE NOTICES SET TITLE=?, CONTENT=?, FILESRC=? , FILESRC2=? WHERE SEQ=?";
		return this.jdbctemplate.update(sql,notice.getTitle(),notice.getContent(),
		notice.getFileSrc(),notice.getFileSrc2() ,notice.getSeq());
	}
	@Override
	public int insert(Notice n) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO NOTICES(SEQ, TITLE, CONTENT, WRITER, REGDATE, HIT, FILESRC , FILESRC2) VALUES( (SELECT MAX(TO_NUMBER(SEQ))+1 FROM NOTICES), ?, ?, 'bituser', SYSDATE, 0, ?,?)";
		return this.jdbctemplate.update(sql, n.getTitle(),n.getContent(),n.getFileSrc(),n.getFileSrc2());
	}
}