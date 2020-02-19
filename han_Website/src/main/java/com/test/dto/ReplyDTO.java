package com.test.dto;

public class ReplyDTO {

	/*
	create table mp_reply (
		dno number not null,
		rno number not null,
		content varchar2(1000) not null,
		writer varchar2(50) not null,
		regdate date default sysdate,
		primary key(dno, rno)
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
