<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>Shop Homepage - Start Bootstrap Template</title>

	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="/resources/css/shop-homepage.css" rel="stylesheet">  
  
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
  
  
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
            		<a class="nav-link" href="#">LEAGUE</a>
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
	        	<h3 class="my-4">Club</h3>
        			<div class="list-group">
        			<c:choose>	
        				<c:when test="${club_belong eq 'EPL'}">	
	        				<a href="/board/Club_Info?club_belong=EPL" style="font-weight: bold;" class="list-group-item">EPL</a>
          					<a href="/board/Club_Info?club_belong=LALIGA" class="list-group-item">LALIGA</a>
          					<a href="/board/Club_Info?club_belong=SERIEA" class="list-group-item">SERIEA</a>
	        			</c:when>	        			
	        			<c:when test="${club_belong eq 'LALIGA'}">
	        				<a href="/board/Club_Info?club_belong=EPL" class="list-group-item">EPL</a>
          					<a href="/board/Club_Info?club_belong=LALIGA" style="font-weight: bold;" class="list-group-item">LALIGA</a>
          					<a href="/board/Club_Info?club_belong=SERIEA" class="list-group-item">SERIEA</a>
	        			</c:when>
	        			<c:when test="${club_belong eq 'SERIEA'}">	
	        				<a href="/board/Club_Info?club_belong=EPL" class="list-group-item">EPL</a>
          					<a href="/board/Club_Info?club_belong=LALIGA" class="list-group-item">LALIGA</a>
          					<a href="/board/Club_Info?club_belong=SERIEA" style="font-weight: bold;" class="list-group-item">SERIEA</a>
	        			</c:when>		
        			</c:choose>      				
        			</div>
			</div>   
			<!-- 측면 메뉴 -->   		     
			<!-- /.col-lg-2 -->

			<div class="col-lg-10">
			<div class="row">
        	
        	<c:forEach items="${list}" var="list">		
			<div class="col-lg-4 col-md-6 mb-4">
				<div class="card h-100">
					<a href="#"><img class="card-img-top" src='${list.club_image}' alt=""></a>
					<div class="card-body">
						<h4 class="card-title">
							<a href="#">${list.club_name}</a>
						</h4>
						<h5>${list.club_belong}</h5>		
					</div>
	            </div>
			</div>
			</c:forEach>	         

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

	<!-- Bootstrap core JavaScript -->
	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>	

</body>

</html>