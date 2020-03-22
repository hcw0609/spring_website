package com.test.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dto.DbDTO;

public class File_Upload {

	// 파일이 저장될 위치
	private static final String filePath = "/hcw0609/tomcat/webapps/ROOT/resources/mp_file/";
		
	public List<Map<String, Object>> parseInsertFileInfo(DbDTO dbdto, 
														 MultipartHttpServletRequest mpRequest) throws Exception{
		
		// 가져온 파일의 이름들을 Iterator<String>형태로 iterator이름에 저장한다.
		Iterator<String> iterator = mpRequest.getFileNames();

		// MultipartHttpServletRequest로 가져온 데이터를 하나씩 받을 예정
		MultipartFile multipartFile = null;
		
		// 사용할 변수들 
		String originalFileName = null;
		String storedFileName = null;
		
		// 하나의 파일에 대한 정보를 담을 그릇을 만든다.
		Map<String, Object> listMap = null;
		
		// listMap에 저장된 파일에대한 정보를  list 라는 곳에 담는다.
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		while(iterator.hasNext()) {
			// iterator에 저장된 값은 하나씩 꺼내서 값에 해당하는 결과를 multipartFile에 저장한다.
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				
				// 랜덤 문자 생성
				String random = UUID.randomUUID().toString().replace("-", "");
				 
				// 원본이름 , 저장될 이름 [중복을 방지하기 위해 랜덤문자]
				originalFileName = multipartFile.getOriginalFilename();
				storedFileName = random + originalFileName.substring(originalFileName.lastIndexOf("."));

				// 파일이 저장될 주소 지정 + 파일 쓰기
				File file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
				
				// 하나의 파일에 대한 정보를 담을 그릇을 만든다.
				listMap = new HashMap<String, Object>();
				
				// 파일에 대한 정보를 하나하나를 listMap에 저장한다.
				listMap.put("dno", dbdto.getDno());
				listMap.put("org_file_name", originalFileName);
				listMap.put("stored_file_name", storedFileName);
				listMap.put("file_size", multipartFile.getSize());
				
				// listMap에 저장된 파일에대한 정보를  list 라는 곳에 담는다.
				list.add(listMap);
			}
		}
		return list;
	}
}