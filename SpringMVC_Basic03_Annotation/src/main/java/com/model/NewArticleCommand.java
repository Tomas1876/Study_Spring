package com.model;

//DB에 Article 테이블이 있다고 가정하고 1:1로 맵핑되는 클래스(DTO)
public class NewArticleCommand {
	
	/*
	 
	  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	  클라이언트가 보내는 form 태그의 name값과 DTO가 가진 멤버필드명과 DB에 있는 테이블의 컬럼명은 !통일!한다
	  자동화해야 하니까
	  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 
	 */
	
	private int parentId;
	private String title;
	private String content;
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "NewArticleCommand [parentId=" + parentId + ", title=" + title + ", content=" + content + "]";
	}
	
	
	
	

}
