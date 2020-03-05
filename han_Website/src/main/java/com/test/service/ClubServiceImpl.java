package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.dao.ClubDAO;
import com.test.dto.ClubDTO;
import com.test.dto.User_good_bad;

@Service
public class ClubServiceImpl implements ClubService {

	@Inject
	private ClubDAO dao;
	
	// 클럽 리스트 가져오기
	@Override
	public List<ClubDTO> Club_list() throws Exception {
		// TODO Auto-generated method stub
		return dao.Club_list();
	}

	// 클럽 별 정보 가져오기 
	@Override
	public List<ClubDTO> Club_data(String name) throws Exception {
		// TODO Auto-generated method stub
		return dao.Club_data(name);
	}
	
	// 클럽 good 카운트
	@Override
	public void good_cnt(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.good_cnt(dto);
	}
	
	// 클럽 bad 카운트
	@Override
	public void bad_cnt(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.bad_cnt(dto);
	}

	// user_good
	@Override
	public void user_good(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		dao.user_good(ugb);
	}

	// user_bad
	@Override
	public void user_bad(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		dao.user_bad(ugb);
	}
	
	// user_good_check
	@Override
	public int user_good_check(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_good_check(ugb);
	}

	// user_bad_check
	@Override
	public int user_bad_check(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		return dao.user_bad_check(ugb);
	}

}
