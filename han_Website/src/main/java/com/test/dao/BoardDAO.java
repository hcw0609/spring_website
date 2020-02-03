package com.test.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.ReplyDTO;
import com.test.util.Search;

public interface BoardDAO {

	// 게시판 글 목록 [전체]
	public List<DbDTO> list(Search search) throws Exception;
	
	// 게시판 글 목록 [카테고리]
	public List<DbDTO> Board_List(Search search) throws Exception;
	
	// 글 생성
	public void create(DbDTO dto) throws Exception;
	
	// 글 읽기
	public DbDTO read(int dno) throws Exception;
	
	// 글 삭제
	public void delete(int dno) throws Exception;
	
	// 글 수정
	public void modify(DbDTO dto) throws Exception;
	
	// 게시글의 총 갯수 [전체]
	public int getBoardListCnt(Search search) throws Exception;
	
	//게시글의 총 갯수 [카테고리]
	public int getBoardListCnt2(Search search) throws Exception;
	
	// 조회수 증가
	public void ViewCount(int dno) throws Exception;
	
	// db에 파일에 대한 정보 업로드
	public void insertFile(Map<String, Object> map) throws Exception;
	
	// 파일 리스트 조회
	public List<FileDTO> selectFileList(int dno) throws Exception;
	
	// 파일 다운로드
	public FileDTO selectFileInfo(FileDTO dto) throws Exception;
	
	// db에 있는 파일에 대한 정보를 삭제 
	public void deleteFile (int dno) throws Exception;
	
	// 수정폼에서 db에 있는 파일에 대한 정보를 삭제
	public void modifyDelete (int dno) throws Exception;
	
	// 수정폼에서 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴
	public String modifyDeleteServer(int dno) throws Exception;
	
	// 저저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴 
	public List<String> deleteServer(int dno) throws Exception;

	// 리플 작성
	public void writeReply(ReplyDTO dto) throws Exception;
	
	// 리플 수정
	public void updateReply(ReplyDTO dto) throws Exception;
	
	// 리플 삭제
	public void deleteReply(ReplyDTO dto) throws Exception;
	
	// 게시글 삭제시 해당 게시물에 작성된 리플도 같이 삭제 
	public void deleteReplyBoard(int dno) throws Exception;
	
	// 리플 리스트
    public List<ReplyDTO> commentList(int dno) throws Exception;

    // 작성중인 게시물의 첨부파일 존재 유무 [YES]
    public void file_yes(int dno) throws Exception;
    
    // 작성중인 게시물의 첨부파일 존재 유무 [NO]
    public void file_no(int dno) throws Exception;
 
    // 이미 작성된 게시물의 첨부파일 존재 유무
    public int file_exist(int dno) throws Exception;
    
    // 리플 카운트
    public int reply_cnt(int dno) throws Exception;
    
    // 리플 카운트한 결과를 테이블에 저장
    public void reply_cnt_store(DbDTO dto2) throws Exception;
}
