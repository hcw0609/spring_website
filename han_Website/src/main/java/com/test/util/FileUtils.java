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

public class FileUtils {

	
	private static final String filePath = "C:\\mp\\file\\"; // 파일이 저장될 위치
	
	
	/*
	// for ftp
	private static final String filePath = "/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"; // 파일이 저장될 위치
	*/
	
	public List<Map<String, Object>> parseInsertFileInfo(DbDTO dbdto, 
														 MultipartHttpServletRequest mpRequest) throws Exception{
	
		
		// 가져온 파일의 이름들을 Iterator<String>형태로 iterator이름에 저장한다.
		Iterator<String> iterator = mpRequest.getFileNames();

		// MultipartHttpServletRequest로 가져온 데이터를 하나씩 받을 예정
		MultipartFile multipartFile = null;
		
		// 사용할 변수들 
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;

		
		// 하나의 파일에 대한 정보를 담을 그릇을 만든다.
		Map<String, Object> listMap = null;
		
		// listMap에 저장된 파일에대한 정보를  list 라는 곳에 담는다.
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		
		File file = new File(filePath);
		
		// 경로가 존재하지 않으면 만들어줘
		if(file.exists() == false) {
			file.mkdirs();
		}

		
		while(iterator.hasNext()) {
			// iterator에 저장된 값은 하나씩 꺼내서 값에 해당하는 결과를 multipartFile에 저장한다.
			multipartFile = mpRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false) {
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = getRandomString() + originalFileExtension;

				// 파일이 저장될 경로를 저장
				file = new File(filePath + storedFileName);
				
				// 지정된위치에 파일을 저장한다.
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
	
	// 저장되는 파일의 이름이 중복되지 않게 하기 위해
	public static String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}