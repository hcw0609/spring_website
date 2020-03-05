<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<title>Information</title>
	
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
	<!-- Custom -->
 	<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>
  	<!-- 2 -->
  
	<script type="text/javascript">
		
		alert("해당 데이터는 나무위키에서 가져오는 데이터입니다.");
		
		// 팀 정보 가져오기
		var list = ${Team_Info};
		
		// 이미지를 넣기 위한 작업
		var team_image = "/Image/team_image/"+list[0]["정식 명칭"]+".png";
		
		//리플 리스트
		function commentList(){
			
			var club_name = "${club_name}";	
			
		    $.ajax({
		        url : "/board/rePlyList",
		        type : "get",
		        data : {'club_name':club_name},
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
		    			
		    			$.ajax({
		    	   			url : "/board/replyDelete",
		    	   			type : "post",
		    	   			data : {"rno" : rno, "club_name":club_name}
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
			    			        data : {'content' : content, 'rno' : rno, "club_name": club_name},
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
		          		            
		            
		$(document).ready(function(){	
			
			commentList();		
			
			// 리플 작성
			$("#replyWriteBtn").on("click", function(){
				if( $("#reply_content").val() == '' ){
					alert("내용을 입력해 주세요.");
				} else {
					
					var club_name = "${club_name}";					
					var login_writer = $('input[name=login_writer]').val();
					var reply_content = $('input[name=reply_content]').val();
					
					$.ajax({
						 url : '/board/replyWrite',
					     type : 'post',
					     data : {'club_name':club_name, 'writer':login_writer, 'content':reply_content},
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

			
			// 좋아요 버튼
			$("#good").on("click", function(e){
				var club_name = "${club_name}";
				$.ajax({
					url: "/board/good",
					type: "post",
					data: {"club_name":club_name},
					success : function(data){   
						if(data == "NO") {
							alert("로그인 해주세요.");
							location.href="/board/login";
						} else if (data == "Check") {
							alert("이미 투표한 아이디 입니다.");
						} else { }
					}
				})
			})
			
			// 싫어요 버튼
			$("#bad").on("click", function(e){
				var club_name = "${club_name}";
				$.ajax({
					url: "/board/bad",
					type: "post",
					data: {"club_name":club_name},
					success : function(data){   
						if(data == "NO") {
							alert("로그인 해주세요.");
							location.href="/board/login";
						} else if (data == "Check") {
							alert("이미 투표한 아이디 입니다.");
						} else { }
					}
				})
			})
			
			// 팀 이미지 삽입
			$("#team_image").attr("src",team_image);
			
			// 팀명 삽입
			$(".message").html(list[0]["정식 명칭"]);
				
							
			var html_base = "<colgroup class='base'>" +
								"<col style='width:40%;'/>" +
								"<col style='width:60%;'/>" +
							"</colgroup>";	
			
							
			// 팀의 우승에 대한 정보 가져오기
			var Champion = ${Champion};
							
			var html = "";
			
			for (var i=Champion.length; i>=0; i--) {

				html = "<tr class='title'>"	
				
				for(key in Champion[i]) {
					
					html += "<td class='text_subtitle1' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
					html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>"+ Champion[i][key] +"</td>";
											
				}
				
				html += "</tr>"
				
				var trHtml = $( ".start_list:last" );
				trHtml.after(html_base+html);
			}		
			
			
			//  팀의 기본 정보 
			var html = "";
			
			for (var i=list.length; i>=0; i--) {
				html = "<tr class='base'>"					
				for(key in list[i]) {					
					if(key == "홈 구장" || key == "홈구장" ) {
						html += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
						html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>" + "<a onclick=\"window.open('/board/map?execute=yes&address="+list[i][key]+"','window_name','width=1400px, height=700px, location=no, status=no,scrollbars=yes')\">" + list[i][key] +"</td>";
					} else {
						html += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";
						html += "<td class='text_subtitle1 text-left' style='border: 1.5px solid #787777;'>"+ list[i][key] +"</td>";
					}													
				}					
				html += "</tr>"					
				var trHtml = $( ".start_list:last" );
				trHtml.after(html_base+html);
			}	
				
			
			// 스쿼드 정보 가져오기
			var Player_list = ${Player_Info};
																
			var html_title = "";
			var html_content = "";
						
			// 스쿼드 정보의 타이틀 
			html_title = "<tr class='roster'>";				
			for (key in Player_list[0]) {		
				if(key == "") {
					html_title += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ "국적" +"</td>";					
				} else {
					html_title += "<td class='text_title' style='color:#ffffff; background-color:#000000; border: 1.5px solid #787777;'>"+ key +"</td>";					
				}
				
			}
			html_title += "</tr>";
			// 스쿼드 정보의 타이틀 
			
			
			// 스쿼드 타이틀의 정보
			for(var i=0; i<=Player_list.length; i++) {
				
				html_content += "<tr class='roster'>"
				
				for (key in Player_list[i] ) {
					if(key == "") {
						html_content += "<td class='text_subtitle1 ' style='border: 1.5px solid #787777;'>" + "<img art='' src="+Player_list[i][key]+" style='width:50px; height:30px;'>"+"</td>";
					} else {
						html_content += "<td class='text_subtitle1 ' style='border: 1.5px solid #787777;'>"+ Player_list[i][key] +"</td>";
					}
					
				}
				
				html_content += "</tr>"
			}
			// 스쿼드 타이틀의 정보	
																
			var trHtml = $( ".start_list2:last" );
			trHtml.after(html_title+html_content);
										
		})
		
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
	        	<h3 class="my-4 message"></h3>
			</div>   
			<!-- 측면 메뉴 -->   		     

			<!-- Main Content -->
			<div class="col-lg-4" style="margin-top:20px">
			<div style="max-width:700px; width:100%; border: 2px solid #787777; text-align:center;">
				<div>
					<img id="team_image" style="width:200px; height:200px;">			
				</div>
										
				<div class="text_title message" style="color:#ffffff; background-color:#000000; border: 1.5px solid #787777;"></div>
							
				<table style="max-width:700px; width:100%; border:1.5px solid #787777;">
					<input class="start_list" type="hidden">				
				</table>
				
			</div>			      			
			</div>			
  			
   			<div class="col-lg-6" style="margin-top:20px; margin-bottom:20px;">	
   												
				<table style="max-width:800px; width:100%; border:2px solid #787777; text-align:center;">
					<input class="start_list2" type="hidden">				
				</table>
				
				<br>
				
				<button style="width:49.9%; height:80px; float:left; background-color:white; margin-bottom:20px;"  id="good"><img alt="" src="/Image/Icon/like.png" style="width:64px; height:64px;"></button>
				<button style="width:49.9%; height:80px; float:right; background-color:white; margin-bottom:20px;" id="bad"><img alt="" src="/Image/Icon/hate.png" style="width:64px; height:64px;"></button>
										
				<!-- Reply List -->					
				<div class="mb-3">
					<label class="text_title">Reply</label>
						<div class="ReplyList" style="border-top:1px solid darkgray; padding-top:10px;"></div>
								
		    	</div>
				<!-- Reply List -->	
							
				<!-- Reply Write -->
		  		<div class="mb-3">
					<input type="hidden" id="login_writer" name="login_writer" value="${loginInfo.ID}"/>
		    		<input type="text" id="reply_content" name="reply_content" style="width:100%"/></br>
		    		<button type="button" id="replyWriteBtn" name="replyWriteBtn" class="btn btn-sm btn-dark" style="width:100%" >리플 작성</button>
		  		</div>
				<!-- Reply Write -->	 			
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