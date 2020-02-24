<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Club_Info</title>
	
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
	<!-- Thumbnail -->
	<link rel="stylesheet" type="text/css" href="/resources/css/Thumbnail.css"/>
  
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  	<!-- 2 -->
  	
	<script type="text/javascript">
		
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
	        	<h3 class="my-4">Club</h3>
        		<div class="list-group">
        		<c:choose>	
        			<c:when test="${club_belong eq 'EPL'}">	
	        			<a class="list-group-item" href="/board/Club_Info?club_belong=EPL" style="font-weight: bold;">EPL</a>
          				<a class="list-group-item" href="/board/Club_Info?club_belong=LALIGA">LALIGA</a>
          				<a class="list-group-item" href="/board/Club_Info?club_belong=SERIEA">SERIEA</a>
	        		</c:when>	        			
	        		<c:when test="${club_belong eq 'LALIGA'}">
	        			<a class="list-group-item" href="/board/Club_Info?club_belong=EPL">EPL</a>
          				<a class="list-group-item" href="/board/Club_Info?club_belong=LALIGA" style="font-weight: bold;">LALIGA</a>
          				<a class="list-group-item" href="/board/Club_Info?club_belong=SERIEA">SERIEA</a>
	        		</c:when>
	        		<c:when test="${club_belong eq 'SERIEA'}">	
	        			<a class="list-group-item" href="/board/Club_Info?club_belong=EPL">EPL</a>
          				<a class="list-group-item" href="/board/Club_Info?club_belong=LALIGA">LALIGA</a>
          				<a class="list-group-item" href="/board/Club_Info?club_belong=SERIEA" style="font-weight: bold;">SERIEA</a>
	        		</c:when>		
        		</c:choose>      				
        		</div>
			</div>   
			<!-- 측면 메뉴 -->   		     
			
			<!-- Main Content -->
			<div class="col-lg-10">
			<div class="row">
        	
        	<c:forEach items="${list}" var="list">		
				<div class="col-lg-3 col-md-6 mb-4" style="margin-top:20px;">
					<div class="card">
						<a href="/board/Information?name=${list.club_search}"><img class="card-img-top" src='${list.club_image}' alt=""></a>
						<div class="card-body">
							<h4 class="card-title">
								<a href="/board/Information?name=${list.club_search}">${list.club_name}</a>
							</h4>
							<h5>${list.club_belong}</h5>		
						</div>
		            </div>
				</div>
			</c:forEach>	         

        	</div>
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