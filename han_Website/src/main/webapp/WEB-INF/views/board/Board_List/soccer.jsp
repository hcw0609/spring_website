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
	<!-- Thumbnail -->
	<link rel="stylesheet" type="text/css" href="/resources/css/Thumbnail.css"/>
  
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
  	<!-- 2 -->
  
  
	 <script type="text/javascript">
	
		// 글 찾기
		function find() {
			var searchType = $('#searchType').val();
			var keyword = $('#keyword').val();		
			var category = $('#category').val();
			var url = "/board/Board_List/soccer";
			url = url + "?searchType=" + searchType
			url = url + "&keyword="    + keyword
			url = url + "&category="   + category
			location.href=url;
		}
		
		
		// 이전 버튼 이벤트
		function fn_prev(curPage, curRange, rangeSize, searchType, keyword) {
			var curPage = ((curRange - 2) * rangeSize) + 1;
			var curRange = curRange - 1;
			var category = $('#category').val();
			var url = "/board/Board_List/soccer";
			url = url + "?curPage="    + curPage;
			url = url + "&curRange="   + curRange;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			url = url + "&category="   + category
			location.href = url;
		}
	
	
		// 페이지 번호 클릭 
		function fn_pagination(idx, curRange, searchType, keyword) {
			var category = $('#category').val();
			var url = "/board/Board_List/soccer";
			url = url + "?curPage="    + idx;
			url = url + "&curRange="   + curRange;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			url = url + "&category="   + category
			location.href = url;	
		}
	
		
		// 다음 버튼 이벤트 
		function fn_next(curPage, curRange, rangeSize, searchType, keyword) {
			var curPage = parseInt((curRange * rangeSize)) + 1;
			var curRange = parseInt(curRange) + 1;
			var category = $('#category').val();
			var url = "/board/Board_List/soccer";
			url = url + "?curPage="    + curPage;
			url = url + "&curRange="   + curRange;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			url = url + "&category="   + category
			location.href = url;
		}
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
				<li class="nav-item active">
  					<a class="nav-link" href="/board/list">Community
						<span class="sr-only">(current)</span>
         		  	</a>
         		</li>
         		<li class="nav-item">
            		<a class="nav-link" href="/board/Club_Info?club_belong=EPL">Club</a>
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
	        	<h3 class="my-4">Community</h3>
	        		<div class="list-group">
	        		<c:choose>
	        			<c:when test="${category eq '해외축구'}">
	        				<a class="list-group-item" href="/board/list">통합 게시판</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=해외축구" style="font-weight: bold;" >해외축구</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=국내축구" >국내축구</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=자유 게시판" >자유 게시판</a>
	        			</c:when>
	        			<c:when test="${category eq '국내축구'}">
	        				<a class="list-group-item" href="/board/list">통합 게시판</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=해외축구" >해외축구</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=국내축구"  style="font-weight: bold;" >국내축구</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=자유 게시판" class="list-group-item">자유 게시판</a>
	        			</c:when>
	        			<c:otherwise>
	        				<a class="list-group-item" href="/board/list">통합 게시판</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=해외축구">해외축구</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=국내축구">국내축구</a>
          					<a class="list-group-item" href="/board/Board_List/soccer?category=자유 게시판" style="font-weight: bold;">자유 게시판</a>
	        			</c:otherwise>      			
        			</c:choose> 
        			</div>     			
			</div>   
			<!-- 측면 메뉴 -->   		     
			<!-- /.col-lg-2 -->

			<div class="col-lg-10">
				
				<!-- 광고판 -->
        		<div id="carouselExampleIndicators" class="carousel slide my-4" data-ride="carousel">
					<ol class="carousel-indicators">
     					<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
   						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
				<div class="carousel-inner" role="listbox">
					<div class="carousel-item active">
						<img class="d-block img-fluid" src="/Image/logo.jpg" style="width:900px; height:300px;" alt="First slide">
					</div>
 					<div class="carousel-item">
						<img class="d-block img-fluid" src="/Image/dd.png" style="width:900px; height:300px;" alt="Second slide">
					</div>
					<div class="carousel-item">
						<img class="d-block img-fluid" src="/Image/free.png" style="width:900px; height:300px;" alt="Third slide">
					</div>
          		</div>
          		<a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            		<span class="carousel-control-prev-icon" aria-hidden="true"></span>
            		<span class="sr-only">Previous</span>
          		</a>
          		<a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            		<span class="carousel-control-next-icon" aria-hidden="true"></span>
            		<span class="sr-only">Next</span>
          		</a>
				</div>
				<!-- 광고판 -->
	
	       	 <div class="row">
        		<input type="hidden" id="category" name="${category}" value="${category}">
				<c:forEach items="${list}" var="list">	
					<div style="float:left; width:100%" >
						<div style="float:left;">	
							<a href="/board/read?dno=${list.dno}">	<img class="thumbnail" src="${list.thumbnail}"></a>
						</div>
							
						<div>
							<div>
								<a class="text_title" href="/board/read?dno=${list.dno}">${list.title} [${list.reply_cnt}]</a>
								<c:if test="${list.fileyn == 'y'}">
									<span><img style="width:11px; height:11px;" src="/Image/File_Image.gif"></span>
								</c:if>	
								<c:if test="${list.imageyn == 'y'}">
									<span><img style="width:11px; height:11px;" src="/Image/Image_Icon.png"></span>
								</c:if>		
							</div>
											
							<div style="height:16px;"><span class="text_etc">${list.category}</span></div>	
									
							<div><span class="text_etc">${list.regdate}</span> / <span class="text_etc">${list.writer}</span></div>
						
						</div>
					</div>
				</c:forEach>

        	</div>
        	<!-- /.row -->    

			<!-- 페이징 -->
			<div>
			</br>
				<ul class="pagination">
					<c:if test="${paging.prevPage}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${paging.curPage}', '${paging.curRange}', '${paging.rangeSize}','${paging.searchType}','${paging.keyword}')">이전</a></li>
					</c:if>
			
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="idx">
						<li class="page-item <c:out value="${paging.curPage == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}','${paging.curRange}','${paging.searchType}','${paging.keyword}')"> ${idx} </a></li>
					</c:forEach>
					
					<c:if test="${paging.nextPage}">
						<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${paging.curPage}','${paging.curRange}', '${paging.rangeSize}','${paging.searchType}','${paging.keyword}')" >다음</a></li>
					</c:if>
				</ul>
			</div>
			<!-- 페이징 -->


			<!-- 검색 -->
			<div class="row justify-content-center">
				<div style="padding-right:10px">
					<select class="form-control form-control-sm" name="searchType" id="searchType">
						<option value="title">제목</option>
						<option value="content">내용</option>
						<option value="writer">글쓴이</option>
					</select>
				</div>
			
				<div class="w300" style="padding-right:10px">
					<input type="text" class="form-control form-control-sm" name="keyword" id="keyword">
				</div>
			
				<div>
					<button class="btn btn-sm btn-dark" name="btnSearch" id="btnSearch" onclick="find()">찾기</button>
					<button type="button" class="btn btn-sm btn-dark" id="btnWriteForm" onclick="location.href='/board/create'">글쓰기</button>							
				</div>
			</div>
			</br>
			<!-- 검색 -->

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