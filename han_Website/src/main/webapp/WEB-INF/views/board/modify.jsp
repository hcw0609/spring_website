<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- Ckeditor -->
<script type="text/javascript" src="/resources/ckeditor/ckeditor.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<!-- Custom -->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>

<!-- Jquery -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<title>Insert title here</title>
<script type="text/javascript">

	var gfv_count = '${fn:length(file)+1}';
	
	$(document).ready(function(){
		
		$("#stored").on("click", function(e) {
		       e.preventDefault();
		       
		       var category = $('#category_select').val();
		       $("#category").attr("value", category);
		       
		       var formObj = $("form[name='modifyForm']");
		       formObj.submit();
		});
		
		
		$('#addFile').on("click", function(e){
			e.preventDefault();
			
	        var str = "<p>" +
         	  		  "<input type='file' id='file_"+(gfv_count)+"' name='file_"+(gfv_count)+"'>"+
           			  "<a href='#' class='btn btn-sm btn-danger' id='delete_"+(gfv_count)+"' name='delete_"+(gfv_count)+"'>삭제</a>" +
      	 			  "</p>";
      	 			  
  			$("#fileDiv").append(str);
  
 			$("#delete_"+(gfv_count++)).on("click", function(e){ //삭제 버튼
     			 e.preventDefault();
      			fn_fileDelete($(this));
  			});
		});
		
		
		$("a[name^='delete']").on("click",function(e){
            e.preventDefault();
            fn_fileDelete($(this));
            
           var tagid = $(this).attr('id');
           alert(tagid);
           
           $.ajax({
   			url : "/board/modifyDelete",
   			type : "post",
   			dataType : "json",
   			data : {"tagid" : tagid}
	   		})  		
        })		
        
	});
	
	
    function fn_fileDelete(obj){
        obj.parent().remove();  
    }
	

</script>
</head>
<body>


	<div class="container" role="main">
		<form name="modifyForm" id="modifyForm" method="post" enctype="multipart/form-data">
		
			<label class="text_title">Title</label>
			<div style="float:left; width:100%;" class="mb-3">
				<div style="float:left; width:10%">
				<select class="form-control text_etc" name="category_select" id="category_select">
					<option class="text_etc" value="해외축구">해외축구</option>
					<option class="text_etc" value="국내축구">국내축구</option>
					<option class="text_etc" value="축구 소식통">축구 소식통</option>
					<option class="text_etc" value="자유 게시판">자유 게시판</option>
				</select>
				</div>
			
				<input type="hidden" id="category" name="category" value="">
			
				<div style="float:left; width:90%">
				<input type="text" class="form-control" name="title" id="title" value="${modify.title}">
				</div>			
			</div>

				
			<div class="mb-3">
				<input type="hidden" class="form-control" name="writer" id="writer" value="${modify.writer}" readonly="readonly">
			</div>			


			<div class="mb-3">
				<textarea class="form-control" rows="5" name="content" id="content" >${modify.content}</textarea>
				<script> CKEDITOR.replace('content',
						{filebrowserUploadUrl: '/board/ckUpload'});
				</script>
			</div>
				
				
			<div id="fileDiv" class="mb-3">
				<c:forEach items="${file}" var="file" varStatus="row">
					<p>
					<a href="#" id="name_${row.index}" name="name_${row.index}" >${file.org_file_name}</a>(${file.file_size}kb)
					<a href="#" id="${file.file_no}" name="delete_${row.index}" class="btn btn-sm btn-danger">삭제</a>
					</p>
				</c:forEach>
			</div>
					
			<div>
				<button type="button" class="btn btn-sm btn-dark" id="addFile">파일 추가</button>
				<button type="button" class="btn btn-sm btn-dark" id="stored">저장</button>
			</div>		
				 
		</form>		
	</div>

	
</body>
</html>