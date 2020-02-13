<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1"
	>
	<title>Insert title here</title>
	
	<!-- 2 -->
	<!-- Ckeditor -->
	<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
	
	<!-- Bootstrap -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	
	<!-- Custom -->
	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
	
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2 -->
	
	
	<script type="text/javascript">
		
		var g_count = 0;
		
		$(document).ready(function(){
				
			// 타이틀은 전체 넓이 - 카테고리의 넓이이다.
			var select_board_width = $(".select_board").outerWidth();			
			$('.title').css({ 'width': 'calc(100% - ' + select_board_width+ 'px)' });
			
			$("#btnCreate").on("click", function(e) {
			       e.preventDefault();
			       
			       var category = document.getElementById("category_select").value;
			       $("#category").attr("value", category);
			       
			       var formObj = $("form[name='form']");
			       formObj.submit();
			});
			
			$("#addFile").on("click", function(e){
				var str = "<p><input type='file' name='file_"+(g_count++)+"' style='width:250px'/><a href='#' name='delete' class='btn btn-sm btn-danger'>삭제하기</a></p> ";
		        
		        $("#fileDiv").append(str);
		        
		        $("a[name='delete']").on("click",function(e){
		            e.preventDefault();
		            fn_fileDelete($(this));         
		        })
			})
			
			$("#btnList").on("click", function(e){
				location.href="/board/list"			
			})
		})
			
		
		function fn_fileDelete(obj){
	    	obj.parent().remove();
		}
		
		
	</script>

</head>
<body>

	
<div class="container">
	<form name="form" id="form" method="post" enctype="multipart/form-data">
	
		<label class="text_title">Title</label>
		<div style="float:left; width:100%;" class="mb-3">
			<div class="select_board" style="float:left;">
			<select class="form-control text_etc" name="category_select" id="category_select">
				<option class="text_etc" value="해외축구">해외축구</option>
				<option class="text_etc" value="국내축구">국내축구</option>
				<option class="text_etc" value="자유 게시판">자유 게시판</option>
			</select>
			</div>
			
			<input type="hidden" id="category" name="category" value="">
			
			<div class="title" style="float:left;">
				<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
			</div>
		</div>
				
				
		<div>
			<input type="hidden" class="form-control" name="writer" id="writer" value="${loginInfo.ID}" readonly="readonly">
		</div>			


		<div class="mb-3">
			<label class="text_title">Content</label>
			<textarea class="form-control" rows="5" name="content" id="content" placeholder="내용을 입력해 주세요" ></textarea>
			<script> CKEDITOR.replace('content',
							{filebrowserUploadUrl: '/board/ckUpload'});
			</script>
		</div>
				
				
		<div id="fileDiv" class="mb-3">
					
		</div>
				
				
		<div>
			<button type="button" class="btn btn-sm btn-primary btn-dark" id="addFile">파일 추가</button>
			<button type="button" class="btn btn-sm btn-primary btn-dark" id="btnCreate">저장</button>
			<button type="button" class="btn btn-sm btn-primary btn-dark" id="btnList">목록</button>
		</div>
			
	</form>		
	
</div>
	

</body>
</html>