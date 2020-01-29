package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.activation.CommandMap;
import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.ReplyDTO;
import com.test.dto.UserDTO;
import com.test.paging.Search;
import com.test.service.BoardService;
import com.test.service.UserService;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	@Inject
	private UserService service1;
	
	@Inject
	JavaMailSender mailSender;
	
	// 글 목록1
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void  getList(Search search,
						Model model,
						HttpSession hs) throws Exception {

		// 검색
		search.setKeyword(search.getKeyword());
		search.setSearchType(search.getSearchType());
		
		// 페이징
		int listCnt = service.getBoardListCnt(search);
		search.Paging(listCnt, search.getCurPage());
		
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		
		// 글 목록 꺼내오기
		List<DbDTO> list = service.list(search);
				
		// 모델
		model.addAttribute("paging", search);
		model.addAttribute("list", list);
		model.addAttribute("loginInfo", loginInfo);
		
	}
	
	
	// 글 목록2
	@RequestMapping(value="/Board_List/soccer", method = RequestMethod.GET)
	public void  getoverseas_soccer(Search search,
									Model model, 
									HttpSession hs) throws Exception {

		// 검색
		search.setKeyword(search.getKeyword());
		search.setSearchType(search.getSearchType());
				
		// 페이징
		int listCnt = service.getBoardListCnt2(search);
		search.Paging(listCnt, search.getCurPage());
				
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
				
		// 글 목록 꺼내오기
		List<DbDTO> list = service.Board_List(search);
						
		// 모델
		model.addAttribute("paging", search);
		model.addAttribute("list", list);
		model.addAttribute("loginInfo", loginInfo);
			
	}

	
	// 글 생성
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getCreate (Model model, HttpSession hs) throws Exception {
		
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		// 모델
		model.addAttribute("loginInfo", loginInfo);	
		
		// 비정상적인 접근 막기
		try {
			loginInfo.getID();			
		} catch (Exception e) {
			return "redirect:/board/Wrong_approach";
		}

		return "/board/create";
	}
	
	
	// 글 생성
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String postCreate ( DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception{	
		
		// 글 생성
		service.create(dto, mpRequest);
				
		return "redirect:/board/list";
	}
	
	
	// 글 보기
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public void getRead (@RequestParam("dno") int dno,
				         Model model, HttpSession hs) throws Exception {
		
		// 글 내용 가져오기
		DbDTO dto = service.read(dno);
		
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		// 파일조회
		List<FileDTO> fileList = service.selectFileList(dno);

		// 모델
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("read", dto);
		model.addAttribute("file",fileList);
	}
	
	
	// 글 수정
	@RequestMapping(value="/modify", method = RequestMethod.GET)	
	public String getModify(@RequestParam("dno") int dno, Model model, HttpSession hs) throws Exception {
				
		// 글 읽어오기
		DbDTO dto = service.read(dno);
		
		// 파일 읽어오기
		List<FileDTO> fileList = service.selectFileList(dno);
		
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		// 모델
		model.addAttribute("modify", dto);		
		model.addAttribute("file",fileList);

		// 비정상적인 접근 막기
		try {
			if ( !loginInfo.getID().equals(dto.getWriter()) ) {
				System.out.println(loginInfo.getID()+""+dto.getWriter());
				return "redirect:/board/Wrong_approach";
			}
		} catch (Exception e) {
			return "redirect:/board/Wrong_approach";
		}
		return "/board/modify";
	}
	
	
	// 글 수정
	@RequestMapping(value="/modify", method = RequestMethod.POST)	
	public String postModify(DbDTO dto, MultipartHttpServletRequest mpRequest) throws Exception {
		
		// 글 수정
		service.modify(dto, mpRequest);
			
		return "redirect:/board/read?dno="+dto.getDno();		
	}
	
	
	// 글 삭제
	@RequestMapping(value="/delete", method = RequestMethod.GET )
	public String getDelete(DbDTO dto, HttpSession hs) throws Exception {
			
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
						
		// 비정상적인 접근 막기
		try {
			if( loginInfo.getID().equals(dto.getWriter()) ) {
				
				// 게시글 삭제시 리플도 삭제
				service.deleteReplyBoard(dto.getDno());
				
				
				//나의 컴퓨터를 서버로 이용할 때
				List<String> deleteServerFile = service.deleteServer(dto.getDno());
				for(int i = 0; i<deleteServerFile.size(); i++) {
					String storedFileName = (String) deleteServerFile.get(i);			
					File file = new File("C:\\mp\\file\\"+storedFileName);
					if(file.exists() == true){
						file.delete();

					}
				}
				
				
				/*
				// for ftp
				List<String> deleteServerFile = service.deleteServer(dto.getDno());
				for(int i = 0; i<deleteServerFile.size(); i++) {
					String storedFileName = (String) deleteServerFile.get(i);			
					File file = new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName);
					if(file.exists() == true){
						file.delete();

					}
				}
				*/
				
				// 게시글 삭제
				service.delete(dto.getDno());
				return "redirect:/board/list";
			} else {
				return "redirect:/board/Wrong_approach";
			}
		} catch (Exception e) {
			return "redirect:/board/Wrong_approach";
		}				
	}
	
	
	// 로그인
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void getLogin() throws Exception{

	}
	
	
	// 로그인 
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String postLogin(UserDTO userdto, HttpServletRequest hsr) throws Exception{
		
		// 세션을 가져온다.
		HttpSession hs = hsr.getSession();
		
		UserDTO login = service1.login(userdto);
				
		if( login == null) {
				return  "redirect:/board/login";
		} else {
			//"User" 이라는 이름으로 login을 세션에 바인딩 시킨다. 
			hs.setAttribute("User", login);
				return "redirect:/board/list";
		}		
	}
	
	
	// 회원가입
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void getRegister() throws Exception{
				
	}
		
		
	// 회원가입
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String postRegister(UserDTO userdto) throws Exception{
		
		// 아이디 중복 체크 한 결과값을 저장
		int result = service1.overLap(userdto);
		
		try {
			if( result == 1) {
				
			} 
			else if ( result == 0) {
				// 회원가입 
				service1.register(userdto);
				return "redirect:/board/login";			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
				
		return "redirect:/board/register";
	}
	
	
	
	// ID 중복체크
	@ResponseBody
	@RequestMapping(value="/overLap", method = RequestMethod.POST)
	public int postOverLap(UserDTO userdto) throws Exception{
		
		// 아이디 중복 체크 한 결과값을 저장
		int result = service1.overLap(userdto);
		
		return result; 		
	}
	
	
	// 로그아웃
	@RequestMapping(value="/logout")
	public ModelAndView Logout(HttpServletRequest hsr) throws Exception {
		
		// User이라는 세션을 제거
		hsr.getSession().removeAttribute("User");
		
		// 모델
		ModelAndView mav = new ModelAndView("/board/logout"); 
		mav.addObject("msg", "로그아웃 되셨습니다.");
		
		return mav;
	}	
	
	
	
	// 이미지 업로드
	@RequestMapping(value = "/ckUpload", method = RequestMethod.POST)
	public void postCKEditorImgUpload(@RequestParam MultipartFile upload,
									  HttpServletRequest req,
									  HttpServletResponse res) throws Exception {
	 
	 // 랜덤 문자 생성
	 UUID uid = UUID.randomUUID();
	 
	 OutputStream out = null;
	 
	 // 자바에서 웹으로 데이터를 출력하기 위해
	 PrintWriter printWriter = null;
	   
	 // 인코딩
	 res.setCharacterEncoding("utf-8");
	 res.setContentType("text/html;charset=utf-8");
	 
	 try {
	  // 파일 이름, 파일크기
	  String fileName = upload.getOriginalFilename();
	  byte[] bytes = upload.getBytes();
	  
	  	  
	  //나의 컴퓨터를 서버로 이용할 때
	  String path = "C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\han_spring\\resources";
	  String ckUploadPath =  path + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	  
		
	  /*
	  // for ftp
	  String path = "/hcw0609/tomcat/webapps/ROOT/resources";
	  String ckUploadPath =  path + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	  */
	  
	  
	  // 업로드 경로에  out에 저장된 데이터를 전송하고 초기화
	  out = new FileOutputStream(new File(ckUploadPath));
	  out.write(bytes);
	  out.flush();

	  
	  printWriter = res.getWriter();
	  String fileUrl = "/ckUpload/" + uid + "_" + fileName;  // 작성화면

	  
	  // json 데이터로 등록 아래와 같은 형태로 리턴이 나가야함.
      // {"uploaded" : 1, "fileName" : "test.jpg", "url" : "/img/test.jpg"}
      JsonObject json = new JsonObject();
      json.addProperty("uploaded", 1);
      json.addProperty("fileName", fileName);
      json.addProperty("url", fileUrl);
	  
      
      printWriter.println(json);
       
	 } catch (IOException e) { e.printStackTrace();
	 } finally {
	  try {
	   if(out != null) { out.close(); }
	  } catch(IOException e) { e.printStackTrace(); }
	 }
	 
	 return; 
	}
	
	
	// 파일 다운로드
	@ResponseBody
	@RequestMapping(value="/fileDown", method = RequestMethod.POST)
	public void fileDown(FileDTO dto,
					     HttpServletResponse response) throws Exception{
		
		
		String originalFileName = service.selectFileInfo(dto).getOrg_file_name();
		String storedFileName = service.selectFileInfo(dto).getStored_file_name();
		
		
		//파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다. [나의 컴퓨터를 서버로 이용할 때]
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\mp\\file\\"+storedFileName));
		
		
		/*
		// for ftp
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName));
		*/
		
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();	
		
	}
	
	
	// 수정폼에서 서버에 있는 파일 삭제
	@ResponseBody
	@RequestMapping(value="/modifyDelete", method = RequestMethod.POST)
	public void postModifyDelte(@RequestParam("tagid") int tagid ) throws Exception {
			
		// 서버에서 파일 삭제
		String deleteServerFile = service.modifyDeleteServer(tagid);
		String storedFileName = deleteServerFile;	
		
		
		//나의 컴퓨터를 서버로 이용할 때
		File file = new File("C:\\mp\\file\\"+storedFileName);
		
		
		/*
		// for ftp
		File file = new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName);
		*/
		
		
		// 파일이 존재한다면 삭제해라.
		if(file.exists() == true){
		
			file.delete();

		}
		
		// tagid에 해당하는 파일을 db에서 삭제
		service.modifyDelete(tagid);
		
	}
	
	
	// 리플 작성
	@ResponseBody
	@RequestMapping(value="/replyWrite", method = RequestMethod.POST)
	public void replyWrite( ReplyDTO dto) throws Exception {
	
		// 리플 작성
		service.writeReply(dto);	
	}
	
	
	// 리플 삭제
	@ResponseBody
	@RequestMapping(value="replyDelete", method = RequestMethod.POST)
	public void replyDelete(@RequestParam("rno") int rno) throws Exception {
		
		service.deleteReply(rno);
	}
	
	
	// 리플 목록
    @ResponseBody
    @RequestMapping(value="/rePlyList", method = RequestMethod.GET )
    public List<ReplyDTO> rePlyList(@RequestParam("dno") int dno, Model model) throws Exception{
        
    	// 가져온 리플 목록을 reply에 저장
    	List<ReplyDTO> reply = service.commentList(dno);
    	
    	// 모델
    	model.addAttribute("reply", reply);
    	
        return service.commentList(dno);
    }
    
    
    // 리플 수정
    @ResponseBody
    @RequestMapping(value="/replyUpdate",  method = RequestMethod.POST)
    private int replyUpdate( ReplyDTO dto ) throws Exception{
        
		service.updateReply(dto);
        		
        return 1;
    }

    
    // 이메일 인증
    @ResponseBody
    @RequestMapping(value = "/auth" , method=RequestMethod.POST )
    public int mailSending(@RequestParam("email") String email) throws IOException {

    	// 인증코드생성
        Random r = new Random();
        int dice = r.nextInt(10000) + 10000;
        
        // 보내는 사람의 이메일
        String setfrom = "hcw0609@gamil.com";
        
        // 받는 사람의 이메일
        String tomail = email;
        
        // 이메일 제목
        String title = "회원가입 인증 이메일 입니다.";
        
        // 이메일 내용
        String content =      
        System.getProperty("line.separator")+ //한줄씩 줄간격을 두기위해 작성
        System.getProperty("line.separator")+
        "안녕하세요 회원님 저희 홈페이지를 찾아주셔서 감사합니다"
        +System.getProperty("line.separator")+
        System.getProperty("line.separator")+
        " 인증번호는 " + dice + " 입니다. ";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message,
                    true, "UTF-8");

            messageHelper.setFrom(setfrom); 	// 보내는사람 생략하면 정상작동을 안함
            messageHelper.setTo(tomail); 		// 받는사람 이메일
            messageHelper.setSubject(title); 	// 메일제목은 생략이 가능하다
            messageHelper.setText(content); 	// 메일 내용
            
            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e);
        }
              
        return dice;        
    }	
    
    
    
    // 비정상적인 접근 막기
    @RequestMapping(value = "/Wrong_approach" , method=RequestMethod.GET )
    public void Wrong_approach() throws Exception {
    	
    }
    
}


