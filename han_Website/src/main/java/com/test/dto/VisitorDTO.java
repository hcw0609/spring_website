package com.test.dto;

public class VisitorDTO {
	/*
	 CREATE TABLE visitor(
		visitor_no 	    INT				NOT NULL PRIMARY KEY AUTO_INCREMENT,
		visitor_regdate CHAR(10)        NOT NULL,
		visitor_ip		String(50)      NOT NULL
	 };
	 
	 */
	
	
	private int 	visitor_no;
	private String  visitor_regdate;
	private String  visitor_ip;
	
	public int getVisitor_no() {
		return visitor_no;
	}
	public void setVisitor_no(int visitor_no) {
		this.visitor_no = visitor_no;
	}
	public String getVisitor_regdate() {
		return visitor_regdate;
	}
	public void setVisitor_regdate(String visitor_regdate) {
		this.visitor_regdate = visitor_regdate;
	}
	public String getVisitor_ip() {
		return visitor_ip;
	}
	public void setVisitor_ip(String visitor_ip) {
		this.visitor_ip = visitor_ip;
	}

}
