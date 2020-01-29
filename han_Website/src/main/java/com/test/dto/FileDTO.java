package com.test.dto;

public class FileDTO {

	/*
	create table mp_file ( 
		file_no          INT       NOT NULL PRIMARY KEY AUTO_INCREMENT ,
		dno              INT       NOT NULL,
		org_file_name    CHAR(250) NOT NULL,
		stored_file_name CHAR(40)  NOT NULL,
		file_size        int
	); 
	 
	 */
	
	
	private int file_no;
	private int dno;
	private String org_file_name;
	private String stored_file_name;
	private int file_size;
	
	
	public int getFile_no() {
		return file_no;
	}
	public void setFile_no(int file_no) {
		this.file_no = file_no;
	}
	public int getDno() {
		return dno;
	}
	public void setDno(int dno) {
		this.dno = dno;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public String getStored_file_name() {
		return stored_file_name;
	}
	public void setStored_file_name(String stored_file_name) {
		this.stored_file_name = stored_file_name;
	}
	public int getFile_size() {
		return file_size;
	}
	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}
	
	
	
}
