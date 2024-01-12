package guest_book_02.model.vo;

import java.util.Date;

public class GuestBookVo {
	
	// 캡슐화
	
	private String author;
	private String content;
	private Date reg_date;
	
	public GuestBookVo () {}
	
	public GuestBookVo(String author, String content, Date reg_date) {
		this.author = author;
		this.content = content;
		this.reg_date = reg_date;
	}
	
	// setter
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	// getter
	public String getAuthor() {
		return author;
	}
	public String getContent() {
		return content;
	}
	public Date getReg_Date() {
		return reg_date;
	}
	
}
