<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Register</title>
	
	<!-- 1 -->
	<!-- JQUERY  -->
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- 1 -->
	
	<!-- 2 -->
	<!-- Custom  -->
	<link rel="stylesheet" type="text/css" href="/resources/css/Login_Register.css"/>
	<!-- 2 -->
		
	<script type="text/javascript">
	
		$(document).ready(function(){
			
			$(".cancle").on("click", function(){
				location.href = "/board/login";
			})
			
			
			// 아이디가 가입 도중에 변경되면 일단 N값을 줘서 다시 아이디를 체크하게 한다.
			$("#ID").on("propertychange change keyup paste input", function() {		
				$("#overLap").attr("value", "N");
			})
					
					
			$("#submit").on("click", function(){
				if( $("#ID").val() == '' ){
					alert("아이디를 입력해주세요.");
					$("#overLap").attr("value", "N");
					$("#ID").focus();
					return false;
				}
				if($("#PASSWORD").val()==""){
					alert("비밀번호를 입력해주세요.");
					$("#PASSWORD").focus();
					return false;
				}		
				
				var overLapVal = $("#overLap").val();
				var email_check = $("#email_check").val();
				var pwd_chk = $("#pwd_chk").val();
				
				if(overLapVal == "N"){
					alert("아이디를 체크해주세요.");
				} else if (pwd_chk == "N" ){
					alert("비밀번호를 체크해 주세요.");
				}
				else if(email_check == "N") {
					alert("이메일 인증을 해주세요.");
				}
				else if(overLapVal == "Y" && email_check == "Y" && pwd_chk == "Y"){
					$("#register").submit();
				}
				
			})
			
			// 아이디 체크
			$("#overLap").on("click", function(){
				$.ajax({
					url : "/board/overLap",
					type : "post",
					data : {"ID" : $("#ID").val()},
					success : function(data){
						if(data == 1){
							$("#ID").css("background-color", "#dc3545");
							$("#overLap").attr("value", "N");
							alert("중복된 아이디입니다.");				
						}else if(data == 0){	
							
							// ID 정규식 체크
							// 문자 : 영어소문자  or 영어대문자  or 숫자  
							// 길이 : 4~20 사이 
							var IdRule = /^[a-zA-Z0-9]{4,20}$/;
						
							if( IdRule.test($("#ID").val()) ) {
								$("#overLap").attr("value", "Y");
								$("#ID").css("background-color", "#ffffff");
								alert("사용가능한 아이디입니다.");
							} else {
								$("#ID").css("background-color", "#dc3545");
								$("#overLap").attr("value", "N");
								alert("아이디는 4~20자 사이의 영어만 가능합니다.");
								$("#ID").focus();
								return false;
							}						
						}
					}
				})
			})
			
				
			// 비밀번호 체크 1
			$("#PASSWORD").on("propertychange change keyup paste input", function() {
			
				// PASSWORD 정규식 체크
				// 문자 : 영어대소문자, 숫자, 특수문자 각각 최소 1개씩 이 포함되어야 한다. 
				// 길이 : 8~20 사이 
				var PwdRule = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
				
				if( PwdRule.test($("#PASSWORD").val()) ) {
					var pwd1 = $("#PASSWORD").val();
					var pwd2 = $("#PASSWORD_CHECK").val();
					
					if(pwd1 != pwd2) {
						$("#pwd_chk").attr("value", "N");
						$(".message").html("패스워드가 서로 다릅니다.");
					} else {
						$("#pwd_chk").attr("value", "Y");
						$(".message").html("");
					}
				} else {
					$("#pwd_chk").attr("value", "N");
					$(".message").html("8~20자 사이 영어대소문, 숫자, 특수문자 각각 최소 1개");
				}						
			});
			
			
			// 비밀번호 체크 2
			$("#PASSWORD_CHECK").on("propertychange change keyup paste input", function() {
			
				// PASSWORD 정규식 체크
				// 문자 : 영어대소문자, 숫자, 특수문자 각각 최소 1개씩 이 포함되어야 한다. 
				// 길이 : 8~20 사이 
				var PwdRule = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,20}$/;
				
				if( PwdRule.test($("#PASSWORD").val()) ) {
					var pwd1 = $("#PASSWORD").val();
					var pwd2 = $("#PASSWORD_CHECK").val();
					
					if(pwd1 != pwd2) {
						$("#pwd_chk").attr("value", "N");
						$(".message").html("패스워드가 서로 다릅니다.");
					} else {
						$("#pwd_chk").attr("value", "Y");
						$(".message").html("");
					}
				} else {
					$("#pwd_chk").attr("value", "N");
					$(".message").html("사용불가한 형식의 비밀번호 입니다.");
				}		
			});
	
			
			// 이메일로 인증번호 전송
			$("#email_receive").on("click", function(){
				
				// E-MAIL 정규식 체크
				// 문자 : [영어소문자 or 영어대문자 or 숫자 or _ or -] "@"  [영어소문자 or 영어대문자 or 숫자] "." [영어소문자 or 영어대문자 or 숫자]
				var EmailRule = /^[a-zA-Z0-9_\-]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+$/;
				
				if( EmailRule.test($("#EMAIL").val()) ) {											
					$.ajax({
						url : "/board/auth",
						type : "post",
						data : {"email" : $("#EMAIL").val()},
						success : function(dice){
							if(dice == 0 ) {						
								$("#EMAIL").css("background-color", "#dc3545");
								$("#email_check").attr("value", "N");
								$("#EMAIL").focus();
								alert("이미 가입된 이메일 입니다.");
							} else {
								$("#email_check").attr("value", "Y");
								$("#EMAIL").css("background-color", "#ffffff");
								$("#dice").attr("value", dice);
								alert("인증번호를 보냈습니다. 이메일을 확인해 주세요. "+ dice);
							}						
						}
					})		
									
				} else {
					$("#EMAIL").css("background-color", "#dc3545");
					$("#email_check").attr("value", "N");
					$("#EMAIL").focus();
					alert("이메일 형식에 적합하지 않습니다.");
					return false;
				}					
			})
			
			
			// 이메일로 전송받은 인증번호 체크
			$("#email_check").on("click", function(){
				var auth = $('#auth').val();
				var dice = $('#dice').val();
				if(auth == dice) {
					alert("인증 성공");
					$("#auth	").css("background-color", "#ffffff");
					$("#email_check").attr("value", "Y");
				} else {
					alert("인증 실패");
					$("#auth").css("background-color", "#dc3545");
					$("#email_check").attr("value", "N");
				}			
			})		
		})
		
	</script>
	
