<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">

	<title>Insert title here</title>
	
	<!-- 2 -->
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2 -->
	
	<script type="text/javascript">
	
		// 글 찾기
		function find() {
			var searchType = $('#searchType').val();
			var keyword = $('#keyword').val();		
			var url = "/board/admin_board";
			url = url + "?searchType=" + searchType
			url = url + "&keyword="    + keyword	
			location.href=url;
		}
		
	
		// 이전 버튼 이벤트
		function fn_prev(curPage, curRange, rangeSize, searchType, keyword) {
			var curPage = ((curRange - 2) * rangeSize) + 1;
			var curRange = curRange - 1;
			var url = "/board/admin_board";
			url = url + "?curPage="    + curPage;
			url = url + "&curRange="   + curRange;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			location.href = url;
		}
	
	
		// 페이지 번호 클릭 
		function fn_pagination(idx, curRange, searchType, keyword) {
			var url = "/board/admin_board";
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
			var url = "/board/admin_board";
			url = url + "?curPage="    + curPage;
			url = url + "&curRange="   + curRange;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			location.href = url;
		}
		
		$(document).ready(function() {
	 		// admin 삭제
			$("button[name^='delete']").on("click", function(e){
				var dno = $(this).val();
				$(this).parent().parent().remove();
		        			        	
				$.ajax({
					url : "/board/admin_board_delete",
					type : "post",
					data : {"dno" : dno},
	    	   		success : function(data){    					
	 					location.reload(true);  
	 					alert("글 번호:"+dno+"를 삭제하였습니다.");
	    	   		}	    	   			
		        })		        	
			})	        	        	        
		})

	</script>
	
</head>
<body class="body_admin_board">
<div>

	<div class="row justify-content-center">
	<label class="text_title">총 게시물 수 : &nbsp;[${listCnt}]</label> &nbsp;&nbsp;&nbsp;
	
	<label class="text_title">총 댓글 수 :  &nbsp; [${reply_allcnt}]</label>
	</div>
	</br>
	
	<table class="table-hover" style="width:100%">
		<colgroup>
			<col style="width:10%; "/>
			<col style="width:15%; "/>	
			<col style="width:auto;"/>
			<col style="width:15%; "/>
			<col style="width:10%; "/>
			<col style="width:10%; "/>
		</colgroup>
	
		<tr>
			<td class="text_title padding_reduce text-center">번호</td>
			<td class="text_title padding_reduce text-center">글쓴이</td>
			<td class="text_title padding_reduce text-center">제목</td>
			<td class="text_title padding_reduce text-center">조회수</td>
			<td class="text_title padding_reduce text-center">날짜</td>
			<td class="text_title padding_reduce text-center">삭제</td>
		</tr>	

		<c:forEach items="${list}" var="list">	
			<tr>
				<td class="text-center">${list.dno}</td>
				<td class="text-center">${list.writer}</td>
				<td>
					<a href="/board/read?dno=${list.dno}">${list.title} [${list.reply_cnt}]</a>
					<c:if test="${list.fileyn == 'y'}">
					<span><img src="//image.fmkorea.com/modules/document/tpl/icons/file.gif"></span>
					</c:if>	
					<c:if test="${list.imageyn == 'y'}">
					<span><img src="/hcw0609/tomcat/webapps/ROOT/resources/image.png"></span>
					</c:if>		
				</td>
				<td class="text-center">${list.viewcnt}</td>
				<td class="text-center">${list.regdate}</td>
				<td class="text-center"><button class="btn-danger btn-Logout" name="delete_btn_${list.dno}" value="${list.dno}">삭제</button></td>			
			<tr>
		</c:forEach>	
	</table>
		 
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
		</div>
	</div>
	<!-- 검색 -->	
</div>

	

</body>
</html>