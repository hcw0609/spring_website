<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- Google Map Api -->
<script async defer  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPJ7JPbS7aFMk5hf78sP4A_ivIC_razg0&callback=initMap"></script>

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
		$("#btn_mapsearch").click(function(){
			var address = $("#address").val();
			
			$.ajax({
				url : "/board/mapsearch",
				type : "POST",
				dataType : "json",
				data : {"address" : address},
				success: function(retVal) {
					var retVal_lat = retVal.lat;
					var retVal_lng = retVal.lng;
					reloadMap(retVal_lat, retVal_lng);
				},
				error: function(retVal) {		
				}
			})
		})		
	})

	function reloadMap(retVal_lat, retVal_lng) {
		var search_lat = retVal_lat;
		var search_lng = retVal_lng;
		
		var Search = {lat:+search_lat, lng:+search_lng};
		var map = new google.maps.Map(document.getElementById('map'),{
			zoom : 17,
			center: Search
		});
		
		// 마커 설정
		var marker = new google.maps.Marker({
			position : Search,
			title : "Search"
		});
		
		marker.setMap(map);	
	}
	
	
	function initMap() {
		var a = 53.430980;
		var b = -2.960719;
		
		var anfield = {lat:a, lng:b};
		var map = new google.maps.Map(document.getElementById('map'),{
			zoom : 17,
			center: anfield
		});
		
		// 마커 설정
		var marker = new google.maps.Marker({
			position : anfield,
			title : "Stadium"
		});		
		marker.setMap(map);		
	}
	 
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
                <a class="menuLink" href="1">BASKETBALL</a>
                <ul class="submenu3">
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>                    
                </ul>
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
                <a class="menuLink" href="1">MEDIA LOG</a>
                <ul class="submenu4">
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li> 
                    <li><a href="" class="submenuLink">미구현</a></li>     
                </ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="/board/map">Map</a>
            </li>
		</ul>
	</div>
	<!-- 상단 메뉴바 -->
	
	
	<br><br>
	
	
	<div id="mapdiv">
		<div id="map" style="height:500px; width:100%; "></div>
	</div>
	
	<input type="text" class="form-control" id="address">
	<button name="subject" class="btn btn-sm btn-dark" id="btn_mapsearch" value="trans" style="width:100%;">지도변경</button>
	
	
</div>

</body>
</html>