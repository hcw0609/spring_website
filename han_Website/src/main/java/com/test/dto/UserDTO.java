package com.test.dto;

public class UserDTO {

	/*
	create table han_user (
			ID varchar2(20) primary key,
			NAME varchar2(20) not null,
			PASSWORD varchar2(20) not null
	)
	*/
	
	private String ID;
	private String NAME;
	private String PASSWORD;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}
	
}
