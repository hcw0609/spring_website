package com.test.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.test.dao.BoardDAO;
import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.ReplyDTO;
import com.test.util.FileUtils;
import com.test.util.Search;
import com.test.util.Thumbnail;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Inject
	private BoardDAO dao;
		
	// 글 목록
	@Override
	public List<DbDTO> list(Search search) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(search);
	}
	
	// 글 목록2
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
		
		// 이미지가 포함되어있는지 확인하자
		String str = dto.getContent();
		if (str.contains("<img")) {
			dto.setImageyn("y");
			// 썸네일 만들기
			Thumbnail tn = new Thumbnail();
			String Thumbnail_make = tn.Thumbnail_make(dto.getContent());
			dto.setThumbnail(Thumbnail_make);

		} else {
			dto.setImageyn("n");
			dto.setThumbnail("/Image/No_Image.gif");
		}
		
		// 글 생성
		dao.create(dto);				
			
		
		// 파일 업로드
		FileUtils fileutils = new FileUtils();
		List<Map<String,Object>> list = fileutils.parseInsertFileInfo(dto, mpRequest);
		int size = list.size();
				
		// 파일이 db에 존재하는지 안하는지
		if(size == 0 ) {
			dao.file_no(dto.getDno());
		} else {
			dao.file_yes(dto.getDno());
		}
		
		for (int i=0; i<size; i++) {
			dao.insertFile(list.get(i));
		}
		
	}

	// 글 보기
	@Override
	public DbDTO read(Integer no) throws Exception {
		// TODO Auto-generated method stub
		dao.ViewCount(no);
		return dao.read(no);
	}

	
	// 글 삭제
	@Override
	public void delete(Integer no) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(no);	
		dao.deleteFile(no);
	}

	
	// 글 수정
	@Override
	public void modify(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception {
		// TODO Auto-generated method stub
										
		// 이미지가 포함되어있는지 확인하자
		String str = dto.getContent();
		if (str.contains("<img")) {
			dto.setImageyn("y");
			// 썸네일 만들기
			Thumbnail tn = new Thumbnail();
			String Thumbnail_make = tn.Thumbnail_make(dto.getContent());
			dto.setThumbnail(Thumbnail_make);
		} else {
			dto.setImageyn("n");
			dto.setThumbnail("/Image/No_Image.gif");
		}
		
		// 글 수정
		dao.modify(dto);	
		
		// 파일 업로드
		FileUtils fileutils = new FileUtils();
		List<Map<String,Object>> list = fileutils.parseInsertFileInfo(dto, mpRequest);
		int size = list.size();
		
		// 파일이 db에 존재하는지 안하는지
		int file_exist = dao.file_exist(dto.getDno());
		if(file_exist == 0) {
			if(size == 0 ) {
				dao.file_no(dto.getDno());
			} else {
				dao.file_yes(dto.getDno());
			}
		} else {
			dao.file_yes(dto.getDno());
		}

		for (int i=0; i<size; i++) {
			dao.insertFile(list.get(i));
		}

	}


	// 총 갯수
	@Override
	public int getBoardListCnt(Search search) throws Exception {
		// TODO Auto-generated method stub		
		return dao.getBoardListCnt(search);
	}
	
	// 총 갯수2
	@Override
	public int getBoardListCnt2(Search search) throws Exception {
		// TODO Auto-generated method stub		
		return dao.getBoardListCnt2(search);
	}


	// 파일 조회
	@Override
	public List<FileDTO> selectFileList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileList(dno);
	}


	// 파일 업로드
	@Override
	public FileDTO selectFileInfo(FileDTO dto) throws Exception {
		// TODO Auto-generated method stub
		return dao.selectFileInfo(dto);
	}

	
	// 수정폼에서 파일 삭제
	@Override
	public void modifyDelete(int tagid) throws Exception {
		// TODO Auto-generated method stub
		dao.modifyDelete(tagid);
	}
	
	
	// 수정폼에서 삭제했을때 서버에 있는 파일도 삭제
	@Override
	public String modifyDeleteServer(int tagid) throws Exception {
		// TODO Auto-generated method stub
		return dao.modifyDeleteServer(tagid);
	}

	
	// 서버에서 파일 삭제
	@Override
	public List<String> deleteServer(int dno) throws Exception {
		// TODO Auto-generated method stub
		
		return dao.deleteServer(dno);
	}

	// 리플 작성
	@Override
	public void writeReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.writeReply(dto);		
	}

	
	// 리플 수정
	@Override
	public void updateReply(ReplyDTO dto) throws Exception {
		// TODO Auto-generated method stub
		dao.updateReply(dto);
	}


	// 리플 삭제
	@Override
	public void deleteReply(int rno) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteReply(rno);
	}


	// 게시글 삭제시 리플도 삭제
	@Override
	public void deleteReplyBoard(int dno) throws Exception {
		dao.deleteReplyBoard(dno);
	}

	
	// 리플 목록
	@Override
	public List<ReplyDTO> commentList(int dno) throws Exception {
		// TODO Auto-generated method stub
		return dao.commentList(dno);
	}
}
