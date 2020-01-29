package com.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.ReplyDTO;
import com.test.dto.UserDTO;
import com.test.paging.Search;

public interface BoardService  {

	// 글 목록
	public List<DbDTO> list(Search search) throws Exception;
	
	// 글 목록2
	public List<DbDTO> Board_List(Search search) throws Exception;

	// 글 생성
	public void create(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception;
		
	// 글 읽기
	public DbDTO read(Integer no) throws Exception;
		
	// 글 삭제
	public void delete(Integer no) throws Exception;
		
	// 글 수정
	public void modify(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception;
	
	// 총 갯수
	public int getBoardListCnt(Search search) throws Exception;
	
	// 총 갯수2
	public int getBoardListCnt2(Search search) throws Exception;
	
	// 파일 조회
	public List<FileDTO> selectFileList(int dno) throws Exception;
	
	// 파일 다운로드
	public FileDTO selectFileInfo(FileDTO dto) throws Exception;
	
	// 수정폼에서 파일 삭제
	public void modifyDelete(int tagid) throws Exception;
	
	// 수정폼에서의  파일을 서버에서 삭제
	public String modifyDeleteServer(int tagid) throws Exception;
	
	// 서버에서 파일 삭제
	public List<String> deleteServer (int dno) throws Exception;
	
	// 리플 작성
	public void writeReply(ReplyDTO dto) throws Exception;
	
	// 리플 수정
	public void updateReply(ReplyDTO dto) throws Exception;
		
	// 리플 삭제
	public void deleteReply(int rno) throws Exception;
	
	// 게시글 삭제시 리플도 삭제
	public void deleteReplyBoard(int dno) throws Exception;
	
	// 리플 목록
    public List<ReplyDTO> commentList(int dno) throws Exception;

}
