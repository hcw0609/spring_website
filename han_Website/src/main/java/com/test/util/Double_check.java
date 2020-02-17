package com.test.util;

import java.util.ArrayList;
import java.util.List;

import com.test.dto.VisitorDTO;

public class Double_check {

	// boardmapper.xml에서 distinct 이나 group by로 중복체크를 하려 했는데 안되서 방법을 바꾼다.
	public List Visitor_Double_check(List<VisitorDTO> list) {
		
		String[] strArr = new String[list.size()];		
		for(int i=0; i<list.size(); i++ ) {
			strArr[i] = list.get(i).getVisitor_regdate();
		}
		
		List str_list = new ArrayList();
		
		for(int i=0; i<strArr.length-1; i++) {
			if(i == 0) {
				str_list.add(strArr[i]);
			} else if ( strArr[i].equals(strArr[i+1]) ) {
				
			} else {
				
				str_list.add(strArr[i+1]);
			}
		}
		
		
		
         return str_list;         	
	}
	
	
}
