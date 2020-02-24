<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>League Table</title>
	
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
	
	$(document).ready(function(){
		
		alert("해당 데이터는 네이버에서 실시간으로 가져오는 데이터 입니다.");
		
		$("#EPLbtn, #LALIGAbtn, #SERIEAbtn").on("click", function(e) {
			for(var i=1; i<21; i++) {
				$("#table").remove();				
			}
			
			// 가져올 데이터 선택
			var EPL_jsonArray = ${EPL};		
			var LALIGA_jsonArray = ${LALIGA};
			var SERIEA_jsonArray = ${SERIEA};
			var League_table;
			var League;
			
			// 눌린 버튼에 대한 정보
			var rno = $(this).attr('id');
			
			if(rno == "EPLbtn") {
				$('#EPLbtn').css("font-weight","bold");
				$('#LALIGAbtn').css("font-weight","400");
				$('#SERIEAbtn').css("font-weight","400");
				
				League_table = EPL_jsonArray;
				League = "EPL";
				
			} else if (rno == "LALIGAbtn") {
				$('#EPLbtn').css("font-weight","400");
				$('#LALIGAbtn').css("font-weight","bold");
				$('#SERIEAbtn').css("font-weight","400");
				
				League_table = LALIGA_jsonArray;
				League = "LALIGA";
				
			} else {
				$('#EPLbtn').css("font-weight","400");
				$('#LALIGAbtn').css("font-weight","400");
				$('#SERIEAbtn').css("font-weight","bold");
				
				League_table = SERIEA_jsonArray;
				League = "SERIEA";
			}
			
			var html = "";
			
			for (var i=League_table.length-1; i>=0; i--) {
				
				var data = ""
				var teamCode = "";	
				html = "<tr id='table'>"
				
				while(true) {
					
					teamCode = League_table[i]["teamCode"];
					data = League_table[i]["rank"]
					switch( data ) {
						case 1:
							html += "<td style='padding:.25rem; background-color:#85e26a;' class='text-center text_subtitle2' >" + data + "</td>";
							break;
						case 2:
						case 3:
						case 4: 
							html += "<td style='padding:.25rem; background-color:#c3dc9a;' class='text-center text_subtitle2' >" + data + "</td>";
							break;
						case 5:
						case 6:
							html += "<td style='padding:.25rem; background-color:#bdd9ef;' class='text-center text_subtitle2' >" + data + "</td>";
							break; 
						case 18:
						case 19:
						case 20:
							html += "<td style='padding:.25rem; background-color:#c0c0c0;' class='text-center text_subtitle2' >" + data + "</td>";
							break;
						default:
							html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					}
					
					data = League_table[i]["teamName"]
					html += "<td style='padding:.25rem' class='text_subtitle2' >" + "<img src='/Image/"+League+"/"+teamCode+".png' style='width:20px; height:20px;'>" + data + "</td>";  
					data = League_table[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = League_table[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					break;					
				}
				
				html += "</tr>"					
				var trHtml = $( ".start_list:last" );
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
				<span class="text_subtitle1" style="color:white;">"비로그인"님 어서오세요.</span>
				<button class="btn-Logout btn" onclick="location.href='/board/login'"><img alt="" src="/Image/Icon/login.png"></button>
			</c:if>
			<c:if test="${loginInfo.ID != null}">
				<span class="text_subtitle1" style="color:white;">"${loginInfo.ID}"님 어서오세요.</span>
				<button class="btn-Logout btn" onclick="location.href='/board/logout'"><img alt="" src="/Image/Icon/logout.png"></button>
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
         		<li class="nav-item">
            		<a class="nav-link" href="/board/Club_Info?club_belong=EPL">Club</a>
         		</li>
        		<li class="nav-item active">
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
	        	<h3 class="my-4">LEAGUE</h3>
        			<div class="list-group">
        				<a class="list-group-item" id="EPLbtn" style="font-weight:bold;">EPL</a>
          				<a class="list-group-item" id="LALIGAbtn">LALIGA</a>
          				<a class="list-group-item" id="SERIEAbtn">SERIEA</a>	
        			</div>
			</div>   
			<!-- 측면 메뉴 -->   		     
			
			<!-- Main Content -->
			<div class="col-lg-10">
			<div class="row" style="margin-top:20px">
        	       	
        	<table class="table table-hover" style="max-width:850px; width:100%; border: 1px solid #787777">
				<colgroup>
						<col style="width:5%; "/>
						<col style="width:30%; "/>	
						<col style="width:5%;"/>
						<col style="width:5%; "/>
						<col style="width:5%; "/>
						<col style="width:5%; "/>
						<col style="width:5%; "/>	
						<col style="width:5%;"/>
						<col style="width:5%; "/>
						<col style="width:5%; "/>
						<col style="width:25%; "/>
				</colgroup>
				
				<br> 
				
				<tr class="start_list" style="background-color: #2d2d2d;" >
					<td style="padding:.25rem" class="text-white text-center text_title" >순위</td>
					<td style="padding:.25rem" class="text-white text_title" >팀 이름</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >경기</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >승점</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >승</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >무</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >패</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >득점</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >실점</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >득실</td>
					<td style="padding:.25rem" class="text-white text-center text_title" >최근</td>
				</tr>	
			</table>

        	</div>
			</div>
			<!-- Main Content -->    

    	</div>
  	</div>

	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2020</p>
		</div>
	</footer>
	<!-- Footer -->
	
</body>

</html>