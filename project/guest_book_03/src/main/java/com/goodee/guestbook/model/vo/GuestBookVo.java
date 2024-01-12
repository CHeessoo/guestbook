package com.goodee.guestbook.model.vo;

import java.util.Date;

public class GuestBookVo {
	
	private String author;
	private String content;
//	private Date reg_date = new Date();  // 기본값을 Date()객체로 선언 (null 방지)
	private Date reg_date;
	
	// 기본 생성자
	public GuestBookVo() {
		super();
	}
	
	public GuestBookVo(String author, String content, Date reg_date) {
		this.author = author;
		this.content = content;
		this.reg_date = reg_date;
	}
	
	// Getter & Setter
	
	public String getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

}
