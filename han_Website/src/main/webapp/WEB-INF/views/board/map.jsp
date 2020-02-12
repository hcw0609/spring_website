<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Insert title here</title>
	
	<!-- 2 -->
	<!-- Google Map Api -->
	<script async defer  src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBPJ7JPbS7aFMk5hf78sP4A_ivIC_razg0&callback=reloadMap"></script>
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2 -->
	
	<script type="text/javascript">
		
		$(document).ready(function(){
			var aaa = "${execute}";
			
			// 자동으로  Search_Map()실행
			if( aaa == "yes" ) {
				Search_Map();
			}
						
			// 버튼을 눌러  Search_Map()실행
			$("#btn_mapsearch").click(function(){
				Search_Map();			
			})	
			
		})
		
		// 검색받은 주소를 좌표로 바꾸어 준다.
		function Search_Map() {		
			var address = $("#address").val();				
			$.ajax({
				url : "/board/mapsearch",
				type : "POST",
				data : {"address" : address},
				success: function(retVal) {
					
					var retVal_lat = retVal.lat;
					var retVal_lng = retVal.lng;
					
					if( retVal_lat == "실패" ) {
						alert("주소를  찾지 못했습니다. 다시 입력해 주세요.");
						$("#address").focus();
					} else {
						reloadMap(retVal_lat, retVal_lng);
					}				
				}
			})
		}
	
		// 사용자가 입력한 값으로 지도, 스트릿뷰 출력
		function reloadMap(retVal_lat, retVal_lng) {
			
			// 찾을 좌표 값
			var search_lat = retVal_lat;
			var search_lng = retVal_lng;	
			var Search = {lat:+search_lat, lng:+search_lng};
			
			// 맵 출력
			var map = new google.maps.Map(document.getElementById('map'),{
				center: Search,
				zoom : 17
			});
			
			
			// 스트릿뷰 출력
			var panorama = new google.maps.StreetViewPanorama(document.getElementById('pano'), {
				position: Search,
				pov: { heading:34, pitch:10 }
			});
			
			map.setStreetView(panorama);
	   		 
			
			// 마커 설정
			var marker = new google.maps.Marker({
				position : Search,
				title : "Search"
			});
			
			marker.setMap(map);	
		}
				
	</script>

</head>
<body>
	
	<div>
		<input type="text" class="form-control" id="address" style="width:100%; border:2px solid #212529;" value="${address}">
		<button class="btn btn-sm btn-dark" id="btn_mapsearch" name="subject" style="width:100%" value="trans">지도변경</button>
	</div>
	
	
	<div id="mapdiv">
		<div id="map" style="float:left; height:600px; width:50%; "></div>
		<div id="pano" style="float:left; height:600px; width:50%;"></div>
	</div>
	

	

</body>
</html>