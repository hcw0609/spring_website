<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>

<!-- Thumbnail -->
<link rel="stylesheet" type="text/css" href="/resources/css/Thumbnail.css"/>

<!-- MenuBar -->
<link rel="stylesheet" type="text/css" href="/resources/css/MenuBar.css"/>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<!-- Custom -->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>

<!-- Jquery -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">


	// 글 찾기
	function find() {
		var searchType = $('#searchType').val();
		var keyword = $('#keyword').val();		
		var url = "/board/list";
		url = url + "?searchType=" + searchType
		url = url + "&keyword="    + keyword	
		location.href=url;
	}
	
	
	// 이전 버튼 이벤트
	function fn_prev(curPage, curRange, rangeSize, searchType, keyword) {
		var curPage = ((curRange - 2) * rangeSize) + 1;
		var curRange = curRange - 1;
		var url = "/board/list";
		url = url + "?curPage="    + curPage;
		url = url + "&curRange="   + curRange;
		url = url + "&searchType=" + searchType
		url = url + "&keyword="    + keyword
		location.href = url;
	}


	// 페이지 번호 클릭 
	function fn_pagination(idx, curRange, searchType, keyword) {
		var url = "/board/list";
		url = url + "?curPage="    + idx;
		url = url + "&curRange="   + curRange;
		url = url + "&searchType=" + searchType
		url = url + "&keyword="    + keyword
		location.href = url;	
	}

	
	// 다음 버튼 이벤트 
	function fn_next(curPage, curRange, rangeSize, searchType, keyword) {
		var curPage = parseInt((curRange * rangeSize)) + 1;
		var curRange = parseInt(curRange) + 1;
		var url = "/board/list";
		url = url + "?curPage="    + curPage;
		url = url + "&curRange="   + curRange;
		url = url + "&searchType=" + searchType
		url = url + "&keyword="    + keyword
		location.href = url;
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
                <a class="menuLink" href="1">LOCATION LOG</a>
                <ul class="submenu3">
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>      
                </ul>
            </li>
		</ul>
	</div>
	<!-- 상단 메뉴바 -->
	
	
	<br><br>



<c:forEach items="${list}" var="list">	
<div style="float:left; width:100%">
	<div style="float:left;">	
		<a href="/board/read?dno=${list.dno}">	<img class="thumbnail" src="${list.thumbnail}"></a>
	</div>
			
	<div>
		<div>
		<a class="text_title" href="/board/read?dno=${list.dno}">${list.title}</a>
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


	<!-- 글쓰기 버튼 -->
	<button type="button" class="btn btn-sm btn-dark offset-11" id="btnWriteForm" onclick="location.href='/board/create'">글쓰기</button>

	<!-- 페이징 -->
	<div>
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
		</div>
	</div>
	<!-- 검색 -->


</div>

</body>
</html>