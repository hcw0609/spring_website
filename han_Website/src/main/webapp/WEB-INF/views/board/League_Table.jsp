<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Soccer Site</title>
	
	<!--  1 -->
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="/resources/css/shop-homepage.css" rel="stylesheet">
	
	<!-- Bootstrap core JavaScript -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>	
	<!--  1 -->
    
    
    <!-- 2 --> 
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
  	<!-- 2 -->
  
  
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		alert("해당 데이터는 네이버에서 실시간으로 가져오는 데이터 입니다.");
		
		$('#EPLbtn').on("click", function(e){
			for(var i=1; i<21; i++) {
				$('#EPL, #LALIGA, #SERIEA').remove();				
			}
			
			$('#EPLbtn').css("font-weight","bold");
			$('#LALIGAbtn').css("font-weight","400");
			$('#SERIEAbtn').css("font-weight","400");
			
			var EPL_jsonArray = ${EPL};		
			var html = "";
			
			for (var i=EPL_jsonArray.length-1; i>=0; i--) {
				
				var data = ""
				var teamCode = "";	
				html = "<tr id='EPL'>"	
				
				while(true) {
					
					teamCode = EPL_jsonArray[i]["teamCode"]	
					
					data = EPL_jsonArray[i]["rank"]
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
					
					data = EPL_jsonArray[i]["teamName"]
					html += "<td style='padding:.25rem' class='text_subtitle2' >" + "<img src='/Image/EPL/"+teamCode+".png' style='width:20px; height:20px;'>" + data + "</td>";  
					data = EPL_jsonArray[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = EPL_jsonArray[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					break;
				}
				html += "</tr>"
				
				var trHtml = $( ".start_list:last" );
				$('#list').append(html);
				trHtml.after(html);
			}			
		})
		
		
		$('#LALIGAbtn').on("click", function(e){
			for(var i=1; i<21; i++) {
				$('#EPL, #LALIGA, #SERIEA').remove();
			}
			
			$('#EPLbtn').css("font-weight","400");
			$('#LALIGAbtn').css("font-weight","bold");
			$('#SERIEAbtn').css("font-weight","400");
			
			var LALIGA_jsonArray = ${LALIGA};
			var html = "";
			
			for (var i=LALIGA_jsonArray.length-1; i>=0; i--) {
				var data = "";
				var teamCode = "";
				html = "<tr id='LALIGA'>"
				while(true) {
					
					teamCode = LALIGA_jsonArray[i]["teamCode"]	
					
					data = LALIGA_jsonArray[i]["rank"]
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
										
					data = LALIGA_jsonArray[i]["teamName"]			
					html += "<td style='padding:.25rem' class='text_subtitle2' >" + "<img src='/Image/LALIGA/"+teamCode+".png' style='width:20px; height:20px;'>" + data + "</td>";			
					data = LALIGA_jsonArray[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					break;
				}
				html += "</tr>"
				
				var trHtml = $( ".start_list:last" );
				trHtml.after(html);
			}			
		})
		
		$('#SERIEAbtn').on("click", function(e){
			for(var i=1; i<21; i++) {
				$('#EPL, #LALIGA, #SERIEA').remove();
			}
			
			$('#EPLbtn').css("font-weight","400");
			$('#LALIGAbtn').css("font-weight","400");
			$('#SERIEAbtn').css("font-weight","bold");
			
			var SERIEA_jsonArray = ${SERIEA};
			var html = "";
			
			for (var i=SERIEA_jsonArray.length-1; i>=0; i--) {
				var data = ""
				var teamCode = "";
				html = "<tr id='SERIEA'>"
				while(true) {
					
					teamCode = SERIEA_jsonArray[i]["teamCode"]	
					
					data = SERIEA_jsonArray[i]["rank"]
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
					
					data = SERIEA_jsonArray[i]["teamName"]
					html += "<td style='padding:.25rem' class='text_subtitle2' >" + "<img src='/Image/SERIEA/"+teamCode+".png' style='width:20px; height:20px;'>" + data + "</td>";
					data = SERIEA_jsonArray[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_subtitle2' >" + data + "</td>";
					break;
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
		
		<!-- 화면의 크기가 정해진 크기보다 작아 지면 버튼을 보여준다. 그리고 id=navbarResponsive인 놈들 활성화 시킨다. -->
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
 		</button>
      
		<!-- 상위 메뉴 -->
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
  					<a class="nav-link" href="/board/list">Community</a>
         		</li>
         		<li class="nav-item">
            		<a class="nav-link" href="/board/Club_Info?club_belong=EPL">Club</a>
         		</li>
        		<li class="nav-item active">
            		<a class="nav-link" href="/board/League_Table">LEAGUE
						<span class="sr-only">(current)</span>
            		</a>
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
	        	<h3 class="my-4">LEAGUE</h3>
        			<div class="list-group">
        				<a class="list-group-item" id="EPLbtn" style="font-weight:bold;">EPL</a>
          				<a class="list-group-item" id="LALIGAbtn">LALIGA</a>
          				<a class="list-group-item" id="SERIEAbtn">SERIEA</a>	
        			</div>
			</div>   
			<!-- 측면 메뉴 -->   		     
			<!-- /.col-lg-2 -->

			<div class="col-lg-10">
			<div class="row">
        	       	
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
				
				<tr class="start_list" style="background-color: #2d2d2d" >
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
        	<!-- /.row -->    

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