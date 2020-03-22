package com.test.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;



public class Soccer_Team_Info {
	
	
	// 팀의 우승 횟수에 대한 정보
	public JsonArray Champion( String name ) throws IOException {
		
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://namu.wiki/w/"+name+"").get();
						
		// 문자열 저장
		String Team_Title[] = new String[30];
		String Team_Info[] = new String[30];
				
		int array_index = 0;				
		int teamcode = 0;
		int i = 0;
				
		if (name.equals("레알마드리드")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 7;
			i = 19;
		} else if (name.equals("fc바르셀로나")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 5;
			i = 18;
		} else if (name.equals("아틀레티코 마드리드")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(2)";
			teamcode = 6;
			i = 20;
		} else if (name.equals("리버풀fc")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 5;
			i = 17;
		} else if (name.equals("첼시FC")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 6;
			i = 25;
		} else if (name.equals("아스날 FC")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(2)";
			teamcode = 6;
			i = 21;
		} else if (name.equals("맨시티")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 7;
			i = 21;
		}
		
		while(true) {
			i = i+1;
			
			String a1 = doc.select(name +" > div > div > div:nth-child("+teamcode+") > table > tbody > tr:nth-child("+i+") > td:nth-child(1) > div").text();
			String b1 = doc.select(name +" > div > div > div:nth-child("+teamcode+") > table > tbody > tr:nth-child("+i+") > td:nth-child(2) > div").text();
			
			if(a1.contains("클럽 기록")) {
				break;
			}	
			
			// 배열에 문자열 담기			
			Team_Title[array_index] = a1;
			Team_Info[array_index] = b1;
						
			array_index = array_index + 1;						
		}
		
		String jsonstr = "";
		
		// 가져온 문자열을 Json 형태로 바꾸어 준다.
		for(int index=0; index<array_index; index++) {
			if(index < array_index-1 ) {
				jsonstr = jsonstr + "{"+'"'+Team_Title[index]+'"'+":"+'"'+Team_Info[index]+'"'+"},";
			} else {
				jsonstr = jsonstr + "{"+'"'+Team_Title[index]+'"'+":"+'"'+Team_Info[index]+'"'+"}";
			}			
		}		
		jsonstr = "["+jsonstr+"]";
		
		// Json형태로 바꾼 문자열을 Json으로 변경
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonstr);
		
