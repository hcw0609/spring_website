package com.test.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.dto.ClubDTO;

@Repository
public class ClubDAOImpl implements ClubDAO {
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "Mapper";

	@Override
	public List<ClubDTO> Club_list(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".Club_list", dto);
	}
}
