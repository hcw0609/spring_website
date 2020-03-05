<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>Read</title>
	
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
 	
 	<!-- For_Mobile_Image Custom -->
	<link rel="stylesheet" type="text/css" href="/resources/css/For_Mobile_Image.css"/>
  	<!-- 2 -->
  	
	<script type="text/javascript">
	
		// 수정버튼
		function Modify() {
			location.href="/board/modify?dno=${read.dno}"
		}
	
		// 삭제버튼
		function Delete() {
			location.href="/board/delete?dno=${read.dno}&writer=${read.writer}"
		}
		
		// 목록으로 돌아가기
		function List() {
			location.href="/board/list"
		}
		
		// 파일 다운로드
		function fn_fileDown(fileNo){
			var formObj = $("form[name='for_fileno']");
			// file_no 의 value에 fileno라는 값을 넣어준다.
			$("#file_no").attr("value", fileNo);
			formObj.attr("action", "/board/fileDown");
			formObj.submit();		
		}
		
			
		$(document).ready(function(){
			
			commentList();		
			
			// 리플 작성
			$("#replyWriteBtn").on("click", function(){
				if( $("#reply_content").val() == '' ){
					alert("내용을 입력해 주세요.");
				} else {
					var dno = ${read.dno};
					var login_writer = $('input[name=login_writer]').val();
					var reply_content = $('input[name=reply_content]').val();
					$.ajax({
						 url : '/board/replyWrite',
					     type : 'post',
					     data : {'dno':dno, 'writer':login_writer, 'content':reply_content},
					     success : function(data){ 
					    	 if(data == "2") {
					    		 window.location.href="/board/needlogin";
					    	 } else {
					    		 // 다시 리플 리스트를 뿌려준다.
					    		 commentList();
					 			$('input[name=reply_content]').val("");
					    	 }
					     }
					 })		 	
				}			 
			});			
		});
		
		
		//리플 리스트
		function commentList(){
			var dno = ${read.dno};	
		    $.ajax({
		        url : "/board/rePlyList",
		        type : "get",
		        data : {'dno':dno},
		        success : function(data){
		            var a =''; 
		            $.each(data, function(key, value){
		            	var login_writer = $('input[name=login_writer]').val();
		            	var writer = value.writer	               	         
						
		            	
		            	a += '<div class="ReplyArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
						a += '<div class="ReplyInfo'+value.rno+' text_subtitle">'+'작성자 : '+value.writer;     
		            	if(writer == login_writer || login_writer == "admin") {
		            		a += '&nbsp;&nbsp;<button type="button" class="btn btn-dark btn-reply-MD" name="modify_'+value.rno+'">수정</button>';
							a += '&nbsp;&nbsp;<button type="button" class="btn btn-dark btn-reply-MD" name="delete_'+value.rno+'">삭제</button>';
		            	}
		            	a += '</div>';
						a += '<div class="ReplyContent'+value.rno+' text_subtitle" name="'+value.content+'"> <p> 내용 : '+value.content +'</p>';
						a += '</div></div>';
		                
		            });
		         	            	            
		            $(".ReplyList").html(a);
		            
		            // 리플 삭제 버튼
		            $("button[name^='delete']").on("click", function(e){
		    			e.preventDefault();
		    			$(this).parent().parent().remove();
		    			
		    			var rno = $(this).attr('name');
		    			rno = rno.substring(7);
		    			
		    			var dno = ${read.dno};
		    			
		    			$.ajax({
		    	   			url : "/board/replyDelete",
		    	   			type : "post",
		    	   			data : {"rno" : rno, "dno" : dno}
		    		   	})
		    		});
		            
		                        
		            // 리플 수정 폼 버튼
		            $("button[name^='modify']").on("click", function(e){
		    			e.preventDefault();
	    			
		    			var rno = $(this).attr('name');
		    			rno = rno.substring(7);	    			
	
		    			var content = $(".ReplyContent"+rno).attr('name');
		    			
		    			var a ='';
		    		    
		    		    a += '<div class="input-group">';
		    		    a += '<input type="text" class="form-control" name="reply_content_'+rno+'" value="'+content+'"/>';
		    		    a += '<span class="input-group-btn">';
		    		    a += '<button type="button" class="btn btn-dark btn-custom" name="saveModify_'+rno+'">수정</button> </span>';
		    		    a += '</div>';
		    		    
		    		    $('.ReplyContent'+rno).html(a);
		    		    
		    		 	// 리플 수정 버튼
			            $("button[name^='saveModify']").on("click", function(e){
			    			e.preventDefault();
		    			
			    			var rno = $(this).attr('name');
			    			rno = rno.substring(11);
	
			    			var content = $('[name=reply_content_'+rno+']').val();
			    			
			    			if (content == '') {
			    				alert("내용을 입력해 주세요.");
			    			} else {
			    				$.ajax({
			    			        url : '/board/replyUpdate',
			    			        type : 'post',
			    			        data : {'content' : content, 'rno' : rno},
			    			        success : function(data){
			    			        	//댓글 수정후 목록 출력 
			    			            if(data == 1) commentList(); 
			    			        }
			    				});
			    			}
			    			 			 
			    		});
		    		});	            
		        }
		    });
		}
		
	</script>

