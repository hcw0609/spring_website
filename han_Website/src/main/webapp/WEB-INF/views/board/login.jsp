<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="Custom_html">
<head>

	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Soccer Site</title>

	<!-- 2 -->
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/Login_Register.css"/>
	
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
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
						if( confirm == "yes") {
							location.href="/board/list"
						} else {
							alert("아이디와 패스워드를 확인해 주세요.");
						}
					}

				})
										
			})			

		})

		
		
	</script>
</head>

<body class="Custom_body">

<div class="Custom_container">
    <div class="Custom_main">  
        
            <div>
                <label class="text_title" style="margin-bottom: .0rem;">로그인</label>
            </div>
            
            <div class="panel-body">
				<div>
					<input type="text" class="form-control" id="ID" placeholder="ID" autofocus>
				</div>

                <div>
					<input type="password" class="form-control" id="PASSWORD" placeholder="PASSWORD">
				</div>

				<div>
					<button type="button" class="form-control btn btn-primary btn-dark btnmargin2" id="login_btn">로그인</button> <br>
					<button type="button" class="form-control btn btn-primary btn-dark btnmargin2" onclick="location.href='/board/register'">회원가입</button>
				</div>
            </div>           
    </div>
</div>

</body>
</html>