package com.test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;



public class Soccer_Team_Info {
	
	
	public void rank( ) throws IOException {
		
		Document doc = Jsoup.connect("https://namu.wiki/w/아틀레티코 마드리드").get();
		String str = doc.select("#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3) > div > div > div:nth-child(14) > div > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div.wiki-table-wrap.table-center > table > tbody > tr:nth-child(2) > td:nth-child(1) > div").text();
		System.out.println(str);
	}
	
	
	// 팀의 스쿼드에 대한 정보 
	public ArrayList<LinkedHashMap<String, String>> Player_Info (String name) throws Exception {
		
		// html 파일 읽어오기  [레알마드리드]

		Document doc = Jsoup.connect("https://namu.wiki/w/"+name+"").get();
		
		ArrayList<LinkedHashMap<String, String>> player_list = new ArrayList<LinkedHashMap<String,String>>();		
								
		String player_title[] = new String[6];
		String player_info[] = new String[6];
		int play_num = 0;
		int start = 0;
		int country = 0;
		
		
		int index_player_title = 0;
		int index_player_info = 0;
		
		if( name.equals("레알마드리드") ) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3) > div > div > div:nth-child(31) > div.wiki-table-wrap > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		} else if( name.equals("fc바르셀로나")) {			
			name= "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3) > div > div > div:nth-child(14) > div > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div.wiki-table-wrap.table-center > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		} else if ( name.equals("아틀레티코 마드리드")) {
			name="#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(2) > div > div > div:nth-child(58) > div.wiki-table-wrap > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		} else if ( name.equals("리버풀fc")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3) > div > div > div:nth-child(70) > div.wiki-table-wrap.table-center > table > tbody > tr:nth-child(2) > td > div > div > dl > dd > div > div > table > tbody"; 
			start = 0;
			play_num = start;
			country = 3;
		}
		
		breakOut :
		while (true) {
			LinkedHashMap<String, String> player_map = new LinkedHashMap<String, String>();
			play_num = play_num + 1;
					
			for(int value=1; value<8; value++) {
				
				if(value == country) { continue; }
				String src = doc.select( name + "> tr:nth-child("+play_num+") > td:nth-child("+value+") > div").text();
				
				// 내용이 "구단" 이라는 내용이 나오면 중첩루프 탈출
				if(src.contains("구단")) { break breakOut; }
				
				// 문자열 대체
				src = src.replace("[?]", "");
				
				// player_title 정보 추출
				if( play_num == start+1) {
					player_title[index_player_title] = src;	
					index_player_title = index_player_title + 1;
				} else {
					// player_info 정보 추출
					player_info[index_player_info] = src;
					index_player_info = index_player_info + 1;
				}
		
			}
					
			index_player_info = 0;
			
			// 가져온 문자열을 맵형태로 변환
			if( play_num > start+1) {
				for(int index=0; index<player_title.length; index++) {
					player_map.put(player_title[index], player_info[index]);
				}		
				// HashMap을 ArrayList에 삽입
				player_list.add(player_map);
			}				
		}


		System.out.println(player_list);
											
		return player_list;	
	}

	
	
	
	
	
	// 팀의 대한 정보
	public JsonArray Team_Info (String name) throws Exception {	
		
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://namu.wiki/w/"+name+"").get();
				
		// 문자열 저장
		String Team_Title[] = new String[20];
		String Team_Info[] = new String[20];
		
		int array_index = 0;				
		int i = 3;
		int teamcode = 0;
		
		if (name.equals("레알마드리드")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3)";
			teamcode = 8;
		} else if (name.equals("fc바르셀로나")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3)";
			teamcode = 5;
		} else if (name.equals("아틀레티코 마드리드")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(2)";
			teamcode = 8;
		}
		else if (name.equals("리버풀fc")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(5) > div:nth-child(3)";
			teamcode = 5;
		}

		while(true) {
			i = i+1;
			if(i == 5) {
				continue;
			}		
			String a1 = doc.select(name +" > div > div > div:nth-child("+teamcode+") > table > tbody > tr:nth-child("+i+") > td:nth-child(1) > div > strong > span").text();
			String b1 = doc.select(name +" > div > div > div:nth-child("+teamcode+") > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > div").text();
			
			// [숫자] 형태의 문자열 제거
			for (int k=0; k<10; k++) {
				b1 = b1.replace("["+k+"]", "");
			}
			
			// (이후의 문자열 제거
			int idx = b1.indexOf("("); 
			if(idx == -1) {
				System.out.println(a1+":"+b1);	
			} else {
				b1 = b1.substring(0, idx);	
				System.out.println(a1+":"+b1);		
			}
			
			// 배열에 문자열 담기			
			Team_Title[array_index] = a1;
			Team_Info[array_index] = b1;
			
			array_index = array_index + 1;
				
			if(a1.contains("부주장")) {
				break;
			}				
		}
		
		String jsonstr = "";
			
		for(int index=0; index<array_index; index++) {
			if(index < array_index-1 ) {
				jsonstr = jsonstr + "{"+'"'+Team_Title[index]+'"'+":"+'"'+Team_Info[index]+'"'+"},";
			} else {
				jsonstr = jsonstr + "{"+'"'+Team_Title[index]+'"'+":"+'"'+Team_Info[index]+'"'+"}";
			}			
		}
		
		jsonstr = "["+jsonstr+"]";
			
		// 문자열을 Json으로 변경 하고 원하는 데이터만 추출
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonstr);	
				
		/*
		// 가져온 문자열을 맵형태로 변환
		LinkedHashMap<String, String> team_map = new LinkedHashMap<String, String>();
		for(int index=0; index<array_index; index++) {
			team_map.put(Team_Title[index], Team_Info[index]);
		}
		*/
		
		System.out.println(jsonArray);
		return jsonArray;
		
	}
	
	
	public static void main(String[] args) throws Exception {

		Soccer_Team_Info soccer_team_info = new Soccer_Team_Info();		
		soccer_team_info.Team_Info("아틀레티코 마드리드");
		soccer_team_info.Player_Info("아틀레티코 마드리드");			
	}
	
	
}
