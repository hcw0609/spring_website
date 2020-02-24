<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Information</title>
	
	<!--  1 -->
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 	
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Bootstrap core JavaScript -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!--  1 --> 
	
	<!-- 2 -->	
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  	<!-- 2 -->
  
	<script type="text/javascript">
		
		alert("해당 데이터는 나무위키에서 실시간으로 가져오는 데이터입니다.");
		
		// 팀 정보 가져오기
		var list = ${Team_Info};
		
		// 이미지를 넣기 위한 작업
		var team_image = "/Image/team_image/"+list[0]["정식 명칭"]+".png";
		
		$(document).ready(function(){	
			$("#team_image").attr("src",team_image);
			$(".message").html(list[0]["정식 명칭"]);
			
			// Base 버튼		
			$('#Basebtn').on("click", function(e){
				
				// 이전 데이터 삭제
				for(var i=1; i<50; i++) {
					$('.base, .title, .roster').remove();				
				}
				
				$('#Basebtn').css("font-weight","bold");
				$('#Titlebtn').css("font-weight","400");
				$('#Rosterbtn').css("font-weight","400");
				
				var html_base = "<colgroup class='base'>" +
									"<col style='width:40%;'/>" +
									"<col style='width:60%;'/>" +
								"</colgroup>";	
							
				var html = "";
				
				for (var i=list.length; i>=0; i--) {

					html = "<tr class='base'>"	
					
					for(key in list[i]) {
						
						if(key == "홈 구장" || key == "홈구장" ) {
							html += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
							html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>" + "<a onclick=\"window.open('/board/map?execute=yes&address="+list[i][key]+"','window_name','width=1400px, height=700px, location=no, status=no,scrollbars=yes')\">" + list[i][key] +"</td>";
						} else {
							html += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
							html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>"+ list[i][key] +"</td>";
						}
														
					}
					
					html += "</tr>"
					
					var trHtml = $( ".start_list:last" );
					trHtml.after(html_base+html);
				}							
			})
			
			
			// Title 버튼			
			$('#Titlebtn').on("click", function(e){
				
				// 이전 데이터 삭제
				for(var i=1; i<50; i++) {
					$('.base, .title ,.roster').remove();				
				}
				
				$('#Basebtn').css("font-weight","400");
				$('#Titlebtn').css("font-weight","bold");
				$('#Rosterbtn').css("font-weight","400");
				
				var html_title = "<colgroup class='base'>" +
									"<col style='width:40%;'/>" +
									"<col style='width:60%;'/>" +
								"</colgroup>";
								
				// 팀의 우승에 대한 정보 가져오기
				var Champion = ${Champion};
								
				var html = "";
				
				for (var i=Champion.length; i>=0; i--) {

					html = "<tr class='title'>"	
					
					for(key in Champion[i]) {
						
						html += "<td class='text_subtitle1' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
						html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>"+ Champion[i][key] +"</td>";
												
					}
					
					html += "</tr>"
					
					var trHtml = $( ".start_list:last" );
					trHtml.after(html_title+html);
				}							
			})
			
			
			// Roster 버튼
			$("#Rosterbtn").on("click", function(e) {
				
				// 이전 데이터 삭제
				for(var i=1; i<50; i++) {
					$('.base, .title, .roster').remove();				
				}
				
				$('#Basebtn').css("font-weight","400");
				$('#Titlebtn').css("font-weight","400");
				$('#Rosterbtn').css("font-weight","bold");
				
				// 스쿼드 정보 가져오기
				var Player_list = ${Player_Info};
																	
				var html_title = "";
				var html_content = "";
							
				// 타이틀 
				html_title = "<tr class='roster'>";				
				for (key in Player_list[0]) {		
					if(key == "") {
						html_title += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ "국적" +"</td>";					
					} else {
						html_title += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";					
					}
					
				}
				html_title += "</tr>";
				// 타이틀 
				
				
				// 정보
				for(var i=0; i<=Player_list.length; i++) {
					
					html_content += "<tr class='roster'>"
					
					for (key in Player_list[i] ) {
						if(key == "") {
							html_content += "<td class='text_subtitle1 ' style='border: 1.5px solid #787777;'>" + "<img art='' src="+Player_list[i][key]+" style='width:50px; height:30px;'>"+"</td>";
						} else {
							html_content += "<td class='text_subtitle1 ' style='border: 1.5px solid #787777;'>"+ Player_list[i][key] +"</td>";
						}
						
					}
					
					html_content += "</tr>"
				}
				// 정보		
																	
				var trHtml = $( ".start_list:last" );
				trHtml.after(html_title+html_content);
						
			})					
		})
		
	</script>
  
