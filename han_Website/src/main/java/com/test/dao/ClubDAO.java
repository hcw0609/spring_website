package com.test.dao;

import java.util.List;
import com.test.dto.ClubDTO;

public interface ClubDAO {
	
	// 클럽 리스트 가져오기
	public List<ClubDTO> Club_list(ClubDTO dto) throws Exception;
}
