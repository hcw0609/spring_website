package com.test.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.dao.UserDAO;
import com.test.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userdao;
	
	// 회원가입
	@Override
	public void register(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("UserServiceImpl");
		userdao.register(userdto);
	}
	
	
	// 로그인
	@Override
	public UserDTO login(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		return userdao.login(userdto);
	}


	// ID 중복체크
	@Override
	public int overLap(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		return userdao.overLap(userdto);
	}

}
