package com.test.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.dto.ClubDTO;
import com.test.dto.User_good_bad;

@Repository
public class ClubDAOImpl implements ClubDAO {
	
	@Inject
	private SqlSession sql;
	
	private static String namespace = "Mapper";
	
	// 클럽 리스트 가져오기
	@Override
	public List<ClubDTO> Club_list() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".Club_list");
	}


	// 클럽 별 정보 가져오기 
	@Override
	public List<ClubDTO> Club_data(String name) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".Club_data", name);
	}

	// 클럽 good 카운트
	@Override
	public void good_cnt(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".good_cnt", dto);
	}

	// 클럽 bad 카운트
	@Override
	public void bad_cnt(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".bad_cnt", dto);
	}


	// user_good
	@Override
	public void user_good(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace +".user_good", ugb);
	}

	
	// user_bad
	@Override
	public void user_bad(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace +".user_bad", ugb);
	}

	// user_good_check
	@Override
	public int user_good_check(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".user_good_check", ugb);
	}

	// user_bad_check
	@Override
	public int user_bad_check(User_good_bad ugb) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".user_bad_check", ugb);
	}
}