</head>

<body>

 	<!-- Navigation -->
 	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
    
		<!-- 로그인 유저 확인 -->
      	<div>
			<c:if test="${loginInfo.ID == null}">
				<span class="text_subtitle1" style="color:white; ">"비로그인"님 어서오세요.</span>
				<input type="button" class="btn-Logout btn-danger" value="로그인하기" onclick="location.href='/board/login'"></input>
			</c:if>
			<c:if test="${loginInfo.ID != null}">
				<span class="text_subtitle1" style="color:white;">"${loginInfo.ID}"님 어서오세요.</span>
				<input type="button" class="btn-Logout btn-danger" value="로그아웃" onclick="location.href='/board/logout'"></input>
			</c:if>
				<input type="hidden" id="loginInfoID" name="${loginInfo.ID}" value="${loginInfo.ID}">
		</div>
		<!-- 로그인 유저 확인 -->
		
		<!-- 화면의 크기가 정해진 크기보다 작아 지면 버튼을 보여준다. 그리고 id=navbarResponsive인 놈들 활성화 시킨다. -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
 		</button>
      
		<!-- 상위 메뉴 -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
  					<a class="nav-link" href="/board/list">Community</a>
         		</li>
         		<li class="nav-item active">
            		<a class="nav-link" href="/board/Club_Info?club_belong=EPL">Club</a>
         		</li>
        		<li class="nav-item">
            		<a class="nav-link" href="/board/League_Table">LEAGUE</a>
          		</li>
          		<li class="nav-item">
            		<a class="nav-link" href="/board/admin_main">Admin</a>
          		</li>
        	</ul>
		</div>
		<!-- 상위 메뉴 -->
      
    </div>
	</nav>
	<!-- Navigation -->

	<!-- Page Content -->
	<div class="container">
		<div class="row">
		
			<!-- 측면 메뉴 -->
			<div class="col-lg-2">			
	        	<h3 class="my-4 message"></h3>
        			<div class="list-group">
        				<a class="list-group-item" id="Basebtn" style="font-weight:bold;">Base</a>
          				<a class="list-group-item" id="Titlebtn">Title</a>
          				<a class="list-group-item" id="Rosterbtn">Roster</a>	
        			</div>
			</div>   
			<!-- 측면 메뉴 -->   		     

			<!-- Main Content -->
			<div class="col-lg-10 ">
			<div class="row justify-content-center" style="margin-top:20px">

			<div style="max-width:800px; width:100%; border: 2px solid #787777; text-align:center;">
				<div>
					<img id="team_image" style="width:200px; height:200px;">			
				</div>
										
				<div class="text_title message" style="color:#ffffff; background-color:#000000; border: 1.5px solid #787777;"></div>
							
				<table class="start_list" style="max-width:800px; width:100%; border:1.5px solid #787777;">
					<input class="start_list" type="hidden">				
				</table>
				
			</div>
			      			
			</div><br>
   			</div>
			<!-- Main Content --> 

    	</div>
  	</div>
  	<!-- Page Content -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
		</div>
	</footer>
	<!-- Footer -->

</body>
</html>