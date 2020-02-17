package com.test.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.dto.UserDTO;
import com.test.dto.VisitorDTO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "Mapper";
	
	// 회원가입
	@Override
	public void register(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".register", userdto);
	}

	
	// 로그인
	@Override
	public UserDTO login(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".login", userdto);
	}


	// EMAIL 중복체크
	@Override
	public int overLap_EMAIL(String EMAIL) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".overLap_EMAIL", EMAIL);
	}
	
	
	// ID 중복체크
	@Override
	public int overLap(UserDTO userdto) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".overLap", userdto);
	}

	// 유저 리스트 가져오기
	@Override
	public List<UserDTO> user_list() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".user_list");
	}
	
	// 유저의 총 수
	@Override
	public int user_count() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".user_count");
	}

	// 날짜 가져오기
	@Override
	public List<VisitorDTO> visitor_visitor_regdate() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".visitor_visitor_regdate");
	}

	
	// 날자별 방문자수 가져오기 [ip중복처리 x]
	@Override
	public int visitor_count_all(String str) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".visitor_count_all", str);
	}

	// 날자별 방문자수 가져오기 [ip중복처리 o]
	@Override
	public int visitor_count_notall(String str) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".visitor_count_notall", str);
	}


	


	
	

}
