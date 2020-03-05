<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">

	<title>Admin_User</title>
	
	<!--  1 -->
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 	
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!--  1 --> 
	
	<!-- 2 -->	
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  	<!-- 2 -->
	
	<script type="text/javascript">	
		// 글 찾기
		function find() {
			var searchType = $('#searchType').val();
			var keyword = $('#keyword').val();		
			var url = "/board/admin_User";
			url = url + "?searchType=" + searchType
			url = url + "&keyword="    + keyword	
			location.href=url;
		}
				
		// 이전 버튼 이벤트
		function fn_prev(cur_page, cur_block, per_block_page, searchType, keyword) {
			var cur_page = ((cur_block - 2) * per_block_page) + 1;
			var cur_block = cur_block - 1;
			var url = "/board/admin_User";
			url = url + "?cur_page="    + cur_page;
			url = url + "&cur_block="   + cur_block;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			location.href = url;
		}
		
		// 페이지 번호 클릭 
		function fn_pagination(idx, cur_block, searchType, keyword) {
			var url = "/board/admin_User";
			url = url + "?cur_page="    + idx;
			url = url + "&cur_block="   + cur_block;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			location.href = url;	
		}
		
		// 다음 버튼 이벤트 
		function fn_next(cur_page, cur_block, per_block_page, searchType, keyword) {
			var cur_page = parseInt((cur_block * per_block_page)) + 1;
			var cur_block = parseInt(cur_block) + 1;
			var url = "/board/admin_User";
			url = url + "?cur_page="    + cur_page;
			url = url + "&cur_block="   + cur_block;
			url = url + "&searchType=" + searchType
			url = url + "&keyword="    + keyword
			location.href = url;
		}	
		
		$(document).ready(function() {
			
	 		// 관리자권한으로 유저 삭제
			$("button[name^='delete']").on("click", function(e){
				var ID = $(this).val();
				$(this).parent().parent().remove();
		        			        	
				$.ajax({
					url : "/board/admin_User_delete",
					type : "post",
					data : {"ID" : ID},
	    	   		success : function(data){   
	    	   			location.reload(true);
	 					alert("유저:"+ID+"를 삭제하였습니다.");    	   			
	    	   		}	    	   			
		        })		        	
			})	        	        	        
		})

	</script>
	
</head>
<body class="body_admin_User" >

<div>
		
	<div class="row justify-content-center">
		<label class="text_title">총 유저 수 : &nbsp; ${user_count} </label>
	</div></br>
		
	<!-- Table -->
	<table class="table-hover" style="width:100%">
		<colgroup>
			<col style="width:20%; "/>
			<col style="width:auto;"/>
			<col style="width:20%; "/>	
		</colgroup>
	
		<tr>
			<td class="text_title padding_reduce text-center">아이디</td>
			<td class="text_title padding_reduce text-center">이메일</td>
			<td class="text_title padding_reduce text-center">삭제</td>
		</tr>	

		<c:forEach items="${list}" var="list">	
			<tr>
				<td class="text-center">${list.ID}</td>
				<td class="text-center">${list.EMAIL}</td>
				<td class="text-center"><button class="btn-danger btn-Logout" name="delete_btn_${list.ID}" value="${list.ID}">삭제</button></td>			
			<tr>
		</c:forEach>	
	</table>
	<!-- Table -->		 
		 
	<!-- 페이징 -->
	<div>
	</br>
		<ul class="pagination">
			<c:if test="${paging.prev_page}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_prev('${paging.cur_page}', '${paging.cur_block}', '${paging.per_block_page}','${paging.searchType}','${paging.keyword}')">이전</a></li>
			</c:if>
	
			<c:forEach begin="${paging.start_page}" end="${paging.end_page}" var="idx">
				<li class="page-item <c:out value="${paging.cur_page == idx ? 'active' : ''}"/> "><a class="page-link" href="#" onClick="fn_pagination('${idx}','${paging.cur_block}','${paging.searchType}','${paging.keyword}')"> ${idx} </a></li>
			</c:forEach>
			
			<c:if test="${paging.next_page}">
				<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${paging.cur_page}','${paging.cur_block}', '${paging.per_block_page}','${paging.searchType}','${paging.keyword}')" >다음</a></li>
			</c:if>
		</ul>
	</div>
	<!-- 페이징 -->

	<!-- 검색 -->
	<div class="row justify-content-center">
		<div style="padding-right:10px">
			<select class="form-control form-control-sm" name="searchType" id="searchType">
				<option value="ID">ID</option>
			</select>
		</div>

		<div style="padding-right:10px">
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