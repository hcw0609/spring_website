package com.test.dto;

public class Information_ReplyDTO {
	/*
	CREATE TABLE information_reply (
		rno       INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
		club_name VARCHAR(10)   NOT NULL,
		content   VARCHAR(250)  NOT NULL,
		writer    VARCHAR(50)   NOT NULL,
		regdate   VARCHAR(10)   NOT NULL
	) 
	*/
	
	
	private int rno;
	private String club_name;
	private String content;
	private String writer;
	private String regdate;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}
