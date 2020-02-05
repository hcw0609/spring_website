package com.test.dto;

public class UserDTO {

	/*
	CREATE TABLE `han_user` (
	`ID`       CHAR(20) NOT NULL PRIMARY KEY,
	`PASSWORD` CHAR(20) NOT NULL,
	`EMAIL`    CHAR(25) NOT NULL
);
	*/
	
	private String ID;
	private String PASSWORD;
	private String EMAIL;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	
	

	
}
