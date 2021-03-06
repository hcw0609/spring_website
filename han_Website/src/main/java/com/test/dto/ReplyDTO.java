package com.test.dto;

public class ReplyDTO {

	/*
	CREATE TABLE `mp_reply` (
		dno       INT           NOT NULL,
		rno       INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
		content   VARCHAR(250)  NOT NULL,
		writer    VARCHAR(50)   NOT NULL,
		regdate   VARCHAR(10)   NOT NULL
	);
	*/
	
	private int dno;
	private int rno;
	private String content;
	private String writer;
	private String regdate;
		
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
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
