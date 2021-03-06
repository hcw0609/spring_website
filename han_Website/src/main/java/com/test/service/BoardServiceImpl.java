package com.test.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dao.BoardDAO;
import com.test.dto.ClubDTO;
import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.Information_ReplyDTO;
import com.test.dto.ReplyDTO;
import com.test.util.File_Upload;
import com.test.util.Search;
import com.test.util.Thumbnail;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
		
	// 게시판 글 목록 [전체]
	@Override
	public List<DbDTO> list(Search search) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(search);
	}
	
	// 게시판 글 목록 [카테고리별]
	@Override
	public List<DbDTO> Board_List(Search search) throws Exception {
		// TODO Auto-generated method stub
		return dao.Board_List(search);
	}

	
	// 글 생성
	@Transactional
	@Override
	public void create(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception {
		// TODO Auto-generated method stub		
		
		// 작성중인 게시물에 이미지 포함 유무
		String str = dto.getContent();
		if (str.contains("<img")) {
			dto.setImageyn("y");
			
			// 썸네일 만들기
			Thumbnail tn = new Thumbnail();
			String Thumbnail_make = tn.Thumbnail_make(dto.getContent());
			dto.setThumbnail(Thumbnail_make);

		} else {
			dto.setImageyn("n");
			dto.setThumbnail("/Image/Icon/No_Image.gif");
		}
		
		// 글 생성
		dao.create(dto);				
					
		// 파일 업로드
		File_Upload fileutils = new File_Upload();
		List<Map<String,Object>> list = fileutils.parseInsertFileInfo(dto, mpRequest);
		int size = list.size();
				
		// 작성중인 게시물에 첨부파일의 존재 유무
		if(size == 0 ) {
			dao.file_no(dto.getDno());
		} else {
			dao.file_yes(dto.getDno());
		}		
		for (int i=0; i<size; i++) {
			// db에 파일에 대한 정보 업로드
			dao.insertFile(list.get(i));
		}
		
	}

	// 글 읽기
	@Override
	public DbDTO read(int dno) throws Exception {
		// TODO Auto-generated method stub
		
		// 조회수 증가
		dao.ViewCount(dno);

		// 글 읽기
		return dao.read(dno);
	}

	
	// 글 삭제
	@Override
	public void delete(int dno) throws Exception {
		// TODO Auto-generated method stub
		// 글 삭제
		dao.delete(dno);	
	}

	
	// 글 수정
	@Override
	public void modify(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception {
		// TODO Auto-generated method stub
										
		// 작성중인 게시물에 이미지 포함 유무
		String str = dto.getContent();
		if (str.contains("<img")) {
			dto.setImageyn("y");
			
			// 썸네일 만들기
			Thumbnail tn = new Thumbnail();
			String Thumbnail_make = tn.Thumbnail_make(dto.getContent());
			dto.setThumbnail(Thumbnail_make);
		} else {
			dto.setImageyn("n");
			dto.setThumbnail("/Image/Icon/No_Image.gif");
		}
		
		// 글 수정
		dao.modify(dto);	
		
		// 파일 업로드
		File_Upload fileutils = new File_Upload();
		List<Map<String,Object>> list = fileutils.parseInsertFileInfo(dto, mpRequest);
		int size = list.size();
		
		// 이미 작성된 게시물에 첨부파일의 존재 유무
		int file_exist = dao.file_exist(dto.getDno());
		if(file_exist == 0) {
			// 작성중인 게시물에 첨부파일의 존재 유무
			if(size == 0 ) {
				dao.file_no(dto.getDno());
			} else {
				dao.file_yes(dto.getDno());
			}
		} else {
			dao.file_yes(dto.getDno());
		}

		for (int i=0; i<size; i++) {
			// db에 파일에 대한 정보 업로드
			dao.insertFile(list.get(i));
		}

	}


	// 게시글의 총 갯수 [전체]
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub		
		return dao.getBoardListCnt(search);
	}
	
	// 게시글의 총 갯수 [카테고리 별]
	@Override
	public int getBoardListCnt2(Search search) throws Exception {
		// TODO Auto-generated method stub		
		return dao.getBoardListCnt2(search);
	}
	
	// 날자별 작성된 게시글
	@Override
	public int board_count_day(String str) throws Exception {
		// TODO Auto-generated method stub
		return dao.board_count_day(str);
	}


	// 파일 리스트 조회
	@Override
	public List<FileDTO> selectFileList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileList(dno);
	}


	// 파일 다운로드
	@Override
	public FileDTO selectFileInfo(FileDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(dto);
	}

	
	// 수정폼에서 db에 있는 파일에 대한 정보를 삭제
	@Override
	public void modifyDelete(int file_no) throws Exception {
		// TODO Auto-generated method stub
		dao.modifyDelete(file_no);
	}
	
	
	// 수정폼에서 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴
	@Override
	public String modifyDeleteServer(int file_no) throws Exception {
		// TODO Auto-generated method stub
		return dao.modifyDeleteServer(file_no);
	}

	
	// 글 삭제시 해당 글번호의 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴 
	@Override
	public List<String> deleteServer(int dno) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.deleteServer(dno);
	}

	// 리플 작성 [게시글]
	@Override
	public void writeReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		// 리플 작성
		dao.writeReply(dto);
		
		// 리플 카운트
		int reply_cnt = dao.reply_cnt(dto.getDno());
						
		// 리플 카운트한 결과를 테이블에 저장
		DbDTO dto2 = new DbDTO();
		dto2.setReply_cnt(reply_cnt);
		dto2.setDno(dto.getDno());
		dao.reply_cnt_store(dto2);
	}
	
	// 리플 작성  [축구정보]
	@Override
	public void soccer_writeReply(Information_ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
	    // 리플 작성
		dao.soccer_writeReply(dto);
		
		// 리플 카운트
		int reply_cnt = dao.soccer_reply_cnt(dto.getClub_name());
		
		// 리플 카운트한 결과를 테이블에 저장
		ClubDTO dto2 = new ClubDTO();
		dto2.setClub_reply_cnt(reply_cnt);
		dto2.setClub_name(dto.getClub_name());
		dao.soccer_reply_cnt_store(dto2);			
	}

	
	// 리플 수정 [게시글]
	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.updateReply(dto);
	}
	
	// 리플 수정 [축구정보]
	@Override
	public void soccer_updateReply(Information_ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.soccer_updateReply(dto);
	}


	// 리플 삭제 [게시글]
	@Override
	public void deleteReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		// 리플 삭제
		dao.deleteReply(dto);
		
		// 리플 카운트
		int reply_cnt = dao.reply_cnt(dto.getDno());
								
		// 리플 카운트한 결과를 테이블에 저장
		DbDTO dto2 = new DbDTO();
		dto2.setReply_cnt(reply_cnt);
		dto2.setDno(dto.getDno());
		dao.reply_cnt_store(dto2);
	}
	
	// 리플 삭제 [축구정보]
	@Override
	public void soccer_deleteReply(Information_ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.soccer_deleteReply(dto);
		
		// 리플 카운트
		int reply_cnt = dao.soccer_reply_cnt(dto.getClub_name());
		
		// 리플 카운트한 결과를 테이블에 저장
		ClubDTO dto2 = new ClubDTO();
		dto2.setClub_reply_cnt(reply_cnt);
		dto2.setClub_name(dto.getClub_name());
		dao.soccer_reply_cnt_store(dto2);		
	}

	// 전체 리플 카운트 [게시글]
	@Override
	public int reply_allcnt() throws Exception {
		// TODO Auto-generated method stub
		return dao.reply_allcnt();
	}

	
	// 리플 리스트 [게시글]
	@Override
	public List<ReplyDTO> commentList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return dao.commentList(dno);
	}
	
	
	// 리플 리스트 [축구정보]
	@Override
	public List<Information_ReplyDTO> soccer_commentList(String club_name) throws Exception {
		// TODO Auto-generated method stub
		return dao.soccer_commentList(club_name);
	}
	
	
	// 날짜별 작성된 리플
	@Override
	public int reply_count_day(String str) throws Exception {
		// TODO Auto-generated method stub
		return dao.reply_count_day(str);
	}


}
