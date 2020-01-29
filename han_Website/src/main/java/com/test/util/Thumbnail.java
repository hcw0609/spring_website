package com.test.util;

public class Thumbnail {

	// 섬네일을 만들기 위해 문자열 자르기
	public String Thumbnail_make(String str) {
		String result1 = str.substring(str.lastIndexOf("src")+5);
				
		int idx = result1.indexOf("\"");
		String result2 = result1.substring(0, idx);	 
		
		return result2;
	}
	
}
