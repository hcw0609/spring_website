package com.test.dao;

import java.beans.Statement;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.ReplyDTO;
import com.test.paging.Search;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "Mapper";
	
	
	// 게시물 목록
	@Override
	public List<DbDTO> list(Search search) throws Exception {
		// TODO Auto-generated method stub
		
		return sql.selectList(namespace+".List", search);
	}
	
	
	// 게시물 목록2
	@Override
	public List<DbDTO> Board_List(Search search) throws Exception {
		// TODO Auto-generated method stub

		return sql.selectList(namespace+".Board_List", search);
	}

	
	// 글 생성
	@Override
	public void create(DbDTO dto) throws Exception {
		// TODO Auto-generated method stub
		
		sql.insert(namespace + ".insert" , dto);
		
	}

	
	// 글 읽기
	@Override
	public DbDTO read(Integer no) throws Exception {
		// TODO Auto-generated method stub
		
		return sql.selectOne(namespace+".read", no);
	}

	
	// 글 삭제
	@Override
	public void delete(Integer no) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace+".delete", no);
		
	}

	
	// 글 수정
	@Override
	public void modify(DbDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace+".modify", dto);
		
	}


	// 총 갯수
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".getBoardListCnt",search);
	}
	
	
	// 총 갯수2
	@Override
	public int getBoardListCnt2(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".getBoardListCnt2",search);
	}


	// 조회수
	@Override
	public void ViewCount(Integer no) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".viewcnt", no);
	}


	// 파일 업로드
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".insertFile", map);
		
	}


	// 파일 조회
	@Override
	public List<FileDTO> selectFileList(int dno) throws Exception {
		// TODO Auto-generated method stub
		
		return sql.selectList(namespace + ".selectFileList", dno);
	}

	
	// 파일 다운로드
	@Override
	public FileDTO selectFileInfo(FileDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".selectFileInfo", dto);
	}


	// 파일 삭제
	@Override
	public void deleteFile(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".deleteFile", dno);
	}


	// 수정폼에서 파일 삭제
	@Override
	public void modifyDelete(int tagid) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".modifyDelete", tagid);
	}
	
	
	// 수정폼에서의  파일을 서버에서 삭제
	@Override
	public String modifyDeleteServer(int tagid) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".modifyDeleteServer", tagid);
	}
	

	// 파일을 서버에서 삭제
	@Override
	public List<String> deleteServer(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".deleteServer", dno);
	}
	
	// 리플 작성
	@Override
	public void writeReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".writeReply", dto); 
	}


	// 리플 수정
	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace +".updateReply", dto);
		
	}

	
	// 리플 삭제
	@Override
	public void deleteReply(int rno) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace +".deleteReply", rno);
	}


	// 게시글 삭제시 리플도 삭제
	@Override
	public void deleteReplyBoard(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".deleteReplyBoard", dno);
	}


	// 리플 목록
	@Override
	public List<ReplyDTO> commentList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace +".commentList", dno);
	}


	// 첨부파일이 존재하는 게시글
	@Override
	public void file_yes(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".file_yes", dno);
	}


	// 첨부파일이 존재하지 않는 게시글
	@Override
	public void file_no(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".file_no", dno);
	}


	// 파일이 db에 존재하는지 안하는지
	@Override
	public int file_exist(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".file_exist", dno);
	}
	
	
	
	

}
