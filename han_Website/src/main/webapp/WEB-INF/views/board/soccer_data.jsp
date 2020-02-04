<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- MenuBar -->
<link rel="stylesheet" type="text/css" href="/resources/css/MenuBar.css"/>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<!-- Custom -->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>

<!-- Jquery -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>


<script type="text/javascript">
	

	$(document).ready(function(){
	
		$('#EPLbtn').on("click", function(e){
			for(var i=1; i<21; i++) {
				$('#LALIGA, #SERIEA').remove();				
			}
			
			var EPL_jsonArray = ${EPL};		
			var html = "";
				
			for (var i=EPL_jsonArray.length-1; i>=0; i--) {
				var data = ""
				html = "<tr id='EPL'>"
				while(true) {
									
					data = EPL_jsonArray[i]["rank"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["teamName"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = EPL_jsonArray[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
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
				$('#EPL, #SERIEA').remove();
			}
			
			var LALIGA_jsonArray = ${LALIGA};
			var html = "";
			
			for (var i=LALIGA_jsonArray.length-1; i>=0; i--) {
				var data = "";
				var teamCode = "";
				html = "<tr id='LALIGA'>"
				while(true) {
					teamCode = LALIGA_jsonArray[i]["teamCode"]					
					data = LALIGA_jsonArray[i]["rank"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					
					data = LALIGA_jsonArray[i]["teamName"]
					switch ( teamCode ) {
						case "26303":
							html += "<td style='padding:.25rem' class='text_title' >" + "<img src='/Image/LALIGA/"+teamCode+".png'>" + "<a onclick=\"window.open('/board/map?address=산티아고 베르나베우','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes')\">" + data + "</a></td>";
							break;
						case "26300":
							html += "<td style='padding:.25rem' class='text_title' >" + "<img src='/Image/LALIGA/"+teamCode+".png'>" + "<a onclick=\"window.open('/board/map?address=캄 노우','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes')\">" + data + "</a></td>";
							break;
						case "26305":
							html += "<td style='padding:.25rem' class='text_title' >" + "<img src='/Image/LALIGA/"+teamCode+".png'>" + "<a onclick=\"window.open('/board/map?address=완다 메트로폴리타노','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes')\">" + data + "</a></td>";
							break;
						case "26316":
							html += "<td style='padding:.25rem' class='text_title' >" + "<img src='/Image/LALIGA/"+teamCode+".png'>" + "<a onclick=\"window.open('/board/map?address=에스타디오 데 메스타야','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes')\">" + data + "</a></td>";
							break;
						case "27821":
							html += "<td style='padding:.25rem' class='text_title' >" + "<img src='/Image/LALIGA/"+teamCode+".png'>" + "<a onclick=\"window.open('/board/map?address=에스타디오 라몬 산체스 피스후안','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes')\">" + data + "</a></td>";
							break;
							
						default:
							html += "<td style='padding:.25rem' class='text_title' >" + "<img src='/Image/LALIGA/"+teamCode+".png'>" + data + "</td>";
					}
					
					data = LALIGA_jsonArray[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = LALIGA_jsonArray[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					break;
				}
				html += "</tr>"
				
				var trHtml = $( ".start_list:last" );
				$('#list').append(html);
				trHtml.after(html);
			}			
		})
		
		$('#SERIEAbtn').on("click", function(e){
			for(var i=1; i<21; i++) {
				$('#EPL, #LALIGA').remove();
			}
			
			var SERIEA_jsonArray = ${SERIEA};
			var html = "";
			
			for (var i=SERIEA_jsonArray.length-1; i>=0; i--) {
				var data = ""
				html = "<tr id='SERIEA'>"
				while(true) {
					data = SERIEA_jsonArray[i]["rank"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["teamName"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["gameCount"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["gainPoint"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["won"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["drawn"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["lost"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["gainGoal"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["loseGoal"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["goalGap"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
					data = SERIEA_jsonArray[i]["lastResult"]
					html += "<td style='padding:.25rem' class='text-center text_title' >" + data + "</td>";
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

<div class="container">
	<div>
		<c:if test="${loginInfo.ID == null}">
		<span class="text_etc">"비로그인"님 어서오세요.</span>
		<input type="button" class="btn-Logout btn-danger" value="로그인하기" onclick="location.href='/board/login'"></input>
		</c:if>
		<c:if test="${loginInfo.ID != null}">
		<span class="text_etc">"${loginInfo.ID}"님 어서오세요.</span>
		<input type="button" class="btn-Logout btn-danger" value="로그아웃" onclick="location.href='/board/logout'"></input>
		</c:if>
		<input type="hidden" id="loginInfoID" name="${loginInfo.ID}" value="${loginInfo.ID}">
	</div>
	
	<!-- 상단 메뉴바 -->
	<div id="topMenu" >
		<ul>
			<li class="topMenuLi">
                <a class="menuLink" href="/board/list">FOOTBALL</a>
                <ul class="submenu4">
                    <li><a href="/board/Board_List/soccer?category=해외축구" class="submenuLink">해외축구</a></li>
                    <li><a href="/board/Board_List/soccer?category=국내축구" class="submenuLink">국내축구</a></li>
                    <li><a href="/board/Board_List/soccer?category=축구 소식통" class="submenuLink">축구 소식통</a></li>
                    <li><a href="/board/Board_List/soccer?category=자유 게시판" class="submenuLink">자유 게시판</a></li>
                </ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="/board/soccer_data">FOOTBALL_DATA</a>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="1">LANGUAGE</a>
                <ul class="submenu4">
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li> 
                    <li><a href="" class="submenuLink">미구현</a></li>    
                </ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="/board/chat">Chat</a>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" onclick="window.open('/board/map','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes');">Map</a>
            </li>
		</ul>
	</div>
	<!-- 상단 메뉴바 -->
	
	
	<br><br>
	
	<h2>네이버에서 실시간으로 파싱해오는 데이터 입니다.</h2>
	<button id="EPLbtn"><img src="/Image/EPL/epl.png" style="width:100px; height:30px;" alt="EPL"></button>
	<button id="LALIGAbtn"><img src="/Image/LALIGA/laliga.png" style="width:100px; height:30px;" alt="LALIGA"></button>
	<button id="SERIEAbtn"><img src="/Image/SERIEA/seriea.png" style="width:100px; height:30px;" alt="SERIEA"></button>
	
	<div style="width:100%; height:10px"></div>
	
<table class="table table-hover">
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
</body>
</html>