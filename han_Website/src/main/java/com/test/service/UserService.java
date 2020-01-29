package com.test.service;

import com.test.dto.UserDTO;

public interface UserService {

	// 회원가입
	public void register(UserDTO userdto) throws Exception;
	
	// 로그인
	public UserDTO login(UserDTO userdto) throws Exception;
	
	// ID 중복체크
	public int overLap(UserDTO userdto) throws Exception;
}