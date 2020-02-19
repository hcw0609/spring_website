package com.test.dao;

import java.util.List;

import com.test.dto.UserDTO;
import com.test.dto.VisitorDTO;
import com.test.util.Search;

public interface UserDAO {

	// 회원가입
	public void register(UserDTO userdto) throws Exception;
	
	// 로그인
	public UserDTO login(UserDTO userdto) throws Exception;
		
	// EMAIL 중복체크
	public int overLap_EMAIL(String EMAIL) throws Exception;
		
	// ID 중복체크
	public int overLap(UserDTO userdto) throws Exception;
	
	// 유저 리스트
	public List<UserDTO> user_list(Search search) throws Exception;
	
	// 유저의 총 수
	public int user_count(Search search) throws Exception;
	
	// 유저 삭제
	public void user_delete(UserDTO userdto) throws Exception;
	
	// 날짜 가져오기
	public List<VisitorDTO> visitor_visitor_regdate() throws Exception;
	
	// 날자별 방문자수 가져오기 [ip중복처리 x]
	public int visitor_count_all(String str) throws Exception;
	
	// 날자별 방문자수 가져오기 [ip중복처리 o]
	public int visitor_count_notall(String str) throws Exception;
	
}
