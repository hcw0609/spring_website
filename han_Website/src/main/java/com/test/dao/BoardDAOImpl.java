package com.test.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.test.dto.ClubDTO;
import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.Information_ReplyDTO;
import com.test.dto.ReplyDTO;
import com.test.util.Search;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sql;
	
	private static String namespace = "Mapper";
	
	
	// 게시판 글 목록 [전체]
	@Override
	public List<DbDTO> list(Search search) throws Exception {
		// TODO Auto-generated method stub
		
		return sql.selectList(namespace+".List", search);
	}
	
	
	// 게시판 글 목록 [카테고리별]
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


	// 게시글의 총 갯수 [전체]
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace+".getBoardListCnt",search);
	}
	
	
	// 게시글의 총 갯수 [카테고리 별]
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
	
	
	// 날자별 작성된 게시글
	@Override
	public int board_count_day(String str) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".board_count_day", str);
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


	// 수정폼에서 db에 있는 파일에 대한 정보를 삭제
	@Override
	public void modifyDelete(int file_no) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace + ".modifyDelete", file_no);
	}
	
	
	// 수정폼에서 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴
	@Override
	public String modifyDeleteServer(int file_no) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".modifyDeleteServer", file_no);
	}
	

	// 글 삭제시 해당 글번호의 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴 
	@Override
	public List<String> deleteServer(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".deleteServer", dno);
	}
	
	// 리플 작성 [게시글]
	@Override
	public void writeReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".writeReply", dto); 
	}
	
	
	// 리플 작성 [축구정보]
	@Override
	public void soccer_writeReply(Information_ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".soccer_writeReply", dto);
	}


	// 리플 수정 [게시글]
	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace +".updateReply", dto);
		
	}
	
	// 리플 수정 [축구정보]
	@Override
	public void soccer_updateReply(Information_ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.update(namespace +".soccer_updateReply", dto);
		
	}

	
	// 리플 삭제 [게시글]
	@Override
	public void deleteReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace +".deleteReply", dto);
	}
	
	
	// 리플 삭제 [축구정보]
	@Override
	public void soccer_deleteReply(Information_ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.delete(namespace +".soccer_deleteReply", dto);
	}
	
	// 리플 리스트 [게시글]
	@Override
	public List<ReplyDTO> commentList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace +".commentList", dno);
	}
	
	
	// 리플 리스트 [축구정보]
	@Override
	public List<Information_ReplyDTO> soccer_commentList(String club_name) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectList(namespace + ".soccer_commentList",club_name);
	}

	
	// 전체 리플 카운트 [게시글]
	@Override
	public int reply_allcnt() throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace +".reply_allcnt");
	}
		
	
	// 리플 카운트 [게시글]
	@Override
	public int reply_cnt(int dno) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".reply_cnt", dno);
	}

	
	// 리플 카운트 [축구정보]
	@Override
	public int soccer_reply_cnt(String club_name) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace + ".soccer_reply_cnt", club_name);
	}

	
	// 리플 카운트한 결과를 게시글 테이블에 저장
	@Override
	public void reply_cnt_store(DbDTO dto2) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".reply_cnt_store", dto2);
	}	
	
	// 리플 카운트한 결과를 축구 정보 테이블에 저장
	@Override
	public void soccer_reply_cnt_store(ClubDTO dto) throws Exception {
		// TODO Auto-generated method stub
		sql.insert(namespace + ".soccer_reply_cnt_store", dto);
	}

	
	// 날짜별 작성된 리플
	@Override
	public int reply_count_day(String str) throws Exception {
		// TODO Auto-generated method stub
		return sql.selectOne(namespace +".reply_count_day", str);
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


}
