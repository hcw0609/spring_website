<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	
	<title>Insert title here</title>
	
	<!-- 1 -->
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
 	
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 	
 	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- 1 -->
	
	
</head>
<body class="body_admin_main">
	
	<ul style="list-style:none;">
	  <li style="padding-bottom:10px; font-size:2rem;"><button class="btn btn-dark" onclick="location.href='/board/list'">메인으로 가기</button></li>
      <li style="padding-bottom:10px; font-size:2rem;"><button class="btn btn-dark" onclick="location.href='/board/admin_board'">게시물 관리</button></li>
      <li style="padding-bottom:10px; font-size:2rem;"><button class="btn btn-dark" onclick="location.href='/board/admin_User'">유저 관리</button></li>
      <li style="padding-bottom:10px; font-size:2rem;"><button class="btn btn-dark" onclick="location.href='/board/admin_visitorcount'">통계</button></li>
	</ul>

</body>
</html>