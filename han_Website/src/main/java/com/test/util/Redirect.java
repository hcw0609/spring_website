package com.test.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.test.dto.Token;

public class Redirect extends HttpServlet {
	
	public static String return_email = "";
	
	public Redirect() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String code = request.getParameter("code");
		String query = "code=" + code;
		query += "&client_id=" + "1012262435218-1h0jvhs2q7ppr14qoefpm60vhndafant.apps.googleusercontent.com";
		query += "&client_secret=" + "mR85AGn5rrs5lpabhmrHhU8g";
		query += "&redirect_uri=" + "http://hcw0609.cafe24.com/board/redirect";
		query += "&grant_type=authorization_code";
		
		// access_token을 얻기 위해 위에 작성한 쿼리를 아래 주소로 전달
		String tokenJson = getHttpConnection("https://accounts.google.com/o/oauth2/token", query);
		System.out.println(tokenJson.toString());
		
		// 나의 정보를 얻기위해 얻어온 access_token를 아래 주소로 전달
		Gson gson = new Gson();
		Token token = gson.fromJson(tokenJson, Token.class);
		String email = getHttpConnection("https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + token.getAccess_token());
		System.out.println(email);
		
		// 원하는 부분만 추출
		String real_email = emailsub(email);
		
		// 구한 Email을 변수에 저장
		return_email = real_email;
	}
	
	// 나의 정보를 얻기위해	
	private String getHttpConnection(String uri) throws ServletException, IOException {
		
		// URL 연결 [구글맵 주소를 좌표로 바꿔오는 작업때 쓰였다.] + GET방식 + 받기모드
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);

		// Json형태로  뿌려주는 데이터를 읽어온다
		String line;
		String email = "";
		try (InputStream stream = conn.getInputStream()) {
				// 아래와 같이 해야 String형태로 한줄씩 readLine을 써서 읽어올 수가 있다.
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					if(line.contains("email")) {
						email = line; break;
					}
				}
			}
		} catch (Throwable e) { e.printStackTrace();}
		
		return email;
	}
	
	
	//access_token을 얻기 위해
	private String getHttpConnection(String uri, String query) throws ServletException, IOException {
		
		// URL 연결 [구글맵 주소를 좌표로 바꿔오는 작업때 쓰였다.] + POST방식 + 쓰기모드
		URL url = new URL(uri);
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		
		// 쿼리문이 String이지만 보낼땐 Byte형태로 보낸다.
		try (OutputStream stream = conn.getOutputStream()) {
			stream.write(query.getBytes());
		}
		
		// Json형태로  뿌려주는 데이터를 읽어온다
		String line;
		StringBuffer buffer = new StringBuffer();
		try (InputStream stream = conn.getInputStream()) {
				// 아래와 같이 해야 String형태로 한줄씩 readLine을 써서 읽어올 수가 있다.
			try (BufferedReader rd = new BufferedReader(new InputStreamReader(stream))) {
				while ((line = rd.readLine()) != null) {
					buffer.append(line);
					buffer.append('\r');
				}
			}
		} catch (Throwable e) {e.printStackTrace();}
		
		return buffer.toString();
	}
	
	
	public String emailsub(String str) {
		String email = str;
		
		int index = email.indexOf(":");
		email = email.substring(index+3);
		
		index = email.indexOf('"');
		email = email.substring(0, index);
		
		return email;
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
}

