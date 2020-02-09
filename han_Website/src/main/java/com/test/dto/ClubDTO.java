package com.test.dto;

public class ClubDTO {

	/*
	 	CREATE TABLE `club` (
			club_image    CHAR(100) NOT NULL,
			club_name     CHAR(30)  NOT NULL PRIMARY KEY,
			club_belong   CHAR(30)  NOT NULL
		);
	 
	 */
	
	private String club_image;
	private String club_name;
	private String club_belong;
	
	
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
	


	
}
