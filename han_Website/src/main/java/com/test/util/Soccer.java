package com.test.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;


public class Soccer {
	
	public JsonArray getDataHtml() throws Exception {	
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://sports.news.naver.com/wfootball/record/index.nhn").get();
		Elements a = doc.select("div#container");
		
		// Json 형태의 데이터 추출하기 [그래도 문자열이다.]
		String result1 = a.toString().substring(a.toString().lastIndexOf("regularTeamRecordList")+23);		 
		int idx = result1.indexOf("]");
		String result2 = result1.substring(0, idx+1);	 
		String jsonstr = result2;
		
		
		// 문자열을 Json으로 변경 하고 원하는 데이터만 추출
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonstr);	
		
		return jsonArray;
	}	
	
	public JsonArray getDataHtml1() throws Exception {	
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://sports.news.naver.com/wfootball/record/index.nhn?category=primera&tab=team").get();
		Elements a = doc.select("div#container");
		
		// Json 형태의 데이터 추출하기 [그래도 문자열이다.]
		String result1 = a.toString().substring(a.toString().lastIndexOf("regularTeamRecordList")+23);		 
		int idx = result1.indexOf("]");
		String result2 = result1.substring(0, idx+1);	 
		String jsonstr = result2;
		
		
		// 문자열을 Json으로 변경 하고 원하는 데이터만 추출
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonstr);	
		
		return jsonArray;
	}	
	
	public JsonArray getDataHtml2() throws Exception {	
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://sports.news.naver.com/wfootball/record/index.nhn?category=seria&tab=team").get();
		Elements a = doc.select("div#container");
		
		// Json 형태의 데이터 추출하기 [그래도 문자열이다.]
		String result1 = a.toString().substring(a.toString().lastIndexOf("regularTeamRecordList")+23);		 
		int idx = result1.indexOf("]");
		String result2 = result1.substring(0, idx+1);	 
		String jsonstr = result2;
		
		
		// 문자열을 Json으로 변경 하고 원하는 데이터만 추출
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonstr);	
		
		return jsonArray;
	}	
	
	/*
	public static void main(String[] args) throws Exception {

		Soccer soccer = new Soccer();
		soccer.getDataHtml1();
		soccer.getDataHtml2();
		
	}
	*/
}
