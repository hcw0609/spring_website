package com.test.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.test.dto.VisitorDTO;

@Repository
public class VisitSessionListener implements HttpSessionListener {

	private static String namespace = "Mapper";
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub		
		HttpSession session = arg0.getSession();
		
		// 리스너는 spring에 접근할수 없기 때문에 집적 가져와야 한다.
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(session.getServletContext());
		
		//request를 파라미터에 넣지 않고도 사용할수 있도록 설정
		HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();		
		
		
		String ip = req.getHeader("X-Forwarded-For");
	    //System.out.println("> X-FORWARDED-FOR : " + ip);
	    	    
	    if (ip == null) {
	        ip = req.getHeader("Proxy-Client-IP");
	        //System.out.println("> Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = req.getHeader("WL-Proxy-Client-IP");
	        //System.out.println(">  WL-Proxy-Client-IP : " + ip);
	    }
	    if (ip == null) {
	        ip = req.getHeader("HTTP_CLIENT_IP");
	        //System.out.println("> HTTP_CLIENT_IP : " + ip);
	    }
	    if (ip == null) {
	        ip = req.getHeader("HTTP_X_FORWARDED_FOR");
	        //System.out.println("> HTTP_X_FORWARDED_FOR : " + ip);
	    }
	    if (ip == null) {
	        ip = req.getRemoteAddr();
	        //System.out.println("> getRemoteAddr : "+ip);
	    }
	    
	    //System.out.println("> Result : IP Address : "+ip);
	    
		
		VisitorDTO dto = new VisitorDTO();
		dto.setVisitor_ip(ip);

		
		((SqlSession) wac.getBean("sqlSession")).insert(namespace + ".visitor_count",dto);	
		 
	}
	
	
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

 

}