		System.out.println(jsonArray);
		return jsonArray;				
	}
	
	
	// 팀의 스쿼드에 대한 정보 
	public JsonArray Player_Info (String name) throws Exception {
		
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://namu.wiki/w/"+name+"").get();
		
		ArrayList<LinkedHashMap<String, String>> player_list = new ArrayList<LinkedHashMap<String,String>>();		
								
		String player_title[] = new String[7];
		String player_info[] = new String[7];
		
		String jsonstr[] = new String[30];
		String json_str = "";
		
		int play_num = 0;
		int start = 0;
		int country = 0;
				
		int index_player_title = 0;
		int index_player_info = 0;
		int index_jsonstr = 0;
		
		if( name.equals("레알마드리드") ) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3) > div > div > div:nth-child(30) > div.wiki-table-wrap > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		} else if( name.equals("fc바르셀로나")) {			
			name= "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3) > div > div > div:nth-child(14) > div > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div.wiki-table-wrap.table-center > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		} else if ( name.equals("아틀레티코 마드리드")) {
			name="#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(2) > div > div > div:nth-child(56) > div.wiki-table-wrap > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		} else if ( name.equals("리버풀fc")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3) > div > div > div:nth-child(84) > div.wiki-table-wrap.table-center > table > tbody > tr:nth-child(2) > td > div > div > dl > dd > div > div > table > tbody"; 
			start = 0;
			play_num = start;
			country = 3;
		} else if (name.equals("첼시FC")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3) > div > div > div:nth-child(54) > div.wiki-table-wrap > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div > table > tbody";
			start = 1;
			play_num = start;
			country = 5;
		} else if (name.equals("아스날 FC")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(2) > div > div > div:nth-child(50) > div.wiki-table-wrap > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div > table > tbody";
			start = 0;
			play_num = start;
			country = 2;
		} else if (name.equals("맨시티")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3) > div > div > div:nth-child(36) > div > table > tbody > tr:nth-child(2) > td > div > dl > dd > div > div.wiki-table-wrap.table-center > table > tbody";
			start = 1;
			play_num = start;
			country = 2;
		}
		
		breakOut :
		while (true) {
			play_num = play_num + 1;
					
			for(int value=1; value<8; value++) {
				
				String src = "";
				if(value == country) {
					 src = doc.select( name + "> tr:nth-child("+play_num+") > td:nth-child("+value+") > div > a > span > span > img.wiki-image").attr("data-src");
				} else {
					src = doc.select( name + "> tr:nth-child("+play_num+") > td:nth-child("+value+") > div").text();
				}
				
				// 내용이 "구단" 이라는 내용이 나오면 중첩루프 탈출
				if(src.contains("구단")) { break breakOut; }
				
				// 문자열 대체
				src = src.replace("[?]", "");
				src = src.replace("\"", "'");
				
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
			
			// 가져온 데이터를 json형태의 문자열로 저장해 둔다.
			json_str = "";
			int index = 0;
			
			breakOut2 :
			if( play_num > start+1) {	
				for (int i=0; i<player_title.length; i++) {					
					int inner_index = 0;
					
					// 원하는 순서대로 추출하기 위함
					while(true) {
						
						if( index == 0 && (player_title[inner_index].equals("등번호") || player_title[inner_index].equals("등 번호")) ) {
							json_str = json_str + "{"+'"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+',';
							index += 1;
							break;
						} else if ( index == 1 && (player_title[inner_index].equals("로마자 성명") || player_title[inner_index].equals("영문표기") || player_title[inner_index].equals("영문 이름")) ) {
							json_str = json_str + '"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+',';
							index += 1;
							break;
						} else if ( index == 2 && (player_title[inner_index].equals("생년 월일") || player_title[inner_index].equals("생년월일")) ) {
							json_str = json_str + '"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+',';
							index += 1;
							break;
						} else if ( index == 3 && (player_title[inner_index].equals("신체조건") || player_title[inner_index].equals("신체 조건") || player_title[inner_index].equals("체격") || player_title[inner_index].equals("cm / kg")) ) {
							json_str = json_str + '"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+',';
							index += 1;
							break;
						} else if ( index == 4 && (player_title[inner_index].equals("포지션")) ) {
							json_str = json_str + '"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+',';
							index += 1;
							break;
						} else if ( index == 5 && (player_title[inner_index].equals("")) ) {
							json_str = json_str + '"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+"}";
							index += 1;
							break breakOut2;
						}  else if ( index == 6 && (player_title[inner_index].equals("한글 성명") || player_title[inner_index].equals("이름")) ) {
							json_str = json_str + '"'+player_title[inner_index]+'"'+":"+'"'+player_info[inner_index]+'"'+',';
							index += 1;
							break;							
						} else {
							inner_index += 1;	
						}							
					}
				}							
			}
					
			// 가져온 json형태의 문자열을 배열에 하나 씩 저장한다.
			jsonstr[index_jsonstr] = json_str;		
			index_jsonstr += 1;	
			
		}
		
		// 가져온 json형태의 여러 문자열들을 하나의 문자열로 통합한다.
		json_str = "";
		for(int i=1; i<index_jsonstr-1; i++ ) {
			if( i != index_jsonstr-1 ) {
				json_str += jsonstr[i] + ",";
			} else {
				json_str += jsonstr[i];
			}
		}
		json_str = "["+json_str+"]";
		
		// Json형태로 바꾼 문자열을 Json으로 변경
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(json_str);	
		
		System.out.println(jsonArray);
		return jsonArray;	
	}

	
	// 팀의 대한 정보
	public JsonArray Team_Info (String name) throws Exception {	
		
		// html 파일 읽어오기
		Document doc = Jsoup.connect("https://namu.wiki/w/"+name+"").get();
				
		// 문자열 저장
		String Team_Title[] = new String[25];
		String Team_Info[] = new String[25];
		
		int array_index = 0;				
		int teamcode = 0;
		int i = 3;
		
		if (name.equals("레알마드리드")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 7;
		} else if (name.equals("fc바르셀로나")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 5;
		} else if (name.equals("아틀레티코 마드리드")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(2)";
			teamcode = 6;
		} else if (name.equals("리버풀fc")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 5;
		} else if (name.equals("첼시FC")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 6;
		} else if (name.equals("아스날 FC")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(2)";
			teamcode = 6;
		} else if (name.equals("맨시티")) {
			name = "#app > div > div:nth-child(2) > article > div:nth-child(6) > div:nth-child(3)";
			teamcode = 7;
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
				//System.out.println(a1+":"+b1);	
			} else {
				b1 = b1.substring(0, idx);	
				//System.out.println(a1+":"+b1);		
			}
			
			// 배열에 문자열 담기			
			Team_Title[array_index] = a1;
			Team_Info[array_index] = b1;
			
			array_index = array_index + 1;
			
			// "부주장"이라는 내용이나오면 break;
			if(a1.contains("부주장")) {
				break;
			}				
		}
		
		// 가져온 문자열을 Json 형태로 바꾸어 준다.
		String jsonstr = "";
		for(int index=0; index<array_index; index++) {
			if(index < array_index-1 ) {
				jsonstr = jsonstr + "{"+'"'+Team_Title[index]+'"'+":"+'"'+Team_Info[index]+'"'+"},";
			} else {
				jsonstr = jsonstr + "{"+'"'+Team_Title[index]+'"'+":"+'"'+Team_Info[index]+'"'+"}";
			}			
		}		
		jsonstr = "["+jsonstr+"]";
			
		// Json형태로 바꾼 문자열을 Json으로 변경
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = (JsonArray) jsonParser.parse(jsonstr);	
		
		System.out.println(jsonArray);	
		return jsonArray;		
	}
	
	
	
	public static void main(String[] args) throws Exception {

		Soccer_Team_Info soccer_team_info = new Soccer_Team_Info();		
		soccer_team_info.Team_Info("맨시티");
		soccer_team_info.Player_Info("맨시티");
		soccer_team_info.Champion("맨시티");
		//soccer_team_info.aaa();
	}
	
	
}
