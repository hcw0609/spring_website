<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Soccer Site</title>

	<!-- 1 -->
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<!-- 1 -->
	
	<!-- 2 -->
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/Login_Register.css"/>
	<!-- 2 -->
	
	
	
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$("#login_btn").on("click",function(e){
				
				var ID = $("#ID").val();
				var PASSWORD = $("#PASSWORD").val();
				
				$.ajax({
					url : "/board/login",
					type : "POST",
					data : {"ID" : ID, "PASSWORD":PASSWORD},
					success: function(confirm) {
						if( confirm == "admin") {
							location.href="/board/admin_main"
						} else if( confirm == "yes") {
							location.href="/board/list"
						} else {
							alert("아이디와 패스워드를 확인해 주세요.");
						}
					}
				})										
			})
			
			// 구글 로그인
			$("#loginBtn").click(function(){
				location.href="https://accounts.google.com/o/oauth2/auth?client_id="+
				"1012262435218-1h0jvhs2q7ppr14qoefpm60vhndafant.apps.googleusercontent.com"+
				"&redirect_uri="+
				"http://hcw0609.cafe24.com/board/redirect" +
				"&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email&approval_prompt=force&access_type=offline";
			});
		})
		
	</script>
</head>

<body class="body_center">

<div>  
	<div>	
		<label class="text_title" style="margin-top: .0rem;">로그인</label>
 		</div>
          
	<div>
		<div>
			<input type="text" class="form-control btn_margin" id="ID" placeholder="ID" autofocus>
		</div>

		<div>
			<input type="password" class="form-control" style="margin-bottom:10px;" id="PASSWORD" placeholder="PASSWORD">
		</div>

		<div>
			<button type="button" class="form-control btn btn-primary btn-dark btn_margin" id="login_btn">로그인</button> <br>
			<button type="button" class="form-control btn btn-primary btn-dark btn_margin" onclick="location.href='/board/register'">회원가입</button>
			<button type="button" class="form-control btn btn-primary btn-dark" id="loginBtn">구글 로그인</button>
		</div>		
	</div> 	          
</div>





</body>
</html>