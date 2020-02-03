package com.test.dto;

import java.util.Date;

public class DbDTO {
/*
create table han (
 dno       INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
 title     CHAR(50)  NOT NULL,
 content   CHAR(250) NOT NULL,
 writer    CHAR(20)  NOT NULL,
 viewcnt   INT       NOT NULL DEFAULT 0,
 regdate   CHAR(10)  NOT NULL,
 fileyn    CHAR(1)   DEFAULT 'n',
 imageyn   CHAR(1)   DEFAULT 'n',
 Thumbnail CHAR(100) NOT NULL,
 category  CHAR(10)  NOT NULL,
 reply_cnt INT
);
*/
		
	private int dno;	
	private String title;
	private String content;
	private String writer;
	private int viewcnt;
	private String regdate;
	private String fileyn;
	private String imageyn;
	private String thumbnail;
	private String category;
	private int reply_cnt;
		
	
	

	private String[] files;
	private int fileCnt;
	
	
	
	public String[] getFiles() {
		return files;
	}

	public int getFileCnt() {
		return fileCnt;
	}	
	
	public void setFiles(String[] files) {
		this.files = files;
		setFileCnt(files.length);
	}
	
	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}

	
	
	public int getDno() {
		return dno;
	}

	public void setDno(int dno) {
		this.dno = dno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getFileyn() {
		return fileyn;
	}

	public void setFileyn(String fileyn) {
		this.fileyn = fileyn;
	}

	public String getImageyn() {
		return imageyn;
	}

	public void setImageyn(String imageyn) {
		this.imageyn = imageyn;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public int getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}
	
	
}
