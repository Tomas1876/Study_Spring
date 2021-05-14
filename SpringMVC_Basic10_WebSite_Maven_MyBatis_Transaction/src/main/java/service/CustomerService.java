package service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import dao.NoticeDao;
import vo.Notice;

@Service
public class CustomerService {
	// DB작업
	// Mybatis >> Root IOC >> DI
	private SqlSession sqlsession;

	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	// 글목록보기 서비스함수
	public List<Notice> notices(String pg, String f, String q) {

		// default
		int page = 1;
		String field = "TITLE";
		String query = "%%";

		// 조건처리
		if (pg != null && !pg.equals("")) {
			page = Integer.parseInt(pg);
		}

		if (f != null && !f.equals("")) {
			field = f;
		}

		if (q != null && !q.equals("")) {
			query = q;
		}

		// DAO 데이터 받아오기
		List<Notice> list = null;
		try {
			// mapper 를 통한 인터페이스 연결
			NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
			//
			list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 글상세보기 서비스 함수
	public Notice noticeDetail(String seq) {
		Notice notice = null;
		try {
			NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return notice;
	}

	// 글쓰기처리 서비스 함수
	// 트랜잭션 처리
	@Transactional
	public String noticeReg(Notice n, HttpServletRequest request) throws Exception {

		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>(); // 파일명관리

		if (files != null && files.size() > 0) { // 최소 1개의 업로드가 있다면
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload");

				String fpath = path + "\\" + filename;

				if (!filename.equals("")) { // 실 파일 업로드
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(multifile.getBytes());
					fs.close();
				}
				filenames.add(filename); // 파일명을 별도 관리 (DB insert)
			}

		}

		// DB 파일명 저장
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));
		
		/*
			insert : notice.insert(n);
			update : dao 인터페이스에 함수가 있어야 하고 함수는 mapper와 맵핑되어 있어야 한다
			
			1. transactionManager bean객체 생성
			2. @Transactional 어노테이션의 동작을 지원하는 <tx:annotation-driven transaction-manager="transactionManager"/> 엘리먼트 생성
			3. 적용될 함수 위에 @Transactional 선언
			4. 두 개의 dao 함수를 실행(insert와 update)
				4-1. 정상 commit 될 경우(게시글 두 개까지는 이상 없이 commit)
				4-2. rollback 될 경우(세번째 게시글 작성시 update point가 걸림)
			5. 다양한 처리 방법 중에서 예외가 발생할 경우 default로 rollback
				transactionManager @Transactional 이 붙은 ㅎ마수를 감시하다 exceptino이 발생하면 모든 처리를 rollback할 것
				
		 */
		
		//mapper 사용
		NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);

		try {
			noticedao.insert(n);
			noticedao.updateOfMemberPoint("admin");
			System.out.println("정상실행");

		} catch (Exception e) {
			//여기가 POINT
			//트랜잭션이 실패하면(알고 있던 것이라도) 500이 떠버린다
			//여기서 예외를 잘 던져서 처리하는 것이 관건
			System.out.println("Transaction 문제 발생 : " + e.getMessage());
			
			throw e;
			//서비스를 실행한 주체가 Controller고 이 주체에게 예외를 던져버린다
			//이제 컨트롤러에 가서 마저 처리를 해준다
		}

		return "redirect:notice.htm"; // 문자열로 리턴

	}

	// 글삭제하기 서비스 함수
	public String noticeDel(String seq) throws ClassNotFoundException, SQLException {
		NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
		noticedao.delete(seq);
		return "redirect:notice.htm";
	}

	// 글수정하기 서비스 함수 (select 화면)
	public Notice noticeEdit(String seq) throws ClassNotFoundException, SQLException {
		NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
		Notice notice = noticedao.getNotice(seq);
		return notice;
	}

	// 글수정하기 처리 서비스 함수(update 처리)
	public String noticeEdit(Notice n, HttpServletRequest request)
			throws IOException, ClassNotFoundException, SQLException {

		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames = new ArrayList<String>(); // 파일명관리

		if (files != null && files.size() > 0) { // 최소 1개의 업로드가 있다면
			for (CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload");

				String fpath = path + "\\" + filename;

				if (!filename.equals("")) { // 실 파일 업로드
					FileOutputStream fs = new FileOutputStream(fpath);
					fs.write(multifile.getBytes());
					fs.close();
				}
				filenames.add(filename); // 파일명을 별도 관리 (DB insert)
			}

		}

		// DB 파일명 저장
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));

		NoticeDao noticedao = sqlsession.getMapper(NoticeDao.class);
		noticedao.update(n);
		return "redirect:noticeDetail.htm?seq=" + n.getSeq();
	}

	// 파일 다운로드 서비스 함수
	public void download(String p, String f, HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		String fname = new String(f.getBytes("euc-kr"), "8859_1");
		response.setHeader("Content-Disposition", "attachment;filename=" + fname + ";");

		String fullpath = request.getServletContext().getRealPath("/customer/" + p + "/" + f);
		System.out.println(fullpath);
		FileInputStream fin = new FileInputStream(fullpath);

		ServletOutputStream sout = response.getOutputStream();
		byte[] buf = new byte[1024]; // 전체를 다읽지 않고 1204byte씩 읽어서
		int size = 0;
		while ((size = fin.read(buf, 0, buf.length)) != -1) {
			sout.write(buf, 0, size);
		}
		fin.close();
		sout.close();
	}
}
