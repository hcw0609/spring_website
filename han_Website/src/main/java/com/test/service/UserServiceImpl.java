package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.dao.UserDAO;
import com.test.dto.UserDTO;
import com.test.dto.VisitorDTO;
import com.test.util.Search;

@Service
public class UserServiceImpl implements UserService {

	@Inject
	private UserDAO userdao;
	
	// 회원가입
	@Override
	public void register(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		userdao.register(userdto);
	}
	
	
	// 로그인
	@Override
	public UserDTO login(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		return userdao.login(userdto);
	}

	
	// EMAIL 중복체크
	@Override
	public int overLap_EMAIL(String EMAIL) throws Exception {
		// TODO Auto-generated method stub
		return userdao.overLap_EMAIL(EMAIL);
	}
			
	// ID 중복체크
	@Override
	public int overLap(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		return userdao.overLap(userdto);
	}
	
	// 유저 리스트 가져오기
	@Override
	public List<UserDTO> user_list(Search search) throws Exception {
		// TODO Auto-generated method stub
		return userdao.user_list(search);
	}
	
	// 유저의 총 수
	@Override
	public int user_count(Search search) throws Exception {
		// TODO Auto-generated method stub
		return userdao.user_count(search);
	}

	// 날짜 가져오기
	@Override
	public List<VisitorDTO> visitor_visitor_regdate() throws Exception {
		// TODO Auto-generated method stub
		return userdao.visitor_visitor_regdate();
	}
	
	// 유저 삭제
	@Override
	public void user_delete(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		userdao.user_delete(userdto);
	}

	// 날자별 방문자수 가져오기 [ip중복처리 x]
	@Override
	public int visitor_count_all(String str) throws Exception {
		// TODO Auto-generated method stub
		return userdao.visitor_count_all(str);
	}

	// 날자별 방문자수 가져오기 [ip중복처리 o]
	@Override
	public int visitor_count_notall(String str) throws Exception {
		// TODO Auto-generated method stub
		return userdao.visitor_count_notall(str);
	}
	
	
}
