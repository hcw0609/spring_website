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

	// 게시판 글 목록
	public List<DbDTO> list(Search search) throws Exception;
	
	// 게시판 글 목록2
	public List<DbDTO> Board_List(Search search) throws Exception;
	
	// 글 생성
	public void create(DbDTO dto) throws Exception;
	
	// 글 읽기
	public DbDTO read(Integer no) throws Exception;
	
	// 글 삭제
	public void delete(Integer no) throws Exception;
	
	// 글 수정
	public void modify(DbDTO dto) throws Exception;
	
	// 총 갯수
	public int getBoardListCnt(Search search) throws Exception;
	
	// 총 갯수2
	public int getBoardListCnt2(Search search) throws Exception;
	
	// 조회수
	public void ViewCount(Integer no) throws Exception;
	
	// 파일 업로드
	public void insertFile(Map<String, Object> map) throws Exception;
	
	// 파일 조회
	public List<FileDTO> selectFileList(int dno) throws Exception;
	
	// 파일 다운로드
	public FileDTO selectFileInfo(FileDTO dto) throws Exception;
	
	// 파일 삭제
	public void deleteFile (int dno) throws Exception;
	
	// 수정폼에서 파일 삭제
	public void modifyDelete (int tagid) throws Exception;
	
	// 수정폼에서의  파일을 서버에서 삭제
	public String modifyDeleteServer(int tagid) throws Exception;
	
	// 파일을 서버에서 삭제
	public List<String> deleteServer(int dno) throws Exception;

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

    // 첨부파일이 존재하는 게시글
    public void file_yes(int dno) throws Exception;
    
    // 첨부파일이 존재하지 않는 게시글
    public void file_no(int dno) throws Exception;
 
    // 파일이 db에 존재하는지 안하는지
    public int file_exist(int dno) throws Exception;
}
