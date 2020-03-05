package com.test.dto;

public class User_good_bad {

	/*
	CREATE Table user_good_bad(
		good_bad_dno    INT           NOT NULL PRIMARY KEY AUTO_INCREMENT,
		user_good       VARCHAR(100),
		user_bad        VARCHAR(100),
		club_name       VARCHAR(100)
	);
	*/
	
	
	private int good_bad_dno;
	private String user_good;
	private String user_bad;
	private String club_name;
	
	public int getGood_bad_dno() {
		return good_bad_dno;
	}
	public void setGood_bad_dno(int good_bad_dno) {
		this.good_bad_dno = good_bad_dno;
	}
	public String getUser_good() {
		return user_good;
	}
	public void setUser_good(String user_good) {
		this.user_good = user_good;
	}
	public String getUser_bad() {
		return user_bad;
	}
	public void setUser_bad(String user_bad) {
		this.user_bad = user_bad;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	
}
