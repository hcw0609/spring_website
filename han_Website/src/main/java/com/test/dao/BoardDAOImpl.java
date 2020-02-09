package com.test.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.ReplyDTO;
import com.test.util.Search;

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
	public DbDTO read(int dno) throws Exception {
		// TODO Auto-generated method stub
		
		return sql.selectOne(namespace+".read", dno);
	}

	
	// 글 삭제
	@Override
	public void delete(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace+".delete", dno);
		
	}

	
	// 글 수정
	@Override
	public void modify(DbDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace+".modify", dto);
		
	}


	// 게시글의 총 갯수 1
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".getBoardListCnt",search);
	}
	
	
	// 게시글의 총 갯수 2
	@Override
	public int getBoardListCnt2(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".getBoardListCnt2",search);
	}


	// 조회수 증가
	@Override
	public void ViewCount(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".viewcnt", dno);
	}


	// db에 파일에 대한 정보 업로드
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".insertFile", map);
		
	}


	// 파일 리스트 조회
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


	// db에 있는 파일에 대한 정보를 삭제 
	@Override
	public void deleteFile(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".deleteFile", dno);
	}


	// 수정폼에서 db에 있는 파일에 대한 정보를 삭제
	@Override
	public void modifyDelete(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".modifyDelete", dno);
	}
	
	
	// 수정폼에서 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴
	@Override
	public String modifyDeleteServer(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".modifyDeleteServer", dno);
	}
	

	// 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴 
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
	public void deleteReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace +".deleteReply", dto);
	}


	// 게시글 삭제시 해당 게시물에 작성된 리플도 같이 삭제 
	@Override
	public void deleteReplyBoard(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace + ".deleteReplyBoard", dno);
	}


	// 리플 리스트
	@Override
	public List<ReplyDTO> commentList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace +".commentList", dno);
	}


	// 작성중인 게시물의 첨부파일 존재 유무 [YES]
	@Override
	public void file_yes(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".file_yes", dno);
	}


	// 작성중인 게시물의 첨부파일 존재 유무 [NO]
	@Override
	public void file_no(int dno) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".file_no", dno);
	}


	// 이미 작성된 게시물의 첨부파일 존재 유무
	@Override
	public int file_exist(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".file_exist", dno);
	}


	// 리플 카운트
	@Override
	public int reply_cnt(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".reply_cnt", dno);
	}


	// 리플 카운트한 결과를 테이블에 저장
	@Override
	public void reply_cnt_store(DbDTO dto2) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".reply_cnt_store", dto2);
	}
	
	
	
	

}
