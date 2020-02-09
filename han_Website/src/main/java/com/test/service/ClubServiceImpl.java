package com.test.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.test.dao.ClubDAO;
import com.test.dto.ClubDTO;

@Service
public class ClubServiceImpl implements ClubService {

	@Inject
	private ClubDAO dao;
	
	@Override
	public List<ClubDTO> Club_list(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.Club_list(dto);
	}

}
