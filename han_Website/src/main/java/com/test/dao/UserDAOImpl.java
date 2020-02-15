package com.test.dao;

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
	

}