</head>
<body class="body_center">

<div>

	<form action="/board/register" method="post" id="register">
		<div class="mb-3">
			<input type="text" class="form-control" id="ID" name="ID" placeholder="ID"/>
			<button class="btn form-control btn-dark" type="button" id="overLap" value="N">아이디 체크</button>
		</div>
							
		<div class="mb-3">
			<input type="password" class="form-control" id="PASSWORD" name="PASSWORD" placeholder="PASSWORD" />
			<input type="password" class="form-control" id="PASSWORD_CHECK" name="PASSWORD_CHECK" placeholder="PASSWORD_CHECK"/>
			<input type="hidden" id="pwd_chk" value="N">
			<p class="message" align="center"></p>
		</div>
			
		<div class="mb-3">
			<input type="text" class="form-control" id="EMAIL" name="EMAIL" placeholder="E-MAIL"/>
			<button type="button" class="btn form-control btn-dark" id="email_receive">인증번호 받기</button>
		</div>
			
		<div class="mb-3">
			<input type="hidden" id="dice" name="dice" value="dice"/>
			<input type="text" class="form-control" id="auth" name="auth" placeholder="E-MAIL_CHECK"/>
			<button type="button" class="btn form-control btn-dark" id="email_check" value="N">인증번호 확인</button>
		</div>							
	</form>
		
	<div>
		<button type="button" class="btn btn-dark form-control btn_margin" id="submit">회원가입</button>
		<button type="button" class="cancle btn btn-danger form-control">취소</button>
	</div>	
	
</div>		

</body>
</html>