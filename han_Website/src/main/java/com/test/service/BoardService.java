package com.test.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.Information_ReplyDTO;
import com.test.dto.ReplyDTO;
import com.test.util.Search;

public interface BoardService  {

	// 게시판 글 목록 [전체]
	public List<DbDTO> list(Search search) throws Exception;
	
	// 게시판 글 목록 [카테고리별]
	public List<DbDTO> Board_List(Search search) throws Exception;

	// 글 생성
	public void create(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception;
		
	// 글 읽기
	public DbDTO read(int dno) throws Exception;
		
	// 글 삭제
	public void delete(int dno) throws Exception;
		
	// 글 수정
	public void modify(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception;
	
	// 게시글의 총 갯수 [전체]
	public int getBoardListCnt(Search search) throws Exception;
	
	// 게시글의 총 갯수 [카테고리 별]
	public int getBoardListCnt2(Search search) throws Exception;
	
	// 날자별 작성된 게시글
	public int board_count_day(String str) throws Exception;
		
	// 파일 리스트 조회
	public List<FileDTO> selectFileList(int dno) throws Exception;
	
	// 파일 다운로드
	public FileDTO selectFileInfo(FileDTO dto) throws Exception;
	
	// 수정폼에서 db에 있는 파일에 대한 정보를 삭제
	public void modifyDelete(int file_no) throws Exception;
	
	// 수정폼에서 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴
	public String modifyDeleteServer(int file_no) throws Exception;
	
	// 글 삭제시 해당 글번호의 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴 
	public List<String> deleteServer (int dno) throws Exception;
	
	// 리플 작성 [게시글]
	public void writeReply(ReplyDTO dto) throws Exception;
	
	// 리플 작성  [축구정보]
	public void soccer_writeReply(Information_ReplyDTO dto) throws Exception;
	
	// 리플 수정 [게시글]
	public void updateReply(ReplyDTO dto) throws Exception;
	
	// 리플 수정 [축구정보]
	public void soccer_updateReply(Information_ReplyDTO dto) throws Exception;
		
	// 리플 삭제 [게시글]
	public void deleteReply(ReplyDTO dto) throws Exception;
	
	// 리플 삭제 [축구정보]
	public void soccer_deleteReply(Information_ReplyDTO dto) throws Exception;
	
	// 전체 리플 카운트 [게시글]
    public int reply_allcnt() throws Exception;
    
	// 리플 리스트 [게시글]
    public List<ReplyDTO> commentList(int dno) throws Exception;  
    
    // 리플 리스트 [축구정보]
    public List<Information_ReplyDTO> soccer_commentList(String club_name) throws Exception;
    
    // 날짜별 작성된 리플 [게시글]
    public int reply_count_day(String str) throws Exception;

}
