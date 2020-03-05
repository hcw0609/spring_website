package com.test.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.test.dto.ClubDTO;
import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.Information_ReplyDTO;
import com.test.dto.ReplyDTO;
import com.test.dto.UserDTO;
import com.test.dto.User_good_bad;
import com.test.dto.VisitorDTO;
import com.test.service.BoardService;
import com.test.service.ClubService;
import com.test.service.UserService;
import com.test.util.Double_check;
import com.test.util.Maputil;
import com.test.util.Redirect;
import com.test.util.Search;
import com.test.util.Soccer;
import com.test.util.UserCheck;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Inject
	private BoardService service;
	
	@Inject
	private UserService service1;
	
	@Inject
	private ClubService service2;
	
	@Inject
	private JavaMailSender mailSender;
	
	/*
	@Inject
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Inject
	private OAuth2Parameters googleOAuth2Parameters;
	*/
	
	// 글 목록 [통합 게시판]
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void  getList(Search search, Model model,HttpSession hs) throws Exception {
		// 검색
		search.setKeyword(search.getKeyword());
		search.setSearchType(search.getSearchType());
		
		// 페이징
		int post_cnt = service.getBoardListCnt(search);
		search.Paging(post_cnt, search.getCur_page());
		
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
	
	
	// 글 목록2 [해외축구, 국내축구, 자유게시판]
	@RequestMapping(value="/Board_List/soccer", method = RequestMethod.GET)
	public void  getoverseas_soccer(Search search, Model model, HttpSession hs) throws Exception {
		// 검색
		search.setKeyword(search.getKeyword());
		search.setSearchType(search.getSearchType());
				
		// 페이징
		int post_cnt = service.getBoardListCnt2(search);	
		search.Paging(post_cnt, search.getCur_page());
		
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
				
		// 글 목록 꺼내오기
		List<DbDTO> list = service.Board_List(search);
		
		// 카테고리 읽어오기
		String category = search.getCategory();
						
		// 모델
		model.addAttribute("paging", search);
		model.addAttribute("list", list);
		model.addAttribute("loginInfo", loginInfo);
		model.addAttribute("category", category);			
	}

	
	// 글 생성
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String getCreate (Model model, HttpSession hs) throws Exception {
		
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		// 모델
		model.addAttribute("loginInfo", loginInfo);	
		
		// 로그인유저의 접근 권한 체크
		// 로그인 YES, 로그인 NO
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(loginInfo);	
		if(result == "OK") {
			return "/board/create";
		} else {
			return "redirect:/board/needlogin";			
		}
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
	public void getRead (@RequestParam("dno") int dno, Model model, HttpSession hs) throws Exception {
		
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
		
		// 로그인유저의 접근 권한 체크
		// 로그인 YES, 로그인 NO, 로그인 유저와 작성자유저의 id가 다름
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(dto.getWriter(), loginInfo);	
		if(result == "OK") {
			return "/board/modify";
		} else if (result == "NullSession") {
			return "redirect:/board/needlogin";
		} else {
			return "redirect:/board/Wrong_approach";
		}
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
		
		// 로그인유저의 접근 권한 체크
		// 로그인 YES, 로그인 NO, 로그인 유저와 작성자유저의 id가 다름
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(dto.getWriter(), loginInfo);	
		if(result == "OK") {
			
			// 게시글 삭제시 해당 게시물에 작성된 리플도 같이 삭제
			service.deleteReplyBoard(dto.getDno());
			
			/*
			//나의 컴퓨터를 서버로 이용할 때 저장공간에 있는 파일 삭제
			List<String> deleteServerFile = service.deleteServer(dto.getDno());
			for(int i = 0; i<deleteServerFile.size(); i++) {
				String storedFileName = (String) deleteServerFile.get(i);			
				File file = new File("C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\han_Website\\resources\\mp_file\\"+storedFileName);
				if(file.exists() == true){
					file.delete();

				}
			}
			*/			
			
			// 호스팅받은 서버를 이용할 때 파일 삭제
			List<String> deleteServerFile = service.deleteServer(dto.getDno());
			for(int i = 0; i<deleteServerFile.size(); i++) {
				String storedFileName = (String) deleteServerFile.get(i);			
				File file = new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName);
				if(file.exists() == true){
					file.delete();

				}
			}
						
			// 게시글 삭제
			service.delete(dto.getDno());		
			return "redirect:/board/list";
		} else if (result == "NullSession") {
			return "redirect:/board/needlogin";
		} else {
			return "redirect:/board/Wrong_approach";
		}	
	}
	
	// 로그인
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String getLogin(HttpSession hs, Model model) throws Exception{
		
		/*
		// 구글 code 발행을 위한 URL 생성 
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		
		model.addAttribute("google_url", url);
		*/
		
		// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		if( loginInfo != null) {
			return "redirect:/board/list";
		}
		
		return "/board/login";
	}
	
	
	// 로그인 
	@ResponseBody
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String postLogin(UserDTO userdto, HttpServletRequest req) throws Exception{
	
		// 세션을 가져온다.
		HttpSession hs = req.getSession();
		
		// 가져온 아이디 비밀번호가 실제로 존재하는지 db에서 확인
		UserDTO login = service1.login(userdto);
		
		if( login == null) {
			return "no";
		} else if ( login.getID().equals("admin")) {
			//"User" 이라는 이름으로 login을 세션에 바인딩 시킨다. 
			// 관리자의 로그인
			hs.setAttribute("User", login);
			return "admin";
		} else {
			// 일반유저의 로그인
			hs.setAttribute("User", login);
			return "yes";
		}		
	}
	
	
	// 회원가입
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void getRegister(Model model, @RequestParam(value="email", required=false) String email ) throws Exception{
		
		model.addAttribute("email", email);
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
	public ModelAndView Logout(HttpServletRequest req) throws Exception {
		
		// User이라는 세션을 제거
		req.getSession().removeAttribute("User");
		
		// 모델
		ModelAndView mav = new ModelAndView("/board/logout"); 
		mav.addObject("msg", "로그아웃 되셨습니다.");
		
		return mav;
	}	
	
	
	
	// 이미지 업로드
	@RequestMapping(value = "/ckUpload", method = RequestMethod.POST)
	public void postCKEditorImgUpload(@RequestParam MultipartFile upload,
									HttpServletRequest req, HttpServletResponse res) throws Exception {
	 
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

	  /*
	  // 나의 컴퓨터를 서버로 이용할 때
	  String path = "C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\han_Website\\resources";
	  String ckUploadPath =  path + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;	  	
	  */
	  
	  // 호스팅받은 서버를 이용할 때
	  String path = "/hcw0609/tomcat/webapps/ROOT/resources";
	  String ckUploadPath =  path + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	 	    
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
	public void fileDown(FileDTO dto, HttpServletResponse res) throws Exception{
				
		String originalFileName = service.selectFileInfo(dto).getOrg_file_name();
		String storedFileName = service.selectFileInfo(dto).getStored_file_name();
		
		/*
		//파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다. [나의 컴퓨터를 서버로 이용할 때]
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\han_Website\\resources\\mp_file\\"+storedFileName));
		*/
			
		// 호스팅받은 서버를 이용할 때
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName));
					
		res.setContentType("application/octet-stream");
		res.setContentLength(fileByte.length);
		res.setHeader("Content-Disposition",  "attachment; fileName=\""+URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		res.getOutputStream().write(fileByte);
		res.getOutputStream().flush();
		res.getOutputStream().close();			
	}
	
	
	// 수정폼에서 서버에 있는 파일 삭제
	@ResponseBody
	@RequestMapping(value="/modifyDelete", method = RequestMethod.POST)
	public void postModifyDelte(@RequestParam("tagid") int dno ) throws Exception {
			
		// 수정폼에서 저장공간에 있는 파일을 삭제하기 위해 저장된파일의 이름을 리턴
		String deleteServerFile = service.modifyDeleteServer(dno);
		String storedFileName = deleteServerFile;	
		
		/*
		//나의 컴퓨터를 서버로 이용할 때 수정폼에서 저장공간에 있는 파일을 삭제
		File file = new File("C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\han_Website\\resources\\mp_file\\"+storedFileName);   
		*/
		
		
		// 호스팅받은 서버를 이용할 때 수정폼에서 저장공간에 있는 파일을 삭제
		File file = new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName);
		
		
		
		// 파일이 존재한다면 삭제해라.
		if(file.exists() == true){		
			file.delete();
		}
		
		// 수정폼에서 db에 있는 파일에 대한 정보를 삭제 
		service.modifyDelete(dno);
		
	}
	
	
	// 리플 작성
	@ResponseBody
	@RequestMapping(value="/replyWrite", method = RequestMethod.POST)
	public String replyWrite( ReplyDTO dto, Information_ReplyDTO dto2, HttpSession hs) throws Exception {
	
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
		
		// 로그인유저의 접근 권한 체크
		// 로그인 YES, 로그인 NO
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(loginInfo);	
		
		// 게시판 댓글 작성
		if ( dto2.getClub_name() == null ) {
			if(result == "OK") {		
				service.writeReply(dto);			
				return "1";	
			} else {
				return "2";	
			}	
		// 축구 정보 댓글 작성
		} else {
			if(result == "OK") {		
				service.soccer_writeReply(dto2);		
				return "1";	
			} else {
				return "2";	
			}	
		}

	}
	
	
	// 리플 삭제
	@ResponseBody
	@RequestMapping(value="replyDelete", method = RequestMethod.POST)
	public void replyDelete(ReplyDTO dto, Information_ReplyDTO dto2 ) throws Exception {
		
		// 게시글의 리플 삭제
		if (dto2.getClub_name() == null) {
			service.deleteReply(dto);
		// 축구 정보의 리플 삭제
		} else {
			service.soccer_deleteReply(dto2);
		}
		
	}
	
	
	// 리플 목록
    @ResponseBody
    @RequestMapping(value="/rePlyList", method = RequestMethod.GET )
    public List rePlyList(@RequestParam(required=false) Integer dno, 
    					@RequestParam(required=false) String club_name, Model model) throws Exception{
        
    	// 게시글 리플 리스트 가져오기
    	if( club_name == null) {
    		// 가져온 리플 목록을 reply에 저장  		
        	List<ReplyDTO> reply = service.commentList(dno);
            return service.commentList(dno);
        // 축구 정보 리플 리스트 가져오기
    	} else {
    		List<Information_ReplyDTO> reply = service.soccer_commentList(club_name);
    		return service.soccer_commentList(club_name);
    	}
    	
    }
    
    
    // 리플 수정
    @ResponseBody
    @RequestMapping(value="/replyUpdate",  method = RequestMethod.POST)
    private int replyUpdate( ReplyDTO dto, Information_ReplyDTO dto2  ) throws Exception{
    	
    	// 게시글 리플 수정
        if( dto2.getClub_name() == null) {
        	service.updateReply(dto);
        // 축구 정보 리플 수정
        } else {
        	service.soccer_updateReply(dto2);
        }       		
        return 1;
    }

    
    // 이메일 인증
    @ResponseBody
    @RequestMapping(value = "/auth" , method=RequestMethod.POST )
    public int mailSending(@RequestParam("email") String email) throws Exception {  	
    	// 인증코드생성
        Random r = new Random();
        int dice = r.nextInt(10000) + 10000;  
        
    	// 중복되는 이메일이 있는지 체크
    	int i = service1.overLap_EMAIL(email);
    	if ( i != 0) {
    		dice = 0;
    		return dice;
    	} else {   		            
            // 보내는 사람의 이메일
            String setfrom = "hcw0609@gamil.com";          
            // 받는 사람의 이메일
            String tomail = email;          
            // 이메일 제목
            String title = "회원가입 인증 이메일 입니다.";           
            // 이메일 내용
            String content = "인증 번호는 " + dice + " 입니다.";
            try {
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
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
    }	
       
    
    // 비정상적인 접근 막기
    @RequestMapping(value = "/Wrong_approach" , method=RequestMethod.GET )
    public void Wrong_approach(Model model) throws Exception {
    	model.addAttribute("msg", "접근 권한이 없습니다.");
    }
    
    
    // 글 쓰기, 수정, 삭제, 리플 작성  -> 로그인 필요
    @RequestMapping(value = "/needlogin" , method=RequestMethod.GET )
    public void Need_Login(Model model) throws Exception {
    	model.addAttribute("msg", "로그인을 해주세요."); 
    }    
    
    
    // Google Map Page
    @RequestMapping(value = "/map" , method=RequestMethod.GET )
    public void Map(Model model, @RequestParam(required=false, value="address") String address,
    							 @RequestParam(required=false, value="execute") String execute) throws Exception {
    	    	
    	if(address != null && execute != null) {
    		model.addAttribute("address", address);
    		model.addAttribute("execute", execute);
    	} else if (execute == null) {
    		model.addAttribute("execute", "no");
    	}
       	
    }
    
    
    // Google Map Search
    @ResponseBody
    @RequestMapping(value = "/mapsearch" , method=RequestMethod.POST)
    public Map<String, String> Map_Search(@RequestParam("address") String address,
    									HttpServletResponse response) throws Exception {
    	   
    	Map<String, String> retVal = new HashMap<String, String>();  	
    	try {
    		// 주소를 좌표값으로 변경
        	Maputil maputil = new Maputil();
        	String lat_lng = maputil.Map_util(address);
    		String[] split_lat_lng = lat_lng.split("!");
    		
    		retVal.put("lat", split_lat_lng[0]);
    		retVal.put("lng", split_lat_lng[1]);
         	
        	return retVal;
    	} catch (Exception e) {
			// TODO: handle exception
    		return retVal;
		} 	
    } 
    
    /*
    // chat
    @RequestMapping(value = "/chat" , method=RequestMethod.GET)
    public void Chat() throws Exception {
    	
    }
    */
    
    // 축구 팀 리스트
    @RequestMapping(value = "/Club_Info" , method=RequestMethod.GET)
    public void Club_Info( Model model, HttpSession hs) throws Exception{
    	
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
    	
    	// 글 목록 꺼내오기
    	List<ClubDTO> list = service2.Club_list();
    					
    	// 모델
    	model.addAttribute("list", list);
    	model.addAttribute("loginInfo", loginInfo);
    	
    }
    
    
    // 축구 팀 데이터 가져오기
    @RequestMapping(value = "/Information" , method=RequestMethod.GET)
    public void Information( Model model, HttpSession hs, @RequestParam("name") String name) throws Exception{
    	
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
       	
    	// 글 목록 꺼내오기
    	List<ClubDTO> list = service2.Club_data(name);
    	String Team_Info = list.get(0).getClub_base();
    	String Champion = list.get(0).getClub_title();
    	String Player_Info = list.get(0).getClub_roster();
    	String club_name = list.get(0).getClub_name();
    	
    	model.addAttribute("Team_Info", Team_Info);
    	model.addAttribute("Champion", Champion);
    	model.addAttribute("Player_Info", Player_Info);
    	model.addAttribute("club_name", club_name);
    	model.addAttribute("loginInfo",loginInfo);
    	
    	/*
    	Soccer_Team_Info sti = new Soccer_Team_Info();	   	
    	// 팀 정보
    	JsonArray Team_Info =  sti.Team_Info(name);    	
    	// 팀의 우승에 대한 정보 가져오기
    	JsonArray Champion = sti.Champion(name);   	
    	// 팀의 스쿼드에 대한 정보 
    	JsonArray Player_Info = sti.Player_Info(name);
    	*/   	
    }
    
    // 축구 좋아요
    @ResponseBody
    @RequestMapping(value="/good", method = RequestMethod.POST)
    public String good(ClubDTO dto, User_good_bad ugb, HttpSession hs) throws Exception {
    	
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
    	
    	// 로그인유저의 접근 권한 체크
		// 로그인 YES, 로그인 NO
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(loginInfo);	
		if(result == "OK") {			
			// 유저 아이디, 투표결과를 저장
	    	ugb.setUser_good(loginInfo.getID()); 
	    	ugb.setClub_name(dto.getClub_name());
	    	
	    	// 이미 한번 투표한 아이디 확인
			if(service2.user_good_check(ugb) == 1) {
				return "Check";
			} else {
				service2.good_cnt(dto);
		    	service2.user_good(ugb);
				return "OK";
			}	    	
		} else {
			return "NO";		
		}
    }
    
    // 축구 싫어요
    @ResponseBody
    @RequestMapping(value="/bad", method = RequestMethod.POST)
    public String bad(ClubDTO dto, User_good_bad ugb, HttpSession hs) throws Exception {
    	
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
    	
    	// 로그인유저의 접근 권한 체크
		// 로그인 YES, 로그인 NO
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(loginInfo);	
		if(result == "OK") {			
			// 유저 아이디, 투표결과를 저장
	    	ugb.setUser_bad(loginInfo.getID()); 
	    	ugb.setClub_name(dto.getClub_name());
	    	
	    	// 이미 한번 투표한 아이디 확인
	    	if(service2.user_bad_check(ugb) == 1) {
	    		return "Check";
	    	} else {
	    		service2.bad_cnt(dto);
		    	service2.user_bad(ugb);	    	
				return "OK";
	    	}	     	
		} else {
			return "NO";			
		} 	
    }
    
    
    // 축구 리그 테이블 가져오기
    @RequestMapping(value = "/League_Table" , method=RequestMethod.GET)
    public void soccer_data(Model model, HttpSession hs) throws Exception {
    	
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
    	
    	model.addAttribute("loginInfo",loginInfo);
    	
    	// 자바에서 파싱해온 데이터 가져오기
    	Soccer soccer = new Soccer();
    	JsonArray EPL = soccer.getDataHtml();
    	JsonArray LALIGA = soccer.getDataHtml1();
    	JsonArray SERIEA = soccer.getDataHtml2();
    	
    	model.addAttribute("EPL",EPL);
    	model.addAttribute("LALIGA",LALIGA);
    	model.addAttribute("SERIEA",SERIEA); 	
    }
    
    
    // admin_main 페이지
    @RequestMapping(value = "/admin_main" , method=RequestMethod.GET)
    public String admin_main(HttpSession hs) throws Exception{
    
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
    	
    	// 관리자 확인
    	UserCheck uc = new UserCheck();
    	String result = uc.User_Check(loginInfo);
    	if(result == "NullSession") {
    		return "redirect:/board/needlogin";
    	} else {
    		if(loginInfo.getID().equals("admin")) {
    			return "/board/admin_main";
        	} else {
        		return "redirect:/board/Wrong_approach";    		
        	}  	
    	}
   	
    }
    
     
    // admin_board 페이지
    @RequestMapping(value = "/admin_board" , method=RequestMethod.GET)
    public void admin_board( Search search, Model model ) throws Exception{
    	// 검색
    	search.setKeyword(search.getKeyword());
    	search.setSearchType(search.getSearchType());
    			
    	// 페이징
    	int post_cnt = service.getBoardListCnt(search);
    	search.Paging(post_cnt, search.getCur_page());
    	   
    	// 글 목록 꺼내오기
    	List<DbDTO> list = service.list(search);
    	
    	// 전체 리플 카운트
    	int reply_allcnt = service.reply_allcnt();
    	
    	
    	model.addAttribute("reply_allcnt", reply_allcnt);
    	model.addAttribute("listCnt", post_cnt);
    	model.addAttribute("paging", search);
		model.addAttribute("list", list);
    }
    
       
    // admin_board삭제
    @ResponseBody
    @RequestMapping(value = "/admin_board_delete" , method=RequestMethod.POST)
    public void admin_delete( DbDTO dto) throws Exception{
    	
    	// 게시글 삭제시 해당 게시물에 작성된 리플도 같이 삭제
    	service.deleteReplyBoard(dto.getDno());
    	
    	// 게시글 삭제
    	service.delete(dto.getDno());
    	   	  	
    }
    
    
    // admin_User 페이지
    @RequestMapping(value = "/admin_User" , method=RequestMethod.GET)
    public void admin_User( Search search, Model model ) throws Exception{
    	   	
    	// 검색
    	search.setKeyword(search.getKeyword());
    	search.setSearchType(search.getSearchType());
    			
    	// 유저 페이징
    	int post_cnt = service1.user_count(search);
    	search.Paging(post_cnt, search.getCur_page());
    			
    	// 유저 리스트 가져오기
    	List<UserDTO> list = service1.user_list(search);
    	
    	
    	model.addAttribute("user_count", post_cnt);
    	model.addAttribute("paging", search);
		model.addAttribute("list", list);
    }
    
    
    // admin_User_delete
    @ResponseBody
    @RequestMapping(value="/admin_User_delete", method=RequestMethod.POST)
    public void admin_User_delete(UserDTO userdto) throws Exception{
    	
    	service1.user_delete(userdto);
    }
    
        
    // admin 방문자 통계 확인
    @RequestMapping(value = "/admin_visitorcount" , method=RequestMethod.GET)
    public void admin_visitorcount() throws Exception{
    	
    }
    
    
    // 날짜별 방문자수 구하기
    @ResponseBody
    @RequestMapping(value = "/visitor_visitor_regdate" , method=RequestMethod.POST)
    public List visitor_visitor_regdate( ) throws Exception{
    	
    	// 날짜 가져오기
		List<VisitorDTO> list = service1.visitor_visitor_regdate();		
		Double_check dc = new Double_check();	
		List list_regdate = dc.Visitor_Double_check(list);
		
		// 날자별 방문자수 가져오기 [ip중복처리 x]
		List list_count_all = new ArrayList();	   	
    	for(int i=0; i<list_regdate.size(); i++ ) {
    		list_count_all.add(service1.visitor_count_all((String) list_regdate.get(i)));
    	}
    	  	
    	// 날자별 방문자수 가져오기 [ip중복처리 o]
    	List list_count_notall = new ArrayList();	   	
    	   for(int i=0; i<list_regdate.size(); i++ ) {
    		   list_count_notall.add(service1.visitor_count_notall((String) list_regdate.get(i)));
    	}
    	
    	List all = new ArrayList();
    	all.add(list_regdate);
    	all.add(list_count_all);
    	all.add(list_count_notall);
    	
    	return all;   		
    }
    
    
    // 날자별 작성된 게시글
    @ResponseBody
    @RequestMapping(value="/board_count_day", method=RequestMethod.POST)
    public List board_count_day() throws Exception{
    	
    	// 날짜 가져오기
    	List<VisitorDTO> list = service1.visitor_visitor_regdate();		
    	Double_check dc = new Double_check();	
    	List list_regdate = dc.Visitor_Double_check(list);
    	
    	// 날짜별 작성된 게시글 가져오기
    	List board_count_day = new ArrayList();
    	for(int i=0; i<list_regdate.size(); i++ ) {
    		board_count_day.add(service.board_count_day((String) list_regdate.get(i)));
    	}
    	
    	// 날짜별 작성된 리플 가져오기
    	List reply_count_day = new ArrayList();
    	for(int i=0; i<list_regdate.size(); i++ ) {
    		reply_count_day.add(service.reply_count_day((String) list_regdate.get(i)));
    	}
    	
      	List all = new ArrayList();
    	all.add(list_regdate);
    	all.add(board_count_day);
    	all.add(reply_count_day);
    	
		return all;
    }
    
    
    
    // 구글 로그인 api
 	@RequestMapping(value="/redirect", method=RequestMethod.GET)
 	public void redirect(HttpServletRequest request, HttpServletResponse response ) throws Exception {
 		
 		try {
 			Redirect redirect = new Redirect();
 			redirect.doPost(request, response);
 		} catch (Exception e) {
 			// TODO: handle exception
 			System.out.println(e);
 		}
 		
 		String email = Redirect.return_email;
 		
 		// 중복 이메일 확인
 		int i = service1.overLap_EMAIL(email);
 		if(i != 0) {
 			PrintWriter out = response.getWriter();
 			response.setContentType("text/html; charset=UTF-8");
 			out.print("<script>");
 			out.println("alert('이미 등록된 이메일 입니다.');");
 			out.println("location.href='/board/login';");
 			out.print("</script>");
 			out.flush();
 			
 		} else {
 			PrintWriter out = response.getWriter();
 			response.setContentType("text/html; charset=UTF-8");
 			out.print("<script>");
 			out.println("alert('회원가입으로 이동합니다.');");
 			out.println("location.href='/board/register?email="+email+"';");
 			out.print("</script>");
 			out.flush();
 			out.close();
 		}				
 	}
    
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public void test () {
    	
    }
 
}


