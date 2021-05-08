package vo;

import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Notice {
	private String seq;
	private String title;
	private String writer;
	private Date regdate;
	private String fileSrc;
	private int hit;
	private String content;
	
	// 파일 업로드를 DTO로 받을거양
	// - 파일 업로드 -
		private CommonsMultipartFile file; // 업로드한 파일 정보를 담는 변수 -> CommonsMultipartFile
		// ㄴ 단 조건은 -> <input type="file" name="file"> 이렇게 타입과 네임이 같이가야됨 
		public CommonsMultipartFile getFile() {
			return file;
		}
		
		public void setFile(CommonsMultipartFile file) {
			this.file = file;
		}
	
	
	
	
	public String getFileSrc() {
		return fileSrc;
	}
	public void setFileSrc(String fileSrc) {
		this.fileSrc = fileSrc;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Notice [seq=" + seq + ", title=" + title + ", writer=" + writer + ", regdate=" + regdate + ", fileSrc="
				+ fileSrc + ", hit=" + hit + ", content=" + content + "]";
	}
	
}
