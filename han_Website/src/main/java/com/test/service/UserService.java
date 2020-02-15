package com.test.service;

import com.test.dto.UserDTO;
import com.test.dto.VisitorDTO;

public interface UserService {

	// 회원가입
	public void register(UserDTO userdto) throws Exception;
	
	// 로그인
	public UserDTO login(UserDTO userdto) throws Exception;
	
	// EMAIL 중복체크
	public int overLap_EMAIL(String EMAIL) throws Exception;
	
	// ID 중복체크
	public int overLap(UserDTO userdto) throws Exception;

}
