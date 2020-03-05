package com.test.util;

// 검색
public class Search extends Pagination{

	private String searchType = "title";
	private String keyword;
	private String category;
	
	/////////////////////////////////////////////////////////////////////////
	

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