</head>
<body> 	

	<div class="container" role="main">
		<form name="for_fileno" method="post">
			<input type="hidden" id="file_no" name="file_no" value=""> 
		</form>
		
		<!-- Main Content -->
		<label class="text_title">Title</label>
		<div style="float:left; width:100%;" class="mb-3">		
			<div style="float:left;">
				<p class="text_subtitle" id="category" name="category">[${read.category}]</p>
			</div>
						
			<div style="float:left;">
				<p class="text_subtitle" name="title" id="title">${read.title}</p>
			</div>				
		</div>
				
		<div class="mb-3">
			<label class="text_title">Writer</label>
			<p class="text_subtitle" name="writer" id="writer">${read.writer}</p>
		</div>
			
		<div class="mb-3">
			<label class="text_title">Content</label>
			<div class="form-control img-fluid" style="overflow:hidden; height:auto; min-height:200px;" id="content" >${read.content}</div>		
		</div>
		<!-- Main Content -->
							
		<!-- File List -->		
		<div class="mb-3">
			<label class="text_title">File</label></br>
			<div class="FileList" style="border-top:1px solid darkgray; padding-top:10px;">
				<c:forEach items="${file}" var="file">
					<!-- oracle일 경우 결과값을 Map으로 가져오면 모두다 대문자로 바꾸어 주어야 한다. -->
					<a href="#" onclick="fn_fileDown('${file.file_no}'); return false;">${file.org_file_name}</a>(${file.file_size}kb)<br>
				</c:forEach>	
			</div>
		</div>
		<!-- File List -->			
		
		<!-- Reply List -->					
		<div class="mb-3">
			<label class="text_title">Reply</label>
				<div class="ReplyList" style="border-top:1px solid darkgray; padding-top:10px;"></div>
						
    	</div>
		<!-- Reply List -->	
		
		</br>
					
		<!-- Reply Write -->
  		<div class="mb-3">
  			<input type="hidden" id="dno" name="dno" value="${read.dno}" />
			<input type="hidden" id="login_writer" name="login_writer" value="${loginInfo.ID}"/>
    		<input type="text" id="reply_content" name="reply_content" style="width:100%"/></br>
    		<button type="button" id="replyWriteBtn" name="replyWriteBtn" class="btn btn-sm btn-dark" style="width:100%" >리플 작성</button>
  		</div>
		<!-- Reply Write -->	
			
		</br>
			
		<!-- Button -->				
		<c:if test="${read.writer == loginInfo.ID}">
			<div class="mb-3">
				<input type="button" class="btn btn-sm btn-dark" value="수정" onclick="Modify()">
				<input type="button" class="btn btn-sm btn-dark" value="삭제" onclick="Delete()">
				<input type="button" class="btn btn-sm btn-dark" value="목록" onclick="List()">
			</div>
		</c:if>
		<!-- Button -->	
							
	</div>

</body>
</html>