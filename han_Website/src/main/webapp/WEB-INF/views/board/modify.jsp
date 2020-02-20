<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Modify</title>
	
	<!--  1 -->
	<!-- Ckeditor -->
	<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>
	
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
	
		var gfv_count = '${fn:length(file)+1}';
		
		
		$(document).ready(function(){
			
			// 타이틀은 전체 넓이 - 카테고리의 넓이이다.
			var select_board_width = $(".select_board").outerWidth();			
			$('.title').css({ 'width': 'calc(100% - ' + select_board_width+ 'px)' });
							
			$("#stored").on("click", function(e) {
			       e.preventDefault();
			       
			       var category = $('#category_select').val();
			       $("#category").attr("value", category);
			       
			       var formObj = $("form[name='modifyForm']");
			       formObj.submit();
			});
			
			
			// 파일추가 버튼
			$('#addFile').on("click", function(e){
				e.preventDefault();
				
		        var str = "<p style='padding-bottom:5px;'>" +
	         	  		  "<input type='file' id='file_"+(gfv_count)+"' name='file_"+(gfv_count)+"' style='max-width:75%'>"+
	           			  "<a href='#' class='btn btn_file_modify_delete btn-danger' id='delete_"+(gfv_count)+"' name='delete_"+(gfv_count)+"' style='float:right;'>삭제</a>" +
	      	 			  "</p>";
	      	 			  
	  			$("#fileDiv").append(str);
	  
	 			$("#delete_"+(gfv_count++)).on("click", function(e){ //삭제 버튼
	     			 e.preventDefault();
	      			fn_fileDelete($(this));
	  			});
			});
			
			
			// 파일삭제 버튼
			$("a[name^='delete']").on("click",function(e){
	            e.preventDefault();
	            fn_fileDelete($(this));
	            
	           var tagid = $(this).attr('id');
	           $.ajax({
	   			url : "/board/modifyDelete",
	   			type : "post",
	   			data : {"tagid" : tagid}
		   		})  		
	        })		
	        
		});
		
		
	    function fn_fileDelete(obj){
	        obj.parent().remove();  
	    }
			
	</script>
	
</head>
<body class="body_read">


	<div class="container" role="main">
		<form name="modifyForm" id="modifyForm" method="post" enctype="multipart/form-data">
		
			<!-- Main Content -->
			<label class="text_title">Title</label>
			<div style="float:left; width:100%">
				<div class="select_board" style="float:left;">
				<select class="form-control text_etc" name="category_select" id="category_select">
					<option class="text_etc" value="해외축구">해외축구</option>
					<option class="text_etc" value="국내축구">국내축구</option>
					<option class="text_etc" value="자유 게시판">자유 게시판</option>
				</select>
				</div>
			
				<input type="hidden" id="category" name="category" value="">
			
				<div class="title" style="float:left;">
					<input type="text" class="form-control text_subtitle3" name="title" id="title" value="${modify.title}">
				</div>			
			</div>
				
			<input type="hidden" class="form-control" name="writer" id="writer" value="${modify.writer}" readonly="readonly">
					
			<br><br><br>
			<div class="mb-3">
				<textarea class="form-control" rows="5" name="content" id="content" >${modify.content}</textarea>
				<script> CKEDITOR.replace('content',
						{filebrowserUploadUrl: '/board/ckUpload'});
				</script>
			</div>
			<!-- Main Content -->
			
			<!--  FIle List-->
			<div id="fileDiv" class="mb-3">
				<c:forEach items="${file}" var="file" varStatus="row">
					<div style="padding-bottom:5px;">
					<a href="#" id="name_${row.index}" name="name_${row.index}" style="max-width:75%">${file.org_file_name}</a>(${file.file_size}kb)
					<a href="#" id="${file.file_no}" name="delete_${row.index}" class="btn btn_file_modify_delete btn-danger" style="float:right">삭제</a>		
					</div>
				</c:forEach>
			</div>
			<!--  FIle List-->
			
			<!-- Button -->		
			<div>
				<button type="button" class="btn btn-sm btn-dark" id="addFile">파일 추가</button>
				<button type="button" class="btn btn-sm btn-dark" id="stored"">저장</button>
				<button type="button" class="btn btn-sm btn-dark" id="list" onclick="location.href='/board/list'">목록</button>
			</div>	
			<!-- Button -->	
				 
		</form>		
	</div>

</body>
</html>