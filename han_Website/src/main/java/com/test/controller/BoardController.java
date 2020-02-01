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

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.JsonObject;
import com.test.dto.DbDTO;
import com.test.dto.FileDTO;
import com.test.dto.GeocoderRequestDTO;
import com.test.dto.ReplyDTO;
import com.test.dto.UserDTO;
import com.test.service.BoardService;
import com.test.service.UserService;
import com.test.util.Maputil;
import com.test.util.Search;
import com.test.util.UserCheck;

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
		// 허락, 로그인 하지 않음, 로그인 유저와 작성자유저의 id가 다름
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(loginInfo);	
		if(result == "OK") {
			return "/board/create";
		} else if (result == "NullSession") {
			return "redirect:/board/needlogin";			
		} else {
			return "redirect:/board/Wrong_approach";
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
		
		// 로그인유저의 접근 권한 체크
		// 허락, 로그인 하지 않음, 로그인 유저와 작성자유저의 id가 다름
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
		// 허락, 로그인 하지 않음, 로그인 유저와 작성자유저의 id가 다름
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(dto.getWriter(), loginInfo);	
		if(result == "OK") {
			
			// 게시글 삭제시 해당 게시물에 작성된 리플도 같이 삭제
			service.deleteReplyBoard(dto.getDno());
			
			
			//나의 컴퓨터를 서버로 이용할 때 저장공간에 있는 파일 삭제
			List<String> deleteServerFile = service.deleteServer(dto.getDno());
			for(int i = 0; i<deleteServerFile.size(); i++) {
				String storedFileName = (String) deleteServerFile.get(i);			
				File file = new File("C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\han_Website\\resources\\mp_file\\"+storedFileName);
				if(file.exists() == true){
					file.delete();

				}
			}
						
			/*
			// 호스팅받은 서버를 이용할 때 파일 삭제
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
		} else if (result == "NullSession") {
			return "redirect:/board/needlogin";
		} else {
			return "redirect:/board/Wrong_approach";
		}	
	}
	
	// 로그인
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public void getLogin() throws Exception{

	}
	
	
	// 로그인 
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String postLogin(UserDTO userdto, HttpServletRequest req) throws Exception{
		
		// 세션을 가져온다.
		HttpSession hs = req.getSession();
		
		UserDTO login = service1.login(userdto);
				
		if( login == null) {
			return "redirect:/board/list";
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

	  
	  // 나의 컴퓨터를 서버로 이용할 때
	  String path = "C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\han_Website\\resources";
	  String ckUploadPath =  path + File.separator + "ckUpload" + File.separator + uid + "_" + fileName;
	  
		
	  /*
	  // 호스팅받은 서버를 이용할 때
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
					     HttpServletResponse res) throws Exception{
		
		
		String originalFileName = service.selectFileInfo(dto).getOrg_file_name();
		String storedFileName = service.selectFileInfo(dto).getStored_file_name();
		
		
		//파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다. [나의 컴퓨터를 서버로 이용할 때]
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\han_Website\\resources\\mp_file\\"+storedFileName));
		
		
		/*
		// 호스팅받은 서버를 이용할 때
		byte fileByte[] = org.apache.commons.io.FileUtils.readFileToByteArray(new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName));
		*/
		
		
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
		
		
		//나의 컴퓨터를 서버로 이용할 때 수정폼에서 저장공간에 있는 파일을 삭제
		File file = new File("C:\\Users\\han\\Documents\\workspace_01\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\han_Website\\resources\\mp_file\\"+storedFileName);   
		
		/*
		// 호스팅받은 서버를 이용할 때 수정폼에서 저장공간에 있는 파일을 삭제
		File file = new File("/hcw0609/tomcat/webapps/ROOT/resources/mp_file/"+storedFileName);
		*/
		
		
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
	public String replyWrite( ReplyDTO dto, HttpSession hs) throws Exception {
	
		// 로그인된 사용자가 누구 인지 확인
		// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
		UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
				
		// 로그인유저의 접근 권한 체크
		// 허락, 로그인 하지 않음, 로그인 유저와 작성자유저의 id가 다름
		UserCheck usercheck = new UserCheck();
		String result = usercheck.User_Check(loginInfo);	
		if(result == "OK") {
			
			// 리플 작성
			service.writeReply(dto);			
			return "1";	
		} else {
			return "2";	
		}	
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
    
    
    
    // 비정상적인 접근 막기
    @RequestMapping(value = "/Wrong_approach" , method=RequestMethod.GET )
    public void Wrong_approach(Model model) throws Exception {
    	model.addAttribute("msg", "잘못된 접근입니다.");
    }
    
    
    // 글 쓰기, 수정, 삭제, 리플 작성  -> 로그인 필요
    @RequestMapping(value = "/needlogin" , method=RequestMethod.GET )
    public void Need_Login(Model model) throws Exception {
    	model.addAttribute("msg", "로그인을 해주세요."); 
    }
    
    
    
    // Google Map
    @RequestMapping(value = "/map" , method=RequestMethod.GET )
    public void Map(HttpSession hs, Model model ) throws Exception {
    	
    	// 로그인된 사용자가 누구 인지 확인
    	// "User"로 바인딩된 객체를 돌려준다. 그리고 그걸 원래상태인 UserDTO형태로 loginInfo에 저장한다.
    	UserDTO loginInfo = (UserDTO) hs.getAttribute("User");
    	
    	model.addAttribute("loginInfo",loginInfo);  
    }
    
    
    // Google Map Search
    @ResponseBody
    @RequestMapping(value = "/mapsearch" , method=RequestMethod.POST)
    public Map<String, String> Map_Search(@RequestParam("address") String address, HttpServletResponse response) throws Exception {
    	   
    	Map<String, String> retVal = new HashMap<String, String>();
    	
    	// 주소를 좌표값으로 변경
    	Maputil maputil = new Maputil();
    	String lat_lng = maputil.Map_util(address);
		String[] split_lat_lng = lat_lng.split("!");
		
		retVal.put("lat", split_lat_lng[0]);
		retVal.put("lng", split_lat_lng[1]);
     	
    	return retVal;
    } 
}


