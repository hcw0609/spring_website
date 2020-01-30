package com.test.util;

public class Search extends Pagination{

	/* 검색 타입  */
	private String searchType = "title";
	
	/* 검색 키워드 */
	private String keyword;
	
	/* 검색 카테고리 */
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
