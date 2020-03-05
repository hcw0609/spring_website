package com.test.dto;

public class ClubDTO {

	/*
 	CREATE TABLE `club` (
		club_image     VARCHAR(100)  NOT NULL,
		club_name      VARCHAR(30)   NOT NULL PRIMARY KEY,
		club_belong    VARCHAR(30)   NOT NULL,
		club_search    VARCHAR(30)   NOT NULL,
		club_good      INT           NOT NULL DEFAULT 0,
		club_bad       INT           NOT NULL DEFAULT 0,
		club_base	   VARCHAR(500),
		club_title     VARCHAR(500),
		club_roster    VARCHAR(500),
		club_reply_cnt INT DEFAULT 0
	);	 
	 */
	
	private String club_image;
	private String club_name;
	private String club_belong;
	private String club_search;
	private int    club_good;
	private int    club_bad;
	private String club_base;
	private String club_title;
	private String club_roster;
	private int club_reply_cnt;
	
	
	public String getClub_base() {
		return club_base;
	}
	public void setClub_base(String club_base) {
		this.club_base = club_base;
	}
	public String getClub_title() {
		return club_title;
	}
	public void setClub_title(String club_title) {
		this.club_title = club_title;
	}
	public String getClub_roster() {
		return club_roster;
	}
	public void setClub_roster(String club_roster) {
		this.club_roster = club_roster;
	}	
	public String getClub_image() {
		return club_image;
	}
	public void setClub_image(String club_image) {
		this.club_image = club_image;
	}
	public String getClub_name() {
		return club_name;
	}
	public void setClub_name(String club_name) {
		this.club_name = club_name;
	}
	public String getClub_belong() {
		return club_belong;
	}
	public void setClub_belong(String club_belong) {
		this.club_belong = club_belong;
	}
	public String getClub_search() {
		return club_search;
	}
	public void setClub_search(String club_search) {
		this.club_search = club_search;
	}
	public int getClub_good() {
		return club_good;
	}
	public void setClub_good(int club_good) {
		this.club_good = club_good;
	}
	public int getClub_bad() {
		return club_bad;
	}
	public void setClub_bad(int club_bad) {
		this.club_bad = club_bad;
	}
	public int getClub_reply_cnt() {
		return club_reply_cnt;
	}
	public void setClub_reply_cnt(int club_reply_cnt) {
		this.club_reply_cnt = club_reply_cnt;
	}

	
}
