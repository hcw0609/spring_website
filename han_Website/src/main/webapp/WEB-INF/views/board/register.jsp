<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	
<!-- JQUERY  -->
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Custom  -->
<link rel="stylesheet" type="text/css" href="/resources/css/style1.css"/>

<title>회원가입</title>

<script type="text/javascript">

	$(document).ready(function(){
		
		$(".cencle").on("click", function(){
			location.href = "/";
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
				$("#overLap").attr("value", "N");
				$("#PASSWORD").focus();
				return false;
			}		
			if($("#NAME").val()==""){
				alert("성명을 입력해주세요.");
				$("#overLap").attr("value", "N");
				$("#NAME").focus();
				return false;
			}		
			
			var overLapVal = $("#overLap").val();
			var email_auth = $("#email_auth").val();
			
			if(overLapVal == "N"){
				alert("아이디를 체크해주세요.");
			}
			else if(email_auth == "N") {
				alert("이메일 인증을 해주세요.");
			}
			else if(overLapVal == "Y" && email_auth == "Y"){
				$("#register").submit();
			}
			
		})
		
		// 아이디 체크
		$("#overLap").on("click", function(){
			$.ajax({
				url : "/board/overLap",
				type : "post",
				dataType : "json",
				data : {"ID" : $("#ID").val()},
				success : function(data){
					if(data == 1){
						$("#ID").css("background-color", "#dc3545");
						$("#overLap").attr("value", "N");
						alert("중복된 아이디입니다.");				
					}else if(data == 0){									
						// 특수문자 불가
						var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
						if(  special_pattern.test($("#ID").val())){
							$("#ID").css("background-color", "#dc3545");
							$("#overLap").attr("value", "N");
							alert("아이디에 특수문자는 사용할 수 없습니다.");
							$("#ID").focus();
							return false;
						}
						// 공백 포함 불가
						else if ( $("#ID").val().search(/\s/) != -1 ){
							$("#ID").css("background-color", "#dc3545");
							$("#overLap").attr("value", "N");
							alert("아이디에 공백이 포함되어 있습니다.");
							$("#ID").focus();
							return false;
						}
						// 공란 불가
						else if ( $("#ID").val() == '' ) {
							$("#ID").css("background-color", "#dc3545");
							$("#overLap").attr("value", "N");
							alert("아이디를 입력해주세요.");
							$("#ID").focus();
							return false;
						}
						// 모든 조건에 만족
						else {
							$("#overLap").attr("value", "Y");
							$("#ID").css("background-color", "#ffffff");
							alert("사용가능한 아이디입니다.");
						}
					}
				}
			})
		})
		
		
		// 인증번호 보내기
		$("#email_receive").on("click", function(){
			$.ajax({
				url : "/board/auth",
				type : "post",
				dataType : "json",
				data : {"email" : $("#email").val()},
				success : function(dice){
					$("#dice").attr("value", dice);
					alert("인증번호를 보냈습니다. 이메일을 확인해 주세요.");
				}
			})		
		})
		
		
		// 받은 인증번호 체크
		$("#email_auth").on("click", function(){
			var auth = $('#auth').val();
			var dice = $('#dice').val();
			if(auth == dice) {
				alert("인증 성공");
				$("#auth	").css("background-color", "#ffffff");
				$("#email_auth").attr("value", "Y");
			} else {
				alert("인증 실패");
				$("#auth").css("background-color", "#dc3545");
				$("#email_auth").attr("value", "N");
			}
				
		})		
	})
	
</script>
	
</head>
<body>

<div class="container">
	<div class="main">
		<form action="/board/register" method="post" id="register">
			<div class="mb-3">
				<input type="text" class="form-control" id="ID" name="ID" placeholder="ID"/>
				<button class="idChk btn form-control btn-dark" type="button" id="overLap"  value="N">아이디 체크</button>
			</div>
				
			<div>
				<input type="text" class="form-control" id="NAME" name="NAME" placeholder="NAME" />
			</div>
				
			<div class="mb-3">
					<input type="password" class="form-control" id="PASSWORD" name="PASSWORD" placeholder="PASSWORD" />
			</div>
				
			<div class="mb-3">
				<input type="text" class="form-control" id="email" name="email" placeholder="email"/>
				<button type="button" class="idChk btn form-control btn-dark" id="email_receive">인증번호 받기</button>
			</div>
				
			<div class="mb-3">
				<input type="hidden" id="dice" name="dice" value="dice"/>
				<input type="text" class="form-control" id="auth" name="auth" placeholder="auth"/>
				<button type="button" class="idChk btn form-control btn-dark" id="email_auth" value="N">인증번호 확인</button>
			</div>
								
		</form>
			
		<div>
			<button type="button" class="btn btn-dark form-control btnmargin2" id="submit">회원가입</button>
			<button type="button" class="cencle btn btn-danger form-control">취소</button>
		</div>	
	</div>		
</div>

</body>
</html>