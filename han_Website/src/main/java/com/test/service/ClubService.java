package com.test.service;

import java.util.List;

import com.test.dto.ClubDTO;
import com.test.dto.User_good_bad;

public interface ClubService {
	
	// 클럽 리스트 가져오기
	public List<ClubDTO> Club_list() throws Exception;
	
	// 클럽 별 정보 가져오기 
	public List<ClubDTO> Club_data(String name) throws Exception;
	
	// 클럽 good 카운트
	public void good_cnt(ClubDTO dto) throws Exception;
		
	// 클럽 bad 카운트
	public void bad_cnt(ClubDTO dto) throws Exception;
	
	// user_good
	public void user_good(User_good_bad ugb) throws Exception;
		
	// user_bad
	public void user_bad(User_good_bad ugb) throws Exception;
	
	// user_good_check
	public int user_good_check(User_good_bad ugb) throws Exception;
		
	// user_bad_check
	public int user_bad_check(User_good_bad ugb) throws Exception;
}
