package com.test.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

public class Soccer_Team_Info {

	public void STI () throws Exception {
		
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://namu.wiki/w/레알마드리드").get();
		Elements a = doc.select("div.wiki-paragraph");
		
		System.out.println(a);
		
	}
	
	public static void main(String[] args) throws Exception {

		Soccer_Team_Info soccer_team_info = new Soccer_Team_Info();		
		soccer_team_info.STI();
	}
}
