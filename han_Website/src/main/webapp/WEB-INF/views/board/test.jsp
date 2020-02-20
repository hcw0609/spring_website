<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	
	<title>Insert title here</title>

	<!--  1 -->
	<!-- Bootstrap core JavaScript -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!--  1 -->
</head>
<body>

<div class="carousel slide" data-ride="carousel" id="carouselExampleControls">
	<ol class="carousel-indicators">
		<li data-target="#carouselExampleControls" data-slide-to="0"></li>
		<li data-target="#carouselExampleControls" data-slide-to="1"></li>
		<li data-target="#carouselExampleControls" data-slide-to="2"></li>
	</ol>
	<div class="carousel-inner">
		<div class="carousel-item active">
			<img class="img-fluid" alt="" src="/Image/background/logo.jpg" style="width:900px; height:280px;" data-interval="3000">
		</div>
		<div class="carousel-item">
			<img class="img-fluid" alt="" src="/Image/background/Domestic football.png" style="width:900px; height:280px;" data-interval="2000">
		</div>
		<div class="carousel-item">
			<img class="img-fluid" alt="" src="/Image/background/unnamed.png" style="width:900px; height:280px;">
		</div>	
	</div>
	<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
		<span class="carousel-control-prev-icon"></span>
	</a>
	<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
		<span class="carousel-control-next-icon"></span>
	</a>
</div>

</body>
</html>