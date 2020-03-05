package com.test.util;

import com.test.dto.UserDTO;

public class UserCheck {

	// 인자 2개 짜리 유저 확인
	public String User_Check(String writer, UserDTO loginInfo) {		
		try {
			if ( !loginInfo.getID().equals(writer) ) {
				return "NotEqual";
			} 			
		} catch (Exception e) {
			return "NullSession";
		}
		return "OK";
	}
	
	// 인자 1개 짜리 유저 확인
	public String User_Check(UserDTO loginInfo) {		
		try { 
			loginInfo.getID();
		} catch (Exception e) {
			return "NullSession";
		}
		return "OK";
	}
}
