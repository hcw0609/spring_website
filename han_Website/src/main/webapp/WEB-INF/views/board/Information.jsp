<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Soccer Site</title>
	
	<!-- 1 -->
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="/resources/css/shop-homepage.css" rel="stylesheet">  
	
	<!-- Bootstrap core JavaScript -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>	
	<!-- 1 -->
  
  	<!-- 2 -->
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 	<!-- 2 -->
  
	<script type="text/javascript">
		
		// 이미지를 넣기 위한 작업
		var list = ${Team_Info};
		var team_image = "/Image/team_image/"+list[0]["정식 명칭"]+".png";
		console.log(team_image);
		
		$(document).ready(function(){	
			$("#team_image").attr("src",team_image);
			$(".message").html(list[0]["정식 명칭"]);
						
			$('#Basebtn').on("click", function(e){
				
				var html = "";
				
				for (var i=0; i<=list.length; i++) {
					
					var data = ""
					html = "<tr>"	
					
					for(key in list[i]) {
						
						console.log(key);
						console.log(list[i][key]);
						
						html += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
						html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>"+ list[i][key] +"</td>";
													
					}
					
					html += "</tr>"
					
					var trHtml = $( ".start_list:last" );
					$('#list').append(html);
					trHtml.after(html);
				}							
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
		
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
 		</button>
      
		<!-- 상위 메뉴 -->
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
  					<a class="nav-link" href="/board/list">Community</a>
         		</li>
         		<li class="nav-item active">
            		<a class="nav-link" href="/board/Club_Info?club_belong=EPL">Club
            			<span class="sr-only">(current)</span>
            		</a>
         		</li>
        		<li class="nav-item">
            		<a class="nav-link" href="/board/League_Table">LEAGUE</a>
          		</li>
          		<li class="nav-item">
            		<a class="nav-link" href="#">Contact</a>
          		</li>
        	</ul>
		</div>
		<!-- 상위 메뉴 -->
      
    </div>
	</nav>

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
			<!-- /.col-lg-2 -->

			<div class="col-lg-10 ">
			<div class="row justify-content-center">

			<div style="max-width:700px; width:100%; border: 2px solid #787777; text-align:center;">
				<div>
					<img id="team_image" style="width:200px; height:200px;">			
				</div>
				
				</br>
				
				<div class="text_title message" style="color:#ffffff; background-color:#000000; border: 1.5px solid #787777;"></div>
				
			
				<table style="max-width:700px; width:100%; border:1.5px solid #787777;  "> 
					<colgroup class="start_list">
						<col style="width:30%; "/>
						<col style="width:70%; "/>	
					</colgroup>
				
					
				</table>
			</div>
			
        				
			</div>			
   			<!-- /.row -->
   			
   			<br>
   			</div>
			<!-- /.col-lg-10 -->       

    	</div>
   		 <!-- /.row -->

  	</div>
  	<!-- /.container -->

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2019</p>
		</div>
    <!-- /.container -->
	</footer>

</body>
</html